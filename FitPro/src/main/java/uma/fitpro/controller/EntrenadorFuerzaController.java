package uma.fitpro.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.*;
import uma.fitpro.entity.*;

import java.time.LocalDate;
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
    @Autowired
    private OrdenSesionRutinaRepository ordenSesionRutinaRepository;

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
            rutinas = rutinaRepository.findByNotInRutinasCliente(cliente.getRutinasCliente());
        }
        else{
            rutinas = new ArrayList<>(entrenador.getRutinasEntrenador());
            session.setAttribute("cliente", null);
        }
        model.addAttribute("rutinas", rutinas);
        return "/entrenador_fuerza/crud-rutina";
    }

    @PostMapping("/crear-rutina")
    public String doNuevaRutina(@RequestParam("nombreRutina") String nombreRutina, Model model, HttpSession session){
        Rutina rutina = new Rutina();

        Usuario entrenador = (Usuario) session.getAttribute("user");
        rutina.setEntrenador(entrenador);

        Set<Rutina> rutinasEntrenador = entrenador.getRutinasEntrenador();
        rutinasEntrenador.add(rutina);
        entrenador.setRutinasEntrenador(rutinasEntrenador);
        usuarioRepository.save(entrenador);


        rutina.setNombre(nombreRutina);
        rutina.setFechaCreacion(LocalDate.now());

        Usuario cliente = (Usuario) session.getAttribute("cliente");
        if(cliente != null){
            Set<Rutina> rutinasCliente = cliente.getRutinasCliente();
            rutinasCliente.add(rutina);
            cliente.setRutinasCliente(rutinasCliente);
            usuarioRepository.save(cliente);
        }

        rutinaRepository.save(rutina);
        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();

    }

    @GetMapping("guardar-rutina")
    public String doGuardarRutina(@RequestParam("rutina") Integer rutina_id, Model model, HttpSession session){
        Rutina rutina = rutinaRepository.findById(rutina_id).orElse(null);
        Usuario cliente = (Usuario) session.getAttribute("cliente");

        Set<Rutina> nuevasRutinas = new HashSet<>(cliente.getRutinasCliente());
        nuevasRutinas.add(rutina);
        cliente.setRutinasCliente(nuevasRutinas);

        usuarioRepository.save(cliente);
        return "redirect:/entrenador_fuerza/crud-rutina?cliente=" + cliente.getId();

    }

    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session) {

        Usuario entrenador = (Usuario)session.getAttribute("user");
        Set<Usuario> clientes = entrenador.getClientesEntrenador();
        model.addAttribute("clientes", clientes);

        return "/entrenador_fuerza/clientes";
    }

    @GetMapping("/sesion")
    public String doSesion(@RequestParam("sesion")@Nullable Integer sesion_id, Model model) {
        Sesion sesion;
        if (sesion_id != null) {
            sesion = sesionRepository.findById(sesion_id).orElse(null);
            model.addAttribute("sesion", sesion);
        }

        //System.out.printf(String.valueOf(model.getAttribute("serie") == null));

        return "/entrenador_fuerza/sesion";
    }

    @PostMapping("/crear-sesion")
    public String doCrearSesion(@RequestParam("nombreSesion") String nombreSesion, Model model, HttpSession session) {

        Sesion sesion = new Sesion();
        sesion.setNombre(nombreSesion);
        sesionRepository.save(sesion);

        Rutina rutina = (Rutina) session.getAttribute("rutina");
        OrdenSesionRutina ordenSesion = new OrdenSesionRutina();
        OrdenSesionRutinaId idOrdenSesion = new OrdenSesionRutinaId();
        idOrdenSesion.setSesionId(sesion.getId());
        idOrdenSesion.setRutinaId(rutina.getId());
        idOrdenSesion.setOrden(1);
        ordenSesion.setId(idOrdenSesion);
        ordenSesion.setSesion(sesion);
        ordenSesion.setRutina(rutina);
        ordenSesionRutinaRepository.save(ordenSesion);

        Set<OrdenSesionRutina> ordenSesionesRutina = rutina.getOrdenSesionRutinas();
        ordenSesionesRutina.add(ordenSesion);
        rutina.setOrdenSesionRutinas(ordenSesionesRutina);
        rutinaRepository.save(rutina);

        return "redirect:/entrenador_fuerza/sesion?sesion=" + sesion.getId();
    }

    @GetMapping("borrar-sesion")
    public String doBorrarSesion(@RequestParam("sesion") Integer sesion_id, Model model, HttpSession session) {
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        sesionRepository.delete(sesion);
        Rutina rutina = (Rutina) session.getAttribute("rutina");

        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();
    }

    @GetMapping("/rutina")
    public String doRutina(@RequestParam("rutina") Integer rutina_id, Model model, HttpSession session) {
        Rutina rutina = rutinaRepository.findById(rutina_id).orElse(null);
        session.setAttribute("rutina", rutina);
        System.out.println(rutina.getOrdenSesionRutinas());
        System.out.println(sesionRepository.findAllByOrdenSesionRutina(rutina));
        model.addAttribute("sesiones", sesionRepository.findAllByOrdenSesionRutina(rutina));
        return "/entrenador_fuerza/rutina";
    }

    @GetMapping("/asignar-ejercicio")
    public String doAsignarEjercicio(@ModelAttribute("sesion") Integer sesion_id, Model model) {
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        model.addAttribute("sesion", sesion);

        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        model.addAttribute("ejercicios", ejercicios);

        Serie serie = new Serie();
        model.addAttribute("serie", serie);

        return "/entrenador_fuerza/asignar-ejercicio";
    }

    @PostMapping("/guardar-ejercicio")
    public String doGuardarEjercicio(@ModelAttribute("serie") Serie serie, Model model) {
        model.addAttribute("sesion", serie.getSesion());

        serie.setRepeticiones(0);
        serie.setPeso((float)0);
        serie.setDistancia((float)0);
        serie.setDuracion(0);
        serieRepository.save(serie);

        return "/entrenador_fuerza/crear-sesion";
    }

    @GetMapping("editar-serie")
    public String doEditarSerie(@RequestParam("serie") Integer serie_id, Model model) {
        Serie serie = serieRepository.findById(serie_id).orElse(null);
        model.addAttribute("serie", serie);
        model.addAttribute("sesion", serie.getSesion());
        return "/entrenador_fuerza/sesion";
    }

    @PostMapping("/guardar-serie")
    public String doGuardarSerie(@ModelAttribute("serie") Serie serie) {
        serieRepository.save(serie);

        return "redirect:/entrenador_fuerza/sesion?sesion=" + serie.getSesion().getId();
    }

    @GetMapping("eliminar-serie")
    public String doEliminarSerie(@RequestParam("serie") Integer serie_id) {
        Serie serie = serieRepository.findById(serie_id).orElse(null);
        serieRepository.delete(serie);

        return "redirect:/entrenador_fuerza/sesion?sesion=" + serie.getSesion().getId();
    }

    @GetMapping("anyadir-serie")
    public String doAnyadirSerie(@RequestParam("sesion") Integer sesion_id ,
                                 @RequestParam("ejercicio") Integer ejercicio_id) {
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicio_id).orElse(null);
        Serie serie = new Serie();
        serie.setRepeticiones(0);
        serie.setPeso((float)0);
        serie.setDistancia((float)0);
        serie.setDuracion(0);
        serie.setSesion(sesion);
        serie.setEjercicio(ejercicio);

        serieRepository.save(serie);
        return "redirect:/entrenador_fuerza/editar-serie?serie=" + serie.getId();
    }
}
