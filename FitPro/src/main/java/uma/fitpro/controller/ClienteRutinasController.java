package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dto.*;
import uma.fitpro.service.*;
import uma.fitpro.ui.FiltroSerie;
import uma.fitpro.utils.SortedList;

import java.util.*;

@Controller
@RequestMapping("/cliente/rutinas")
public class ClienteRutinasController {

    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private SesionService sesionService;

    @Autowired
    private DesempenyoSesionService desempenyoSesionService;

    @Autowired
    private SerieService serieService;

    @Autowired
    private DesempenyoSerieService desempenyoSerieService;

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping("/")
    public String doRutinas(Model model, HttpSession session) {
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
        if(cliente == null){
            return "redirect:/";
        }

        List<RutinaDTO> rutinasList = rutinaService.buscarRutinas(cliente.getRutinasCliente());
        model.addAttribute("rutinas", rutinasList);

        return "cliente/rutinas/rutinas";
    }

    @GetMapping("/sesiones_rutina")
    public String doSeriesRutinas(@RequestParam("id") Integer rutina_id,
                                  Model model,
                                  HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        RutinaDTO rutina = rutinaService.buscarRutina(rutina_id);
        model.addAttribute("rutina", rutina);

        return "cliente/rutinas/sesiones_rutina";
    }

    @GetMapping("/desempenyos_sesion")
    public String doDesempenyoSesion(@RequestParam("id") Integer sesion_id,
                                     Model model, HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        //Encontrar los desempeños_sesion de aquellas que tengan el rutina_id y cliente_id
        SesionDTO sesion = sesionService.buscarSesion(sesion_id);
        Integer client_id = ((UsuarioDTO) session.getAttribute("user")).getId();
        List<DesempenyoSesionDTO> desempenyoSesiones =
                desempenyoSesionService.buscarDesempenyosSesionPorClienteYSesion(client_id,sesion_id);

        session.setAttribute("sesion",sesion);
        model.addAttribute("desempenyos", desempenyoSesiones);

        return "cliente/rutinas/desempenyos_sesion";
    }

    @GetMapping("/resultados_sesion")
    public String doResultadosSesion(@RequestParam("id") Integer desempenyo_id,
                                     HttpSession session,
                                     Model model) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        DesempenyoSesionDTO desempenyoSesion =
                desempenyoSesionService.buscarDesempenyoSesion(desempenyo_id);
        if(!desempenyoSesion.isTerminado()){
            //Si no esta terminado se manda a entrenamiento
            return "redirect:/cliente/rutinas/entrenamiento?id=" + desempenyo_id;
        }

        //Si esta terminado se muestran los resultados.
        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
        Map<EjercicioDTO, SortedList<SerieDTO>> sesion_dict = serieService.buscarSeriesDictionary(sesion.getSeries());
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> des_dict =
                desempenyoSerieService.buscarDesempenyoSeriesDictionary(desempenyoSesion.getDesempenyoSeries());


        //ATRIBUTOS FILTRO
        model.addAttribute("filtro",new FiltroSerie(sesion.getId(),desempenyo_id));
        model.addAttribute("grupo_muscular",ejercicioService.listarGruposMusculares());
        model.addAttribute("tipo_ejercicio",ejercicioService.listarTiposEjercicio());

        //INFORMACION TABLA
        model.addAttribute("desempenyo_sesion",desempenyoSesion);
        model.addAttribute("sesion_dict",sesion_dict);
        model.addAttribute("des_dict",des_dict);

