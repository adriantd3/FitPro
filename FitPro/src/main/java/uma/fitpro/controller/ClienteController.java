package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.*;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.entity.*;
import uma.fitpro.utils.UtilityFunctions;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrdenSesionRutinaRepository ordenSesionRutinaRepository;

    @Autowired
    private DesempenyoSesionRepository desempenyoSesionRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private DesempenyoSerieRepository desempenyoSerieRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @GetMapping("/rutinas")
    public String doRutinas(Model model, HttpSession session) {

        Usuario cliente = this.usuarioRepository.findById(1).orElse(null);
        if(cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        session.setAttribute("cliente_id", cliente.getId());

        List<Rutina> rutinasList = new ArrayList<>(cliente.getRutinasCliente());
        model.addAttribute("rutinas", rutinasList);

        return "cliente/rutinas";
    }

    @GetMapping("/sesiones_rutina")
    public String doSeriesRutinas(@RequestParam("id") Integer rutina_id,
                                  @RequestParam("nombre_rutina") String nombreRutina,
                                  Model model) {

        List<OrdenSesionRutina> ordenSesionRutinas =
                ordenSesionRutinaRepository.findOrdenSesionRutinaByRutinaID(rutina_id);

        model.addAttribute("ordenSesionRutinas", ordenSesionRutinas);
        model.addAttribute("nombre_rutina", nombreRutina);

        return "cliente/sesiones_rutina";
    }

    @GetMapping("/desempenyos_sesion")
    public String doDesempenyoSesion(@RequestParam("id") Integer sesion_id,
                                     Model model, HttpSession session) {
        //Encontrar los desempeños_sesion de aquellas que tengan el rutina_id y cliente_id
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        session.setAttribute("sesion",sesion);

        Integer cliente_id = (Integer) session.getAttribute("cliente_id");
        List<DesempenyoSesion> desempenyoSesiones =
                desempenyoSesionRepository.findByClientIDAndSesionID(sesion_id,cliente_id);

        model.addAttribute("desempenyos", desempenyoSesiones);
        return "cliente/desempenyos_sesion";
    }

    @GetMapping("/desempenyo_sesion")
    public String doResultadosSesion(@RequestParam("id") Integer desempenyo_id,
                                     HttpSession session,
                                     Model model) {
        DesempenyoSesion desempenyoSesion =
                (DesempenyoSesion) desempenyoSesionRepository.findById(desempenyo_id).orElse(null);

        if(desempenyoSesion.getTerminado() == 0){
            //Si no esta terminado se manda a entrenamiento
            return "redirect:/cliente/entrenamiento?id=" + desempenyo_id;
        }
        //Si esta terminado se muestran los resultados.
        Sesion sesion = (Sesion) session.getAttribute("sesion");

        Map<Ejercicio,List<Serie>> sesion_dict = UtilityFunctions.dictFromSerie(sesion.getSeries());
        Map<Ejercicio,List<DesempenyoSerie>> des_dict =
                UtilityFunctions.dictFromDesempenyoSerie(desempenyoSesion.getDesempenyoSeries());


        //ATRIBUTOS FILTRO
        model.addAttribute("filtro",new FiltroSerie());
        model.addAttribute("grupo_muscular",grupoMuscularRepository.findAll());
        model.addAttribute("tipo_ejercicio",tipoEjercicioRepository.findAll());

        //INFORMACION TABLA
        model.addAttribute("desempenyo_sesion_fecha",desempenyoSesion.getFecha().toString());
        model.addAttribute("sesion_dict",sesion_dict);
        model.addAttribute("des_dict",des_dict);

        return "cliente/resultados_sesion";
    }

    @GetMapping("/entrenamiento")
    public String doEntrenamiento(@RequestParam("id") Integer desempenyo_id, Model model){
        DesempenyoSesion desempenyoSesion = desempenyoSesionRepository.findById(desempenyo_id).orElse(null);

        List<DesempenyoSerie> desempenyoSeries = desempenyoSesion.getDesempenyoSeries();
        Map<Ejercicio,List<DesempenyoSerie>> series_dict =
                UtilityFunctions.dictFromDesempenyoSerie(desempenyoSeries);

        model.addAttribute("desempenyo_sesion_id",desempenyo_id);
        model.addAttribute("series_dict",series_dict);

        return "cliente/entrenamiento";
    }

    @PostMapping("/prev_desempenyo")
    public String doPrevDesempenyoSesion(Model model,HttpSession session) {

        Sesion sesion = (Sesion) session.getAttribute("sesion");

        List<Serie> series_list = new ArrayList<>(sesion.getSeries());
        Map<Ejercicio,List<Serie>> sesion_dict = UtilityFunctions.dictFromSerie(series_list);

        model.addAttribute("sesion_dict", sesion_dict);

        return "cliente/prev_entrenamiento";
    }


    @PostMapping("/nuevo_desempenyo_sesion")
    public String doNuevoDesempenyoSesion(Model model,HttpSession session) {
        Sesion sesion = (Sesion) session.getAttribute("sesion");
        Integer cliente_id = (Integer) session.getAttribute("cliente_id");
        Usuario cliente = (Usuario) usuarioRepository.findById(cliente_id).orElse(null);

        List<Serie> series_list = sesion.getSeries();

        DesempenyoSesion des = new DesempenyoSesion();
        des.setFecha(LocalDate.now());
        des.setSesion(sesion);
        des.setUsuario(cliente);
        des.setTerminado((byte) 0);

        desempenyoSesionRepository.saveAndFlush(des);

        for(Serie serie : series_list){
            DesempenyoSerie des_serie = getDesempenyoSerie(serie, des);
            desempenyoSerieRepository.saveAndFlush(des_serie);
        }

        return "redirect:/cliente/entrenamiento?id=" + des.getId();
    }

    @PostMapping("/borrar_serie")
    public String doBorrarSerie(@RequestParam("id") Integer desempenyoSerieId,
                                @RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id) {
        desempenyoSerieRepository.deleteById(desempenyoSerieId);

        return "redirect:/cliente/entrenamiento?id=" + desempenyo_sesion_id;
    }

    @PostMapping("/terminar_entrenamiento")
    public String doTerminarEntrenamiento(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
            HttpSession session) {
        Sesion sesion = (Sesion) session.getAttribute("sesion");
        DesempenyoSesion des = desempenyoSesionRepository.findById(desempenyo_sesion_id).orElse(null);

        des.setTerminado((byte) 1);
        desempenyoSesionRepository.save(des);

        return "redirect:/cliente/desempenyos_sesion?id=" + sesion.getId();
    }

    @PostMapping("/cancelar_entrenamiento")
    public String doCancelarEntrenamiento(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
                                          HttpSession session) {
        Sesion sesion = (Sesion) session.getAttribute("sesion");
        desempenyoSesionRepository.deleteById(desempenyo_sesion_id);

        return "redirect:/cliente/desempenyos_sesion?id=" + sesion.getId();
    }

    @PostMapping("/nueva_serie")
    public String doNuevaSerie(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
                               @RequestParam("ejercicio_id") Integer ejercicio_id,
                               Model model){
        DesempenyoSerie desSerie = new DesempenyoSerie();
        desSerie.setDesempenyoSesion(desempenyoSesionRepository.findById(desempenyo_sesion_id).orElse(null));
        desSerie.setEjercicio(ejercicioRepository.findById(ejercicio_id).orElse(null));
        desempenyoSerieRepository.save(desSerie);

        model.addAttribute("desSerie",desSerie);

        return "cliente/serie";
    }

    @PostMapping("/editar_serie")
    public String doEditarSerie(@RequestParam("id") Integer desempenyo_serie_id, Model model){
        DesempenyoSerie desSerie = desempenyoSerieRepository.findById(desempenyo_serie_id).orElse(null);
        model.addAttribute("desSerie",desSerie);
        return "cliente/serie";
    }

    @PostMapping("/guardar_serie")
    public String doGuardarSerie(@ModelAttribute("desSerie") DesempenyoSerie desempenyoSerie) {
        desempenyoSerieRepository.save(desempenyoSerie);

        return "redirect:/cliente/entrenamiento?id=" + desempenyoSerie.getDesempenyoSesion().getId();
    }

    @GetMapping("/ejercicio")
    public String doEjercicio(@RequestParam("id") Integer ejercicio_id, Model model){
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicio_id).orElse(null);

        model.addAttribute("ejercicio",ejercicio);
        return "cliente/ejercicio";
    }

    @PostMapping("/filtro_series")
    public String DoFiltroSeries(@ModelAttribute("filtro") FiltroSerie filtroString, Model model, HttpSession session){



        return "cliente/resultados_sesion";
    }


    private DesempenyoSerie getDesempenyoSerie(Serie serie, DesempenyoSesion des) {
        DesempenyoSerie des_serie = new DesempenyoSerie();

        des_serie.setDesempenyoSesion(des);
        des_serie.setEjercicio(serie.getEjercicio());
        des_serie.setPeso(serie.getPeso());
        des_serie.setRepeticiones(serie.getRepeticiones());
        des_serie.setDistancia(serie.getDistancia());
        des_serie.setDuracion(serie.getDuracion());

        return des_serie;
    }


    @GetMapping("/dietas")
    public String doDietas() {
        return "cliente/dietas";
    }


}
