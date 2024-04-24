package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Usuario;

import java.util.List;
import java.util.Set;

//////////////////////////////////////////////////////
/////////        ENTRENADOR FUERZA           /////////
//////////////////////////////////////////////////////

/**
 * @author Victor Perez Armenta
 */
@Controller
@RequestMapping("/entrenador-fuerza")
public class EntrenadorFuerzaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/index")
    public String doEntrenadorFuerzaHome(@RequestParam String user, @RequestParam String password, Model model) {

        return "/entrenador-fuerza/index";
    }

    @GetMapping("/index")
    public String goHome() {
        return "/entrenador-fuerza/index";
    }

    @GetMapping("/crud-rutina")
    public String goToCrudRutina() {
        return "/entrenador-fuerza/crud-rutina";
    }

    @GetMapping("/clientes")
    public String goToClientes(@RequestParam("entrenador") Integer entrenador_id, Model model, HttpSession session) {

        List<Usuario> USUARIOS = usuarioRepository.findAll();

        Usuario entrenador = (Usuario)session.getAttribute("entrenador");
        Set<Usuario> clientes = entrenador.getClientesEntrenador();
        model.addAttribute("clientes", clientes);

        return "/entrenador-fuerza/clientes";
    }

    @GetMapping("/crear-sesion")
    public String goToCrearSesion() {
        return "/entrenador-fuerza/crear-sesion";
    }

    @GetMapping("/rutina")
    public String goToRutina() {
        return "/entrenador-fuerza/rutina";
    }
}