        return "cliente/rutinas/resultados_sesion";
    }

    @GetMapping("/entrenamiento")
    public String doEntrenamiento(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        DesempenyoSesionDTO desempenyoSesion = desempenyoSesionService.buscarDesempenyoSesion(desempenyo_id);
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> series_dict =
                desempenyoSerieService.buscarDesempenyoSeriesDictionary(desempenyoSesion.getDesempenyoSeries());

        model.addAttribute("desempenyo_sesion",desempenyoSesion);
        model.addAttribute("series_dict",series_dict);

        return "cliente/rutinas/entrenamiento";
    }

    @PostMapping("/prev_desempenyo")
    public String doPrevDesempenyoSesion(Model model,HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
        Map<EjercicioDTO,SortedList<SerieDTO>> sesion_dict = serieService.buscarSeriesDictionary(sesion.getSeries());

        model.addAttribute("sesion_dict", sesion_dict);

        return "cliente/rutinas/prev_entrenamiento";
    }

    @PostMapping("/nuevo_desempenyo_sesion")
    public String doNuevoDesempenyoSesion(Model model,HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");

        DesempenyoSesionDTO desempenyoSesion =
                desempenyoSesionService.nuevoDesempenyoSesion(sesion.getId(),cliente.getId());
        //PROBAR A QUITAR ESO, YO CREO QUE NO HACE FALTA
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> series_dict =
                desempenyoSerieService.buscarDesempenyoSeriesDictionary(desempenyoSesion.getDesempenyoSeries());
        model.addAttribute("series_dict",series_dict);

        return "redirect:/cliente/rutinas/entrenamiento?id=" + desempenyoSesion.getId();
    }

    @PostMapping("/terminar_entrenamiento")
    public String doTerminarEntrenamiento(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
            HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
        desempenyoSesionService.terminarDesempenyoSesion(desempenyo_sesion_id);

        return "redirect:/cliente/rutinas/desempenyos_sesion?id=" + sesion.getId();
    }

    @PostMapping("/cancelar_entrenamiento")
    public String doCancelarEntrenamiento(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
                                          HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
        desempenyoSesionService.borrarDesempenyoSesion(desempenyo_sesion_id);

        return "redirect:/cliente/rutinas/desempenyos_sesion?id=" + sesion.getId();
    }

    @PostMapping("/nueva_serie")
    public String doNuevaSerie(@RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
                               @RequestParam("ejercicio_id") Integer ejercicio_id,
                               Model model, HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        DesempenyoSerieDTO desSerie = desempenyoSerieService.nuevoDesempenyoSerie(desempenyo_sesion_id,ejercicio_id);
        EjercicioDTO ejercicioSerie = ejercicioService.buscarEjercicio(desSerie.getEjercicio());

        model.addAttribute("desSerie",desSerie);
        model.addAttribute("tipo_ejercicio",ejercicioSerie.getTipo().getId());

        return "cliente/rutinas/serie";
    }

    @PostMapping("/borrar_serie")
    public String doBorrarSerie(@RequestParam("id") Integer desempenyoSerieId,
                                @RequestParam("desempenyo_sesion_id") Integer desempenyo_sesion_id,
                                HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }
        desempenyoSerieService.borrarDesempenyoSerie(desempenyoSerieId);

        return "redirect:/cliente/rutinas/entrenamiento?id=" + desempenyo_sesion_id;
    }

    @PostMapping("/editar_serie")
    public String doEditarSerie(@RequestParam("id") Integer desempenyo_serie_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        DesempenyoSerieDTO desSerie = desempenyoSerieService.buscarDesempenyoSerie(desempenyo_serie_id);
        EjercicioDTO ejercicioSerie = ejercicioService.buscarEjercicio(desSerie.getEjercicio());

        model.addAttribute("desSerie",desSerie);
        model.addAttribute("tipo_ejercicio",ejercicioSerie.getTipo().getId());
        return "cliente/rutinas/serie";
    }

    @PostMapping("/guardar_serie")
    public String doGuardarSerie(@ModelAttribute("desSerie") DesempenyoSerieDTO desempenyoSerie, HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        desempenyoSerieService.guardarDesempenyoSerie(desempenyoSerie);
        return "redirect:/cliente/rutinas/entrenamiento?id=" + desempenyoSerie.getDesempenyoSesion();
    }

    @GetMapping("/ejercicio")
    public String doEjercicio(@RequestParam("id") Integer ejercicio_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(ejercicio_id);
        model.addAttribute("ejercicio",ejercicio);
        return "cliente/rutinas/ejercicio";
    }

    @PostMapping("/filtro_series")
    public String DoFiltroSeries(@ModelAttribute("filtro") FiltroSerie filtro, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("sesion") == null){
            return "redirect:/";
        }

        DesempenyoSesionDTO desempenyoSesion =
                desempenyoSesionService.buscarDesempenyoSesion(filtro.getDesSesionId());
        SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");

        Map<EjercicioDTO, SortedList<SerieDTO>> sesion_dict = serieService.filtroBuscarSeriesDictionary(filtro);
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> des_dict =
                desempenyoSerieService.filtroBuscarDesempenyoSeriesDictionary(filtro);


        //ATRIBUTOS FILTRO
        model.addAttribute("filtro",filtro);
        model.addAttribute("grupo_muscular",ejercicioService.listarGruposMusculares());
        model.addAttribute("tipo_ejercicio",ejercicioService.listarTiposEjercicio());

        //INFORMACION TABLA
        model.addAttribute("desempenyo_sesion",desempenyoSesion);
        model.addAttribute("sesion_dict",sesion_dict);
        model.addAttribute("des_dict",des_dict);

        return "cliente/rutinas/resultados_sesion";
    }

}
