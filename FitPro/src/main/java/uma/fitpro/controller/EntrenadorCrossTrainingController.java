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
/*
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
        //Collections.sort(clientes);

        model.addAttribute("clientes", clientes);

        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/filtrar_clientes")
    public String doFiltrarClientes(@RequestParam("nombre") String nombre,
                                    @RequestParam("edad") String edad,
                                    @RequestParam("altura") String altura,
                                    @RequestParam("peso") String peso,
                                    Model model,HttpSession session){
        Usuario user = (Usuario) session.getAttribute("user");
        Usuario entrenador =  usuarioRepository.findById(user.getId()).orElse(null);
        Integer edadInt = Integer.parseInt(edad);
        Float pesoFloat = Float.parseFloat(peso);
        Float alturaFloat = Float.parseFloat(altura);
        List<Usuario> clientes = usuarioRepository.filtrarCliente(nombre, entrenador, edadInt,pesoFloat, alturaFloat);

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

    @GetMapping("/filtrar_rutinas")
    public String doFiltrarRutinas(@RequestParam("nombre") String nombre,
                                   @RequestParam("fecha") String fecha,
                                   Model model,HttpSession session){

        Usuario entrenador = (Usuario) session.getAttribute("user");
        LocalDate fechaFiltrada = getFecha(fecha);
        List<Rutina> rutinas = rutinaRepository.getFilteredRutinasByEntrenadorAndFecha(entrenador.getId(), nombre, fechaFiltrada);
        Collections.sort(rutinas);

        model.addAttribute("rutinas", rutinas);

        return "entrenador_cross_training/rutinas";
    }

    private LocalDate getFecha(String fecha){
        LocalDate res;
        if (fecha.isEmpty()){
            res = LocalDate.of(1,1,1);
        }else {
            String[] aux = fecha.split("-");
            if (aux.length == 3){
                res = LocalDate.of(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
            }else {
                res = LocalDate.of(1,1,1);
            }
        }

        return res;
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

    @GetMapping("/filtrar_sesiones")
    public String doFiltrarSesiones(@RequestParam("nombre") String nombre,Model model,HttpSession session){

        String str = "redirect:/entrenador_cross_training/sesiones";
        if (!nombre.isEmpty()){

            List<Sesion> sesiones = sesionRepository.findByNombre(nombre);

            model.addAttribute("sesiones", sesiones);

            str = "entrenador_cross_training/sesiones";

        }

        return str;
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
        Map<Integer,List<String>> ejercicioParametros = getEjercicioParametros();

        model.addAttribute("ejercicioParametros", ejercicioParametros);
        model.addAttribute("sesion", sesion);
        model.addAttribute("mapa", mapa);

        return "entrenador_cross_training/sesion";
    }

    private Map<Integer,List<String>> getEjercicioParametros(){
        HashMap<Integer, List<String>> mapa = new HashMap<>();
        mapa.put(1, Arrays.asList("Peso (kg)", "Repeticiones"));
        mapa.put(2, Arrays.asList("Distancia (m)", "Duracion (seg)"));
        mapa.put(3, Arrays.asList("Duracion (seg)", "Descanso (seg)"));
        mapa.put(4, Arrays.asList("Repeticiones", "Descanso (seg)"));
        mapa.put(5, Arrays.asList("Repeticiones", "Descanso (seg)"));

        return mapa;
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
        Map<Integer,List<String>> ejercicioParametros = getEjercicioParametros();

        model.addAttribute("ejercicioParametros", ejercicioParametros);
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
        Map<Integer,List<String>> ejercicioParametros = getEjercicioParametros();

        model.addAttribute("ejercicioParametros", ejercicioParametros);
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("sesion", sesion);

        return "entrenador_cross_training/anyadir_serie";
    }

    @PostMapping("/crear_serie")
    public String doCrearSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("ejercicio") Integer id_ejercicio,
                                 @RequestParam("param1") String parametro1,
                                 @RequestParam("param2") String parametro2){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        Ejercicio ejercicio = ejercicioRepository.findById(id_ejercicio).orElse(null);

        Serie nueva_serie = new Serie();
        nueva_serie.setEjercicio(ejercicio);
        nueva_serie.setSesion(sesion);

        guardarSerie(nueva_serie, parametro1, parametro2);
        this.serieRepository.save(nueva_serie);

        return "redirect:/entrenador_cross_training/sesion?id=" + sesion.getId();
    }

    @GetMapping("editar_serie")
    public String doEditarSerie(@RequestParam("sesion") Integer id_sesion,
                                @RequestParam("serie") Integer id_serie,
                                Model model){
        Serie serie = serieRepository.findById(id_serie).orElse(null);
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        Map<Integer,List<String>> ejercicioParametros = getEjercicioParametros();

        model.addAttribute("ejercicioParametros", ejercicioParametros);
        model.addAttribute("sesion", sesion);
        model.addAttribute("serie", serie);

        return "entrenador_cross_training/editar_serie";
    }

    @PostMapping("/guardar_serie")
    public String doGuardarSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("serie") Integer id_serie,
                                 @RequestParam("param1") String parametro1,
                                 @RequestParam("param2") String parametro2){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        Serie serie = serieRepository.findById(id_serie).orElse(null);

        guardarSerie(serie, parametro1, parametro2);
        this.serieRepository.save(serie);

        return "redirect:/entrenador_cross_training/sesion?id=" + sesion.getId();
    }

    @PostMapping("/borrar_serie")
    public String doBorrarSerie(@RequestParam("sesion") Sesion sesion,
                                @RequestParam("serie") Integer id_serie){

        Serie serie = this.serieRepository.findById(id_serie).orElse(null);
        this.serieRepository.delete(serie);

        return "redirect:/entrenador_cross_training/sesion?id=" +sesion.getId();
    }

    private void guardarSerie(Serie serie, String parametro1, String parametro2){
        // Guardamos los parametros de la serie (segun el ejercicio)
        switch (serie.getEjercicio().getTipo().getId()){
            case 1:
                serie.setPeso(Float.parseFloat(parametro1));
                serie.setRepeticiones(Integer.parseInt(parametro2));
                break;
            case 2:
                serie.setDistancia(Float.parseFloat(parametro1));
                serie.setDuracion(Integer.parseInt(parametro2));
                break;
            case 3:
                serie.setDuracion(Integer.parseInt(parametro1));
                serie.setDescanso(Integer.parseInt(parametro2));
                break;
            case 4,5:
                serie.setRepeticiones(Integer.parseInt(parametro1));
                serie.setDescanso(Integer.parseInt(parametro2));
                break;
        }

    }

*/
}
