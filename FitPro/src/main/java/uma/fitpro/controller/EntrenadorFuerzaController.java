package uma.fitpro.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.*;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//////////////////////////////////////////////////////
/////////        ENTRENADOR FUERZA           /////////
//////////////////////////////////////////////////////

/**
 * @author Victor Perez Armenta
 */
@Controller
@RequestMapping("/entrenador_fuerza")
public class EntrenadorFuerzaController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private SerieRepository serieRepository;

    @PostMapping("/home")
    public String doEntrenadorFuerzaHome() {
        return "/entrenador_fuerza/home";
    }

    @GetMapping("/home")
    public String doHome() {
        return "/entrenador_fuerza/home";
    }

    @GetMapping("/crud-rutina")
    public String doCrudRutina(@RequestParam("cliente")@Nullable Integer cliente_id, Model model, HttpSession session) {

        List<Rutina> rutinas;
        Usuario entrenador = (Usuario) session.getAttribute("user");
        if (cliente_id != null) {
            Usuario cliente = usuarioRepository.findById(cliente_id).orElse(null);
            session.setAttribute("cliente", cliente);
            rutinas = rutinaRepository.findByClienteByEntrenador(cliente.getRutinasCliente(), entrenador);

        }
        else{
            rutinas = new ArrayList<>(entrenador.getRutinasEntrenador());
            session.setAttribute("cliente", null);
        }

        model.addAttribute("rutinas", rutinas);
        return "/entrenador_fuerza/crud-rutina";
    }

    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session) {

        Usuario entrenador = (Usuario)session.getAttribute("user");
        Set<Usuario> clientes = entrenador.getClientesEntrenador();
        model.addAttribute("clientes", clientes);

        return "/entrenador_fuerza/clientes";
    }

    @GetMapping("/crear-sesion")
    public String doCrearSesion(@RequestParam("sesion")@Nullable Integer sesion_id, Model model, HttpSession session) {
        Sesion sesion;
        if (sesion_id != null) {
            sesion = sesionRepository.findById(sesion_id).orElse(null);
            model.addAttribute("sesion", sesion);
        }

        return "/entrenador_fuerza/crear-sesion";
    }

    @GetMapping("/rutina")
    public String doRutina(@RequestParam("rutina") Integer rutina_id, Model model, HttpSession session) {
        Rutina rutina = rutinaRepository.findById(rutina_id).orElse(null);
        session.setAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesionRepository.findAllByOrdenSesionRutina(rutina.getOrdenSesionRutinas()));
        return "/entrenador_fuerza/rutina";
    }

    @GetMapping("/asignar-ejercicio")
    public String doAsignarEjercicio(@RequestParam("sesion") Integer sesion_id, Model model, HttpSession session) {
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        model.addAttribute("sesion", sesion);

        model.addAttribute("ejercicios", ejercicioRepository.findAll());

        return "/entrenador_fuerza/asignar-ejercicio";
    }

    @PostMapping("/guardar-ejercicio")
    public String doGuardarClientes(@RequestParam("ejercicio") Integer ejercicio_id,
                                    @RequestParam("sesion") Integer sesion_id, Model model, HttpSession session) {
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicio_id).orElse(null);
        model.addAttribute("sesion", sesion);

        Serie serieNueva = new Serie();
        serieNueva.setRepeticiones(0);
        serieNueva.setPeso((float)0);
        serieNueva.setEjercicio(ejercicio);
        serieNueva.setSesion(sesion);

        serieRepository.saveAndFlush(serieNueva);

        return "/entrenador_fuerza/crear-sesion";
    }
}
