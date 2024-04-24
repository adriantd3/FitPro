package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.*;
import uma.fitpro.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {

    // EN DESARROLLO
    private int entrenador_id = 4;

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

    @Autowired
    protected SerieRepository serieRepository;

    // --------------------------- CLIENTES ---------------------------

    @GetMapping("/clientes")
    public String doClientes(Model model){
        Usuario entrenador = usuarioRepository.findById(entrenador_id).orElse(null);
        List<Usuario> clientes = new ArrayList<>(entrenador.getClientesEntrenador());
        model.addAttribute("clientes", clientes);
        return "entrenador_cross_training/clientes";
    }

    // --------------------------- RUTINAS ---------------------------

    @GetMapping("/rutinas")
    public String doRutinas(Model model){
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("rutinas", rutinas);
        return "entrenador_cross_training/rutinas";
    }

    @GetMapping("borrar_rutina")
    public String doBorrarRutina(@RequestParam("id") Integer id_rutina){
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        rutinaRepository.delete(rutina);
        return "redirect:/entrenador_cross_training/rutinas";
    }

    @PostMapping("nueva_rutina")
    public String doNuevaRutina(@RequestParam("nombre") String nombre){
        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        Usuario entrenador = usuarioRepository.findById(entrenador_id).orElse(null);
        rutina.setEntrenador(entrenador);
        rutina.setFechaCreacion(LocalDate.now());
        this.rutinaRepository.save(rutina);
        return "redirect:/entrenador_cross_training/rutinas";
    }

    @GetMapping("/rutinas_cliente")
    public String doRutinasCliente(Model model, @RequestParam("id") Integer id_cliente){
        Usuario cliente = usuarioRepository.findById(id_cliente).orElse(null);
        List<Rutina> rutinas = new ArrayList<>(cliente.getRutinasCliente());
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("cliente", cliente);
        return "entrenador_cross_training/rutinas";
    }

    /*@PostMapping("borrar_rutina_cliente")
    public String doBorrarRutinaCliente(@RequestParam("rutina") Integer id_rutina,
                                        @RequestParam("cliente") Integer id_cliente){
        //Usuario cliente = usuarioRepository.findById(id_cliente);
        //Rutina rutina = rutinaRepository.findById(id_rutina);
        return "redirect:/entrenador_cross_training/rutinas_cliente?id_cliente=" + id_cliente;
    }*/

    // --------------------------- SESIONES ---------------------------

    @GetMapping("/sesiones")
    public String doSesiones(Model modelo){
        List<Sesion> sesiones = sesionRepository.findAll();

        modelo.addAttribute("sesiones", sesiones);
        return "entrenador_cross_training/sesiones";
    }

    @GetMapping("/borrar_sesion")
    public String doBorrarSesion(@RequestParam("id") Integer id_sesion){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        sesionRepository.delete(sesion);
        return "redirect:/entrenador_cross_training/sesiones";
    }

    @GetMapping("/sesion")
    public String doSesion(@RequestParam("id") Integer id_sesion, Model model){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        List<Serie> series = new ArrayList<>(sesion.getSeries());
        Map<Ejercicio, List<Serie>> mapa = getEjercicioYSeries(series);

        model.addAttribute("sesion", sesion);
        model.addAttribute("mapa", mapa);
        return "entrenador_cross_training/sesion";
    }

    @PostMapping("nueva_sesion")
    public String doNuevaSesion(@RequestParam("nombre") String nombre){
        Sesion sesion = new Sesion();
        sesion.setNombre(nombre);
        sesionRepository.save(sesion);
        return "redirect:/entrenador_cross_training/sesiones";
    }

    private Map<Ejercicio, List<Serie>> getEjercicioYSeries(List<Serie> series){
        Map<Ejercicio, List<Serie>> mapa = new HashMap<Ejercicio, List<Serie>>();
        for (Serie e : series){
            List<Serie> s = mapa.get(e.getEjercicio());
            if (s==null){
                s = new ArrayList<>();
            }
            s.add(e);
            mapa.put(e.getEjercicio(), s);
        }
        return mapa;
    }


    // --------------------------- EJERCICIOS ---------------------------

    @GetMapping("/ejercicios")
    public String doEjercicios(@RequestParam("ejercicio") String ejercicio,
                               @RequestParam("musculo") String musculo,
                               @RequestParam("tipo") String tipo,
                               @RequestParam(value = "sesion", required = false) Integer id_sesion, Model modelo){
        // DE MOMENTO SOLO FILTRA POR NOMBRE DEL EJERCICIO
        List<Ejercicio> ejercicios = ejercicioRepository.filtrarEjercicio(ejercicio);
        List<TipoEjercicio> tipos = tipoEjercicioRepository.findAll();
        List<GrupoMuscular> grupos = grupoMuscularRepository.findAll();
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);

        modelo.addAttribute("ejercicios", ejercicios);
        modelo.addAttribute("tipos", tipos);
        modelo.addAttribute("grupos", grupos);
        modelo.addAttribute("sesion", sesion);

        return "entrenador_cross_training/ejercicios";
    }

    @GetMapping("anyadir_ejercicio")
    public String doAnyadirEjercicio(@RequestParam("sesion") Integer id_sesion,
                                     @RequestParam("ejercicio") Integer id_ejercicio,
                                     Model model){
        Ejercicio ejercicio = ejercicioRepository.findById(id_ejercicio).orElse(null);
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        List<Serie> series = new ArrayList<>(sesion.getSeries());
        Map<Ejercicio, List<Serie>> mapa = getEjercicioYSeries(series);
        if (!mapa.containsKey(ejercicio)){
            mapa.put(ejercicio, new ArrayList<>());
        }


        model.addAttribute("sesion", sesion);
        model.addAttribute("mapa", mapa);
        return "entrenador_cross_training/sesion";
    }

    // --------------------------- SERIES ---------------------------

    @GetMapping("/anyadir_serie")
    public String doAnyadirSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("ejercicio") Integer id_ejercicio,
                                 Model model){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        Ejercicio ejercicio = ejercicioRepository.findById(id_ejercicio).orElse(null);

        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("sesion", sesion);
        return "entrenador_cross_training/anyadir_serie";
    }

    @PostMapping("guardar_serie")
    public String doGuardarSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("ejercicio") Integer id_ejercicio,
                                 @RequestParam("repeticiones") Integer repeticiones,
                                 @RequestParam("peso") float peso){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        Ejercicio ejercicio = ejercicioRepository.findById(id_ejercicio).orElse(null);

        Serie nueva_serie = new Serie();
        SerieId serieId = new SerieId();
        serieId.setEjercicioId(ejercicio.getId());
        serieId.setSesionId(sesion.getId());

        nueva_serie.setEjercicio(ejercicio);
        nueva_serie.setSesion(sesion);
        nueva_serie.setId(serieId);
        nueva_serie.setPeso(peso);
        nueva_serie.setRepeticiones(repeticiones);
        this.serieRepository.save(nueva_serie);

        return "redirect:/entrenador_cross_training/sesion?id=" + sesion.getId();
    }

    @GetMapping("borrar_serie")
    public String doBorrarSerie(@RequestParam("sesion") Sesion sesion,
                                @RequestParam("serie") Integer id_serie,
                                @RequestParam("ejercicio") Integer id_ejercicio){

        SerieId serieId = new SerieId();
        serieId.setId(id_serie);
        serieId.setSesionId(sesion.getId());
        serieId.setEjercicioId(id_ejercicio);

        Serie serie = this.serieRepository.findById(serieId).orElse(null);

        this.serieRepository.delete(serie);

        return "redirect:/entrenador_cross_training/sesion?id=" +sesion.getId();
    }


}
