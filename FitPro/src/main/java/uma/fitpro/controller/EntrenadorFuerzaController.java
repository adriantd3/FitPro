package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//////////////////////////////////////////////////////
/////////        ENTRENADOR FUERZA           /////////
//////////////////////////////////////////////////////

/**
 * @author Victor Perez Armenta
 */
@Controller
@RequestMapping("/entrenador-fuerza")
public class EntrenadorFuerzaController {

    @PostMapping("/home")
    public String doEntrenadorFuerzaHome(@RequestParam String user, @RequestParam String password) {
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
    public String goToClientes() {
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
