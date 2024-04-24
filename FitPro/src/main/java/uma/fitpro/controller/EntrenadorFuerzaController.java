package uma.fitpro.controller;

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

    @PostMapping("/home")
    public String doEntrenadorFuerzaHome(@RequestParam String user, @RequestParam String password, Model model) {

        Usuario usuario = usuarioRepository.findById(3).orElse(null);

        model.addAttribute("usuario", usuario);

        return "/entrenador-fuerza/home";
    }

    @GetMapping("/home")
    public String goHome() {
        return "/entrenador-fuerza/home";
    }

    @GetMapping("/crud-rutina")
    public String goToCrudRutina() {
        return "/entrenador-fuerza/crud-rutina";
    }

    @GetMapping("/clientes")
    public String goToClientes(@RequestParam("entrenador") Integer entrenador_id, Model model) {

        List<Usuario> clientes = usuarioRepository.findClientes(entrenador_id);

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
