package uma.fitpro.controller;

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

import java.time.LocalDate;
import java.util.*;

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {

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

    @Autowired
    protected OrdenSesionRutinaRepository ordenSesionRutinaRepository;

    // --------------------------- HOME ---------------------------

    @GetMapping("/home")
    public String doHome(Model model){
        return "entrenador_cross_training/home";
    }
    
    // --------------------------- CLIENTES ---------------------------

    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session){
        Usuario user = (Usuario) session.getAttribute("user");
        Usuario entrenador =  usuarioRepository.findById(user.getId()).orElse(null);
        List<Usuario> clientes = new ArrayList<>(entrenador.getClientesEntrenador());
        Collections.sort(clientes);

        model.addAttribute("clientes", clientes);

        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/rutinas_cliente")
    public String doRutinasCliente(Model model, @RequestParam("id") Integer id_cliente,
                                   HttpSession session){
        Usuario cliente = usuarioRepository.findById(id_cliente).orElse(null);
        List<Rutina> rutinas = new ArrayList<>(cliente.getRutinasCliente());
        Usuario entrenador = (Usuario) session.getAttribute("user");
        List<Rutina> todasLasRutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        todasLasRutinas.removeAll(rutinas);

        model.addAttribute("todasLasRutinas", todasLasRutinas);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("cliente", cliente);

        return "entrenador_cross_training/rutinas_cliente";
    }

    @PostMapping("/asignar_rutina_cliente")
    public String doAsignarRutinaCliente(@RequestParam("rutina") Rutina rutina,
                                         @RequestParam("cliente") Usuario cliente){
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.add(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);

        return "redirect:/entrenador_cross_training/rutinas_cliente?id=" + cliente.getId();
    }

    @PostMapping("/borrar_rutina_cliente")
    public String doBorrarRutinaCliente(@RequestParam("rutina") Integer id_rutina,
                                        @RequestParam("cliente") Integer id_cliente){

        Usuario cliente = usuarioRepository.findById(id_cliente).orElse(null);
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.remove(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);

        return "redirect:/entrenador_cross_training/rutinas_cliente?id=" + id_cliente;
    }

    // --------------------------- RUTINAS ---------------------------

    @GetMapping("/rutinas")
    public String doRutinas(Model model, HttpSession session){
        Usuario entrenador = (Usuario) session.getAttribute("user");
        List<Rutina> rutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        Collections.sort(rutinas);

        model.addAttribute("rutinas", rutinas);

        return "entrenador_cross_training/rutinas";
    }

    @PostMapping("/borrar_rutina")
    public String doBorrarRutina(@RequestParam("id") Integer id_rutina){
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        rutinaRepository.delete(rutina);

        return "redirect:/entrenador_cross_training/rutinas";
    }

    @PostMapping("/nueva_rutina")
    public String doNuevaRutina(@RequestParam("nombre") String nombre, HttpSession session){
        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        Usuario entrenador = (Usuario) session.getAttribute("user");
        rutina.setEntrenador(entrenador);
        rutina.setFechaCreacion(LocalDate.now());
        this.rutinaRepository.save(rutina);

        return "redirect:/entrenador_cross_training/rutinas";
    }

    @GetMapping("/editar_rutina")
    public String doEditarRutina(@RequestParam("id") Integer id_rutina, Model model){
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        List<OrdenSesionRutina> ordenSesiones = new ArrayList<>(rutina.getOrdenSesionRutinas());
        Map<Integer, Sesion> diaSesion = getDiasSesion(ordenSesiones);
        Map<Integer, String> diasSemana = getDiasSemana();
        List<Sesion> sesiones = sesionRepository.findAll();

        model.addAttribute("diaSesion", diaSesion);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);

        return "entrenador_cross_training/editar_rutina";
    }

    @PostMapping("/asociar_dia_sesion")
    public String doAsociarDiaSesion(@RequestParam("rutina") Rutina rutina,
                                     @RequestParam("dia") Integer dia,
                                     @RequestParam("nueva_sesion") Integer nueva_sesion_id,
                                     @RequestParam("antigua_sesion") Integer antigua_sesion_id){

        Sesion nueva_sesion = sesionRepository.findById(nueva_sesion_id).orElse(null);
        Sesion antigua_sesion = sesionRepository.findById(antigua_sesion_id).orElse(null);

        if (antigua_sesion != null){ // Ya habia una sesion asociada a ese dia
            OrdenSesionRutinaId id = new OrdenSesionRutinaId();
            id.setOrden(dia);
            id.setSesionId(antigua_sesion.getId());
            id.setRutinaId(rutina.getId());

            OrdenSesionRutina antigua = ordenSesionRutinaRepository.findById(id).orElse(null);
            ordenSesionRutinaRepository.delete(antigua);
        }

        if (nueva_sesion != null){ // No queremos asociar ninguna sesion
            OrdenSesionRutinaId nueva_id = new OrdenSesionRutinaId();
            nueva_id.setOrden(dia);
            nueva_id.setSesionId(nueva_sesion.getId());
            nueva_id.setRutinaId(rutina.getId());

            OrdenSesionRutina nueva = new OrdenSesionRutina();
            nueva.setId(nueva_id);
            nueva.setSesion(nueva_sesion);
            nueva.setRutina(rutina);
            ordenSesionRutinaRepository.save(nueva);
        }


        return "redirect:/entrenador_cross_training/editar_rutina?id=" + rutina.getId();
    }

    private Map<Integer, String> getDiasSemana(){
        Map<Integer, String> mapa = new HashMap<>();
        mapa.put(1, "Lunes");
        mapa.put(2, "Martes");
        mapa.put(3, "Miércoles");
        mapa.put(4, "Jueves");
        mapa.put(5, "Viernes");
        mapa.put(6, "Sábado");
        mapa.put(7, "Domingo");
        return mapa;
    }

    private Map<Integer, Sesion> getDiasSesion(List<OrdenSesionRutina> ordenSesiones){
        Map<Integer, Sesion> mapa = new HashMap<>();
        for (OrdenSesionRutina o : ordenSesiones){
            OrdenSesionRutinaId id = o.getId();
            Integer orden = id.getOrden();
            mapa.put(orden, o.getSesion());
        }
        return mapa;
    }




    // --------------------------- SESIONES ---------------------------

    @GetMapping("/sesiones")
    public String doSesiones(Model modelo){
        List<Sesion> sesiones = sesionRepository.findAll();

        modelo.addAttribute("sesiones", sesiones);

        return "entrenador_cross_training/sesiones";
    }

    @PostMapping("/borrar_sesion")
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

    @PostMapping("/nueva_sesion")
    public String doNuevaSesion(@RequestParam("nombre") String nombre){
        Sesion sesion = new Sesion();
        sesion.setNombre(nombre);
        sesionRepository.save(sesion);

        return "redirect:/entrenador_cross_training/sesiones";
    }

    private Map<Ejercicio, List<Serie>> getEjercicioYSeries(List<Serie> series){
        Map<Ejercicio, List<Serie>> mapa = new TreeMap<>();
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
                               @RequestParam(value = "aplicar", required = false) Boolean aplicar, Model modelo){
        List<Ejercicio> ejercicios;
        if (aplicar!=null){ // Filtro por nombre, musculo y tipo
            ejercicios = ejercicioRepository.filtrarEjercicioPorMusculoYTipo(ejercicio, musculo, tipo);
        }else { // Filtro por nombre solo
            ejercicios = ejercicioRepository.filtrarEjercicioPorNombre(ejercicio);
        }

        List<TipoEjercicio> tipos = tipoEjercicioRepository.findAll();
        List<GrupoMuscular> grupos = grupoMuscularRepository.findAll();

        modelo.addAttribute("ejercicios", ejercicios);
        modelo.addAttribute("tipos", tipos);
        modelo.addAttribute("grupos", grupos);

        return "entrenador_cross_training/ejercicios";
    }

    @PostMapping("/anyadir_ejercicio")
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

    @PostMapping("/guardar_serie")
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

    @PostMapping("/borrar_serie")
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
