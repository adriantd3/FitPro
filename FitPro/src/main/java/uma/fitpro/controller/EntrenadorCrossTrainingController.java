//AUTOR: Ezequiel Sánchez García (100%)
package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dto.*;
import uma.fitpro.entity.*;
import uma.fitpro.service.*;
import java.util.*;

import static uma.fitpro.utils.UtilityFunctions.getDiasSemana;

/**
 * @author Ezequiel Sánchez García
 */

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected RutinaService rutinaService;

    @Autowired
    protected SesionService sesionService;

    @Autowired
    protected SerieService serieService;

    @Autowired
    protected EjercicioService ejercicioService;

    @Autowired
    protected DesempenyoSesionService desempenyoSesionService;

    @Autowired
    protected DesempenyoSerieService desempenyoSerieService;

    // --------------------------- HOME ---------------------------

    @GetMapping("")
    public String doHome(Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        return "entrenador_cross_training/home";
    }
    
    // --------------------------- CLIENTES ---------------------------

    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesEntrenador(entrenador);
        model.addAttribute("clientes", clientes);

        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/filtrar_clientes")
    public String doFiltrarClientes(@RequestParam("nombre") String nombre,
                                    @RequestParam("edad") String edad,
                                    @RequestParam("altura") String altura,
                                    @RequestParam("peso") String peso,
                                    Model model,HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.filtrarClientes(entrenador, nombre, edad, altura, peso);
        model.addAttribute("clientes", clientes);

        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/rutinas_cliente")
    public String doRutinasCliente(Model model, @RequestParam("id") Integer id_cliente,
                                   HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO cliente = usuarioService.findById(id_cliente);
        List<RutinaDTO> rutinas = rutinaService.getRutinasCliente(cliente);
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        List<RutinaDTO> todasLasRutinas = rutinaService.getRestantesRutinasByEntrenador(entrenador, rutinas);

        model.addAttribute("todasLasRutinas", todasLasRutinas);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("cliente", cliente);

        return "entrenador_cross_training/rutinas_cliente";
    }

    @PostMapping("/asignar_rutina_cliente")
    public String doAsignarRutinaCliente(@RequestParam("rutina") Integer id_rutina,
                                         @RequestParam("cliente") Integer id_cliente,
                                         HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        RutinaDTO rutina = rutinaService.buscarRutina(id_rutina);
        UsuarioDTO cliente = usuarioService.findById(id_cliente);
        usuarioService.asignarRutinaCliente(cliente, rutina);

        return "redirect:/entrenador_cross_training/rutinas_cliente?id=" + cliente.getId();
    }

    @PostMapping("/borrar_rutina_cliente")
    public String doBorrarRutinaCliente(@RequestParam("rutina") Integer id_rutina,
                                        @RequestParam("cliente") Integer id_cliente,
                                        HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        RutinaDTO rutina = rutinaService.buscarRutina(id_rutina);
        UsuarioDTO cliente = usuarioService.findById(id_cliente);
        usuarioService.borrarRutinaCliente(cliente, rutina);

        return "redirect:/entrenador_cross_training/rutinas_cliente?id=" + cliente.getId();
    }

    @GetMapping("/seguimiento_cliente")
    public String doSeguimientoCliente(@RequestParam("cliente") Integer id_cliente,
                                       @RequestParam("rutina") Integer id_rutina,
                                       Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO cliente = usuarioService.findById(id_cliente);
        RutinaDTO rutina = rutinaService.buscarRutina(id_rutina);
        HashMap<OrdenSesionRutinaDTO,List<DesempenyoSesionDTO>> seguimientoRutina = desempenyoSesionService.seguimientoRutina(cliente, rutina);
        Map<Integer,String> diasSemana = getDiasSemana();

        session.setAttribute("cliente", cliente);
        session.setAttribute("rutina", rutina);
        model.addAttribute("seguimientoRutina", seguimientoRutina);
        model.addAttribute("cliente", cliente);
        model.addAttribute("rutina", rutina);
        model.addAttribute("diasSemana", diasSemana);
        return "entrenador_cross_training/seguimiento_cliente";
    }

    @GetMapping("seguimiento_sesion")
    public String doSeguimientoSesion(@RequestParam("desempenyo_sesion") Integer id_desempenyo_sesion,
                                       Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        DesempenyoSesionDTO desempenyoSesion =
                desempenyoSesionService.buscarDesempenyoSesion(id_desempenyo_sesion);
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> mapaDesempenyoSesion =
                desempenyoSerieService.buscarDesempenyoSeriesDictionary(desempenyoSesion.getDesempenyoSeries());

        SesionDTO sesionOriginal = sesionService.buscarSesion(desempenyoSesion.getIdSesion());
        Map<EjercicioDTO, List<SerieDTO>> mapaSesionOriginal = sesionService.getEjercicioYSeries(sesionOriginal);

        model.addAttribute("mapaDesempenyoSesion", mapaDesempenyoSesion);
        model.addAttribute("mapaSesionOriginal", mapaSesionOriginal);
        model.addAttribute("desempenyoSesion", desempenyoSesion);
        return "entrenador_cross_training/seguimiento_sesion";
    }

    // --------------------------- RUTINAS ---------------------------

    @GetMapping("/rutinas")
    public String doRutinas(Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        List<RutinaDTO> rutinas = rutinaService.getRutinasEntrenador(entrenador);

        model.addAttribute("rutinas", rutinas);

        return "entrenador_cross_training/rutinas";
    }

    @GetMapping("/filtrar_rutinas")
    public String doFiltrarRutinas(@RequestParam("nombre") String nombre,
                                   @RequestParam("fecha") String fecha,
                                   Model model,HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        List<RutinaDTO> rutinas = rutinaService.filtrarRutinas(entrenador.getId(), nombre, fecha);

        model.addAttribute("rutinas", rutinas);

        return "entrenador_cross_training/rutinas";
    }


    @PostMapping("/borrar_rutina")
    public String doBorrarRutina(@RequestParam("id") Integer id_rutina, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        rutinaService.borrarRutina(id_rutina);

        return "redirect:/entrenador_cross_training/rutinas";
    }

    @PostMapping("/nueva_rutina")
    public String doNuevaRutina(@RequestParam("nombre") String nombre, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
        rutinaService.nuevaRutina(entrenador, nombre);

        return "redirect:/entrenador_cross_training/rutinas";
    }

    @GetMapping("/editar_rutina")
    public String doEditarRutina(@RequestParam("id") Integer id_rutina, Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        RutinaDTO rutina = rutinaService.buscarRutina(id_rutina);
        Map<Integer, SesionDTO> diaSesion = rutinaService.getDiasSesion(rutina);
        Map<Integer, String> diasSemana = getDiasSemana();
        List<SesionDTO> sesiones = sesionService.getSesiones();

        model.addAttribute("diaSesion", diaSesion);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("rutina", rutina);
        model.addAttribute("sesiones", sesiones);

        return "entrenador_cross_training/editar_rutina";
    }

    @PostMapping("/asociar_dia_sesion")
    public String doAsociarDiaSesion(@RequestParam("rutina") Integer id_rutina,
                                     @RequestParam("dia") Integer dia,
                                     @RequestParam("nueva_sesion") Integer nueva_sesion_id,
                                     @RequestParam("antigua_sesion") Integer antigua_sesion_id,
                                     HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        RutinaDTO rutina = rutinaService.buscarRutina(id_rutina);
        SesionDTO nueva_sesion = sesionService.buscarSesion(nueva_sesion_id);
        SesionDTO antigua_sesion = sesionService.buscarSesion(antigua_sesion_id);
        rutinaService.asociarDiaSesion(rutina, dia, nueva_sesion, antigua_sesion);

        return "redirect:/entrenador_cross_training/editar_rutina?id=" + rutina.getId();
    }

    // --------------------------- SESIONES ---------------------------

    @GetMapping("/sesiones")
    public String doSesiones(Model modelo, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        List<SesionDTO> sesiones = sesionService.getSesiones();
        modelo.addAttribute("sesiones", sesiones);

        return "entrenador_cross_training/sesiones";
    }

    @GetMapping("/filtrar_sesiones")
    public String doFiltrarSesiones(@RequestParam("nombre") String nombre,Model model,HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        String str = "redirect:/entrenador_cross_training/sesiones";
        if (!nombre.isEmpty()){
            List<SesionDTO> sesiones = sesionService.filtrarSesiones(nombre);
            model.addAttribute("sesiones", sesiones);
            str = "entrenador_cross_training/sesiones";
        }

        return str;
    }

    @PostMapping("/borrar_sesion")
    public String doBorrarSesion(@RequestParam("id") Integer id_sesion, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        sesionService.borrarSesion(id_sesion);

        return "redirect:/entrenador_cross_training/sesiones";
    }

    @GetMapping("/sesion")
    public String doSesion(@RequestParam("id") Integer id_sesion, Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        Map<EjercicioDTO, List<SerieDTO>> mapa = sesionService.getEjercicioYSeries(sesion);

        model.addAttribute("sesion", sesion);
        model.addAttribute("mapa", mapa);

        return "entrenador_cross_training/sesion";
    }


    @PostMapping("/nueva_sesion")
    public String doNuevaSesion(@RequestParam("nombre") String nombre, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        sesionService.nuevaSesion(nombre);

        return "redirect:/entrenador_cross_training/sesiones";
    }


    // --------------------------- EJERCICIOS ---------------------------

    @GetMapping("/ejercicios")
    public String doEjercicios(@RequestParam("ejercicio") String nombre_ejercicio,
                               @RequestParam("musculo") String musculo,
                               @RequestParam("tipo") String tipo,
                               @RequestParam(value = "aplicar", required = false) Boolean aplicar,
                               Model modelo,
                               HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        List<EjercicioDTO> ejercicios;
        if (aplicar!=null){ // Filtro por nombre, musculo y tipo
            ejercicios = ejercicioService.filtrarEjercicioPorNombreMusculoYTipo(nombre_ejercicio, musculo, tipo);
        }else { // Filtro por nombre solo
            ejercicios = ejercicioService.filtrarEjercicioPorNombre(nombre_ejercicio);
        }

        List<TipoEjercicioDTO> tipos = ejercicioService.listarTiposEjercicio();
        List<GrupoMuscularDTO> grupos = ejercicioService.listarGruposMusculares();

        modelo.addAttribute("ejercicios", ejercicios);
        modelo.addAttribute("tipos", tipos);
        modelo.addAttribute("grupos", grupos);

        return "entrenador_cross_training/ejercicios";
    }

    @PostMapping("/anyadir_ejercicio")
    public String doAnyadirEjercicio(@RequestParam("sesion") Integer id_sesion,
                                     @RequestParam("ejercicio") Integer id_ejercicio,
                                     Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id_ejercicio);
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        Map<EjercicioDTO, List<SerieDTO>> mapa = sesionService.getEjercicioYSeries(sesion);
        ejercicioService.anyadirEjercicio(mapa, ejercicio);

        model.addAttribute("sesion", sesion);
        model.addAttribute("mapa", mapa);

        return "entrenador_cross_training/sesion";
    }

    @GetMapping("/ejercicio")
    public String doEjercicio(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        EjercicioDTO ejercicioDTO = ejercicioService.buscarEjercicio(id);
        model.addAttribute("ejercicio", ejercicioDTO);

        return "cliente/rutinas/ejercicio";
    }

    // --------------------------- SERIES ---------------------------

    @GetMapping("/anyadir_serie")
    public String doAnyadirSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("ejercicio") Integer id_ejercicio,
                                 Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id_ejercicio);

        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("sesion", sesion);

        return "entrenador_cross_training/anyadir_serie";
    }

    @PostMapping("/crear_serie")
    public String doCrearSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("ejercicio") Integer id_ejercicio,
                                 @RequestParam("param1") String parametro1,
                                 @RequestParam("param2") String parametro2,
                               HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id_ejercicio);
        serieService.crearSerie(ejercicio, sesion, parametro1, parametro2);

        return "redirect:/entrenador_cross_training/sesion?id=" + sesion.getId();
    }

    @GetMapping("editar_serie")
    public String doEditarSerie(@RequestParam("sesion") Integer id_sesion,
                                @RequestParam("serie") Integer id_serie,
                                Model model, HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SerieDTO serie = serieService.buscarSerie(id_serie);
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(serie.getEjercicio());

        model.addAttribute("sesion", sesion);
        model.addAttribute("serie", serie);
        model.addAttribute("ejercicio", ejercicio);

        return "entrenador_cross_training/editar_serie";
    }

    @PostMapping("/guardar_serie")
    public String doGuardarSerie(@RequestParam("sesion") Integer id_sesion,
                                 @RequestParam("serie") Integer id_serie,
                                 @RequestParam("param1") String parametro1,
                                 @RequestParam("param2") String parametro2,
                                 HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SesionDTO sesion = sesionService.buscarSesion(id_sesion);
        SerieDTO serie = serieService.buscarSerie(id_serie);
        serieService.guardarSerie(serie, parametro1, parametro2);

        return "redirect:/entrenador_cross_training/sesion?id=" + sesion.getId();
    }

    @PostMapping("/borrar_serie")
    public String doBorrarSerie(@RequestParam("sesion") Sesion sesion,
                                @RequestParam("serie") Integer id_serie,
                                HttpSession session){
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        SerieDTO serie = serieService.buscarSerie(id_serie);
        serieService.borrarSerie(serie);

        return "redirect:/entrenador_cross_training/sesion?id=" +sesion.getId();
    }

    // --------------------------- FUNCIONES AUXILIARES ---------------------------

    // Comprueba que el usuario esté autenticado y además tenga el rol de entrenador cross-training
    private boolean estaAutenticado(HttpSession session){
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getRol().getId() == 3;
    }

}
