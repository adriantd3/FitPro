package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @PostMapping("/home")
    public String doClienteHome(){
        return "cliente/index";
    }

    @GetMapping("/rutinas")
    public String doRutinas(){
        return "cliente/rutinas";
    }

    @GetMapping("/dietas")
    public String doDietas(){
        return "cliente/dietas";
    }



}
