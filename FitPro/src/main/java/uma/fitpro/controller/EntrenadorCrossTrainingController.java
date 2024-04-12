package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/entrenador-cross-training")
@Controller
public class EntrenadorCrossTrainingController {
    @GetMapping("/clientes")
    public String doClientes(){
        return "entrenador-cross-training/clientes";
    }

    @GetMapping("/rutinas")
    public String doRutinas(){
        return "entrenador-cross-training/rutinas";
    }

    @GetMapping("/sesiones")
    public String doSesiones(){
        return "entrenador-cross-training/sesiones";
    }

    @GetMapping("/sesion")
    public String doSesion(){
        return "entrenador-cross-training/sesion";
    }
}
