package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.*;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {

    // EN DESARROLLO
    private int entrenador_id = 4;

    @Autowired
    protected EntrenadorClienteRepository entrenadorClienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RutinaRepository rutinaRepository;

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @Autowired
    protected TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    protected GrupoMuscularRepository grupoMuscularRepository;

    @Autowired
    protected SesionRepository sesionRepository;

    @GetMapping("/clientes")
    public String doClientes(Model model){
        Usuario entrenador = usuarioRepository.findById(entrenador_id).orElse(null);
        List<EntrenadorCliente> lista = entrenadorClienteRepository.findByEntrenador(entrenador.getId());
        List<Usuario> clientes = new ArrayList<>();
        for (EntrenadorCliente e : lista) {
            clientes.add(e.getCliente());
        }
        model.addAttribute("clientes", clientes);
        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/rutinas")
    public String doRutinas(Model model){
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("rutinas", rutinas);
        return "entrenador_cross_training/rutinas";
    }

    @GetMapping("/sesiones")
    public String doSesiones(Model modelo){
        List<Sesion> sesiones = sesionRepository.findAll();

        modelo.addAttribute("sesiones", sesiones);
        return "entrenador_cross_training/sesiones";
    }

    @GetMapping("/sesion")
    public String doSesion(){

        return "entrenador_cross_training/sesion";
    }

    @GetMapping("/ejercicios")
    public String doEjercicios(@RequestParam("ejercicio") String ejercicio,
                               @RequestParam("musculo") String musculo,
                               @RequestParam("tipo") String tipo, Model modelo){
        // DE MOMENTO SOLO FILTRA POR NOMBRE DEL EJERCICIO
        List<Ejercicio> ejercicios = ejercicioRepository.filtrarEjercicio(ejercicio);
        List<TipoEjercicio> tipos = tipoEjercicioRepository.findAll();
        List<GrupoMuscular> grupos = grupoMuscularRepository.findAll();

        modelo.addAttribute("ejercicios", ejercicios);
        modelo.addAttribute("tipos", tipos);
        modelo.addAttribute("grupos", grupos);

        return "entrenador_cross_training/ejercicios";
    }
}
