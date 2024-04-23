package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.OrdenSesionRutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.OrdenSesionRutina;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrdenSesionRutinaRepository ordenSesionRutinaRepository;

    @GetMapping("/rutinas")
    public String doRutinas(Model model, HttpSession session) {

        Usuario cliente = this.usuarioRepository.findById(1).orElse(null);
        session.setAttribute("cliente", cliente);

        List<Rutina> rutinasList = new ArrayList<>(cliente.getRutinasCliente());
        model.addAttribute("rutinas", rutinasList);

        return "cliente/rutinas";
    }

    @GetMapping("/series_rutina")
    public String doSeriesRutinas(@RequestParam("id") Integer id, Model model) {

        List<OrdenSesionRutina> ordenSesionRutinas = ordenSesionRutinaRepository.findOrdenSesionRutinaByRutinaID(id);

        //ordenSesionRutinas.sort((o1, o2) -> o1.getId().getOrden() < o2.getId().getOrden() ? -1 : 1);
        model.addAttribute("ordenSesionRutinas", ordenSesionRutinas);

        return "cliente/series_rutina";
    }

    @GetMapping("/dietas")
    public String doDietas() {
        return "cliente/dietas";
    }


}
