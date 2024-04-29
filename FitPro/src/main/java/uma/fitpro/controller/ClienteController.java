package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.DesempenyoSesionRepository;
import uma.fitpro.dao.OrdenSesionRutinaRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.*;
import uma.fitpro.utils.UtilityFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/desempenyo_sesion")
    public String doDesempenyoSesion(@RequestParam("id") Integer sesion_id,
                                     Model model, HttpSession session) {
        //Encontrar los desempeños_sesion de aquellas que tengan el rutina_id y cliente_id
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        if(sesion == null) {
            throw new RuntimeException("Sesion no encontrada");
        }
        session.setAttribute("sesion",sesion);

        Integer cliente_id = (Integer) session.getAttribute("cliente_id");
        List<DesempenyoSesion> desempenyoSesiones =
                desempenyoSesionRepository.findByClientIDAndSesionID(sesion_id,cliente_id);

        model.addAttribute("desempenyos", desempenyoSesiones);
        model.addAttribute("sesion_name", sesion.getNombre());

        return "cliente/desempenyo_sesion";
    }

    @GetMapping("/resultados_sesion")
    public String doResultadosSesion(@RequestParam("id") Integer desempenyo_id, Model model) {

        return "cliente/resultados_sesion";
    }

    @PostMapping("/nuevo_desempenyo")
    public String doNuevoDesempenyo(Model model,HttpSession session) {

        Sesion sesion = (Sesion) session.getAttribute("sesion");
        List<Serie> series_list = new ArrayList<>(sesion.getSeries());

        Map<Ejercicio,List<Serie>> sesion_dict = UtilityFunctions.generateDictionary(series_list);

        model.addAttribute("sesion_dict", sesion_dict);
        return "cliente/nuevo_desempenyo";
    }

    @PostMapping("/nuevo_entrenamiento")
    public String doNuevoEntrenamiento(Model model, HttpSession session){

        Sesion sesion = (Sesion) session.getAttribute("sesion");
        List<Serie> series_list = new ArrayList<>(sesion.getSeries());

        Map<Ejercicio,List<Serie>> sesion_dict = UtilityFunctions.generateDictionary(series_list);

        return null;
    }

    @GetMapping("/dietas")
    public String doDietas() {
        return "cliente/dietas";
    }


}
