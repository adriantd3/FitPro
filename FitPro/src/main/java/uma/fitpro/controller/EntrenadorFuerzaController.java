package uma.fitpro.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dto.*;
import uma.fitpro.service.*;
import uma.fitpro.ui.FiltroRutina;
import java.util.*;

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
    private UsuarioService usuarioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private SesionService sesionService;
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private SerieService serieService;
    @Autowired
    private DesempenyoSesionService desempenyoSesionService;
    @Autowired
    private DesempenyoSerieService desempenyoSerieService;

    // ----------- PAGINA INICIAL -----------------
    @PostMapping("/")
    public String doEntrenadorFuerzaHome() {
        return "/entrenador_fuerza/home";
    }

    @GetMapping("/")
    public String doHome() {
        return "/entrenador_fuerza/home";
    }

    // ------------- PAGINA LISTA DE CLIENTES ---------------
    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        UsuarioDTO entrenador = (UsuarioDTO)session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesEntrenador(entrenador);
        model.addAttribute("clientes", clientes);

        return "/entrenador_fuerza/clientes";
    }


    // ----------- PAGINA DE RUTINAS ---------------
    @GetMapping("/crud-rutina")
    public String doCrudRutina(@RequestParam("cliente")@Nullable Integer cliente_id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        List<RutinaDTO> rutinas;
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        if (cliente_id != null) {
            UsuarioDTO cliente = usuarioService.findById(cliente_id);
            session.setAttribute("cliente", cliente);
            List<Integer> rutinasIdCliente  = cliente.getRutinasCliente();
            if(rutinasIdCliente.isEmpty()) rutinasIdCliente.add(-1);
            rutinas = rutinaService.getRutinasSinAsignarACliente(rutinasIdCliente);
            //rutinas = rutinaService.getRutinasSinAsignarACliente(cliente.getRutinasCliente());
            List<RutinaDTO> rutinasCliente = rutinaService.getRutinasCliente(cliente);
            session.setAttribute("rutinasCliente", rutinasCliente);
        }
        else{
            rutinas = rutinaService.getRutinasEntrenador(entrenador);
            session.setAttribute("cliente", null);
        }
        model.addAttribute("rutinas", rutinas);

        FiltroRutina filtroRutina = new FiltroRutina();
        model.addAttribute("filtroRutina", filtroRutina);
        return "/entrenador_fuerza/crud-rutina";
    }

    @PostMapping("/crear-rutina")
    public String doNuevaRutina(@RequestParam("nombreRutina") String nombreRutina, HttpSession session){
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");

        rutinaService.nuevaRutina(entrenador, nombreRutina);

        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
        if(cliente != null){
            return "redirect:/entrenador_fuerza/crud-rutina?cliente=" + cliente.getId();
        }else{
            return "redirect:/entrenador_fuerza/crud-rutina";
        }
    }

    @GetMapping("guardar-rutina")
    public String doGuardarRutina(@RequestParam("rutina") Integer rutina_id, HttpSession session){
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        RutinaDTO rutinaDTO = rutinaService.buscarRutina(rutina_id);
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");

        usuarioService.asignarRutinaCliente(cliente, rutinaDTO);

        return "redirect:/entrenador_fuerza/crud-rutina?cliente=" + cliente.getId();

    }

    @PostMapping("/crud-rutina/filtro")
    public String doFiltroListaRutinas(@ModelAttribute FiltroRutina filtroRutina,
                                       @RequestParam("cliente") Integer cliente_id,
                                       Model model, HttpSession session){
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        List<RutinaDTO> rutinas;
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        if (cliente_id != -1) {
            UsuarioDTO cliente = usuarioService.findById(cliente_id);
            session.setAttribute("cliente", cliente);
            rutinas = rutinaService.getRutinasSinAsignarACliente(cliente.getRutinasCliente(), filtroRutina);
            List<RutinaDTO> rutinasCliente = rutinaService.getRutinasCliente(cliente, filtroRutina);
            session.setAttribute("rutinasCliente", rutinasCliente);
        }
        else{
            rutinas = rutinaService.getRutinasEntrenador(entrenador, filtroRutina);
            session.setAttribute("cliente", null);
        }
        model.addAttribute("rutinas", rutinas);

        model.addAttribute("filtroRutina", filtroRutina);
        return "/entrenador_fuerza/crud-rutina";
    }


    // ----------- PAGINA DE UNA RUTINA -------------------
    @GetMapping("/rutina")
    public String doRutina(@RequestParam("rutina") Integer rutina_id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        RutinaDTO rutina = rutinaService.buscarRutina(rutina_id);
        session.setAttribute("rutina", rutina);

        Map<Integer, SesionDTO> sesiones = rutinaService.getDiasSesion(rutina);

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("sesionesTotales", sesionService.getSesiones());
        return "/entrenador_fuerza/rutina";
    }

    @GetMapping("/borrar-rutina")
    public String doBorrarRutina(@RequestParam("rutina") Integer rutina_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        rutinaService.borrarRutina(rutina_id);
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
        if(cliente != null){
            return "redirect:/entrenador_fuerza/crud-rutina?cliente=" + cliente.getId();
        }else{
            return "redirect:/entrenador_fuerza/crud-rutina";
        }
    }

    @GetMapping("/desasignar-rutina")
    public String doDesasignar(@RequestParam("rutina") Integer rutina_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        RutinaDTO rutina = rutinaService.buscarRutina(rutina_id);
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");

        usuarioService.borrarRutinaCliente(cliente, rutina);

        return "redirect:/entrenador_fuerza/crud-rutina?cliente=" + cliente.getId();
    }

    @PostMapping("/asignar-sesion")
    public String doAsignarSesion(@RequestParam("sesionNueva") Integer sesion_id,
                                  @RequestParam("dia") Integer dia,
                                  HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SesionDTO sesion = sesionService.buscarSesion(sesion_id);
        RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
        SesionDTO sesionAnterior = sesionService.getSesionByDia(rutina,dia);
        rutinaService.asociarDiaSesion(rutina,dia, sesion, sesionAnterior);

        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();
    }

    @PostMapping("/crear-sesion")
    public String doCrearSesion(@RequestParam("nombreSesion") String nombreSesion, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        sesionService.nuevaSesion(nombreSesion);
        RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");

        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();
    }

    @PostMapping("/rutina/filtro")
    public String doFiltrarListaSesiones(@RequestParam("nombre") String nombre, HttpSession session, Model model) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
        Map<Integer, SesionDTO> sesiones = rutinaService.getDiasSesion(rutina);

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("sesionesTotales", sesionService.filtrarSesiones(nombre));

        return "entrenador_fuerza/rutina";
    }


    // ---------- PAGINA DE SESION ------------------
    @GetMapping("/sesion")
    public String doSesion(@RequestParam("sesion")@Nullable Integer sesion_id,
                           Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SesionDTO sesion;
        if (sesion_id != null) {
            sesion = sesionService.buscarSesion(sesion_id);
            model.addAttribute("sesion", sesion);

            Map<EjercicioDTO, List<SerieDTO>> tablas = serieService.buscarSeriesDictionary(sesion.getSeries());
            model.addAttribute("tablas", tablas);
        }
        //System.out.printf(String.valueOf(model.getAttribute("serie") == null));

        return "/entrenador_fuerza/sesion";
    }


    @GetMapping("borrar-sesion")
    public String doBorrarSesion(@RequestParam("sesion") Integer sesion_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        sesionService.borrarSesion(sesion_id);
        RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");

        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();
    }

    @GetMapping("/asignar-ejercicio")
    public String doAsignarEjercicio(@ModelAttribute("sesion") Integer sesion_id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SesionDTO sesion = sesionService.buscarSesion(sesion_id);
        model.addAttribute("sesion", sesion);

        List<EjercicioDTO> ejercicios = ejercicioService.getEjercicios();
        model.addAttribute("ejercicios", ejercicios);

        SerieDTO serie = new SerieDTO();
        model.addAttribute("serie", serie);

        return "/entrenador_fuerza/asignar-ejercicio";
    }

    @GetMapping("editar-serie")
    public String doEditarSerie(@RequestParam("serie") Integer serie_id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SerieDTO serie = serieService.buscarSerie(serie_id);
        SesionDTO sesion = sesionService.buscarSesion(serie.getSesion());
        String nombreEjercicio = ejercicioService.buscarEjercicio(serie.getEjercicio()).getNombre();
        model.addAttribute("serie", serie);
        model.addAttribute("sesion", sesion);
        model.addAttribute("nombreEjercicio", nombreEjercicio);

        Map<EjercicioDTO, List<SerieDTO>> tablas = serieService.buscarSeriesDictionary(sesion.getSeries());
        model.addAttribute("tablas", tablas);
        return "/entrenador_fuerza/sesion";
    }

    @PostMapping("/guardar-serie")
    public String doGuardarSerie(@ModelAttribute("serie") SerieDTO serie, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        serieService.guardarSerie(serie, serie.getMetrica1().toString(), serie.getMetrica2().toString());
        return "redirect:/entrenador_fuerza/sesion?sesion=" + serie.getSesion();
    }

    @GetMapping("eliminar-serie")
    public String doEliminarSerie(@RequestParam("serie") Integer serie_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SerieDTO serie = serieService.buscarSerie(serie_id);
        serieService.borrarSerie(serie);

        return "redirect:/entrenador_fuerza/sesion?sesion=" + serie.getSesion();
    }

    @GetMapping("anyadir-serie")
    public String doAnyadirSerie(@RequestParam("sesion") Integer sesion_id ,
                                 @RequestParam("ejercicio") Integer ejercicio_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        SesionDTO sesion = sesionService.buscarSesion(sesion_id);
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(ejercicio_id);
        Integer serie_id = serieService.crearSerie(ejercicio, sesion, "0", "0");
        return "redirect:/entrenador_fuerza/editar-serie?serie=" + serie_id;
    }

    @GetMapping("/desasignar-sesion")
    public String doDesasignarSesion(@RequestParam("sesion") Integer sesion_id, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
        Integer dia = sesionService.getDiaBySesion(sesion_id, rutina);
        SesionDTO sesion = sesionService.buscarSesion(sesion_id);
        rutinaService.asociarDiaSesion(rutina, dia, null, sesion);

        return "redirect:/entrenador_fuerza/rutina?rutina=" + rutina.getId();
    }


    // -------- LISTA DE EJERCICIOS ---------------
    @PostMapping("/guardar-ejercicio")
    public String doGuardarEjercicio(@RequestParam("ejercicio") Integer ejercicioId, @RequestParam("sesion") Integer sesionId,
                                     HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        EjercicioDTO ejercicioDTO = ejercicioService.buscarEjercicio(ejercicioId);
        SesionDTO sesionDTO = sesionService.buscarSesion(sesionId);

        Integer serieId = serieService.crearSerie(ejercicioDTO, sesionDTO, "0", "0");

        return "redirect:/entrenador_fuerza/editar-serie?serie=" + serieId;
    }

    // --------- PAGINA DE EJERCICIO ---------------
    @GetMapping("ejercicio/{id}")
    public String doEjercicio(@PathVariable Integer id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        EjercicioDTO ejercicioDTO = ejercicioService.buscarEjercicio(id);
        model.addAttribute("ejercicio", ejercicioDTO);

        return "cliente/rutinas/ejercicio";
    }


    //---------------- PAGINAS DE SEGUIMIENTO -----------------------
    @GetMapping("seguimiento")
    public String doSeguimiento(Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
        List<DesempenyoSesionDTO> desempenyosSesiones = desempenyoSesionService.getDesempenyosSesionesByCliente(cliente);
        model.addAttribute("desempenyoSesiones", desempenyosSesiones);
        return "/entrenador_fuerza/seguimiento";
    }

    @GetMapping("desempenyos-sesion/{id}")
    public String doDesempenyoSesion(@PathVariable Integer id, Model model, HttpSession session) {
        if(checkUsuarioYRol(session)){
            return "redirect:/";
        }

        DesempenyoSesionDTO desempenyoSesion = desempenyoSesionService.buscarDesempenyoSesion(id);
        SesionDTO sesionDTO = sesionService.buscarSesion(desempenyoSesion.getIdSesion());
        Map<EjercicioDTO, List<SerieDTO>> tablasEsperadas = serieService.buscarSeriesDictionary(sesionDTO.getSeries());
        Map<EjercicioDTO, List<DesempenyoSerieDTO>> tablas = desempenyoSerieService.buscarDesempenyoSeriesDictionary(desempenyoSesion.getDesempenyoSeries());
        model.addAttribute("tablas", tablas);
        model.addAttribute("tablasEsperadas", tablasEsperadas);

        model.addAttribute("desempenyoSesion", desempenyoSesion);
        return "/entrenador_fuerza/desempenyos-sesion";
    }

    private boolean checkUsuarioYRol(HttpSession session) {
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        return usuario == null || usuario.getRol().getId() != 2;
    }
}
