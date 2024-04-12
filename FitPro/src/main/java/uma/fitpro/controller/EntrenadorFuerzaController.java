package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
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
}
