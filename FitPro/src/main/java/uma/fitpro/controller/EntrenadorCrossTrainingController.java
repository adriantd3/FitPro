package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uma.fitpro.dao.EntrenadorClienteRepository;
import uma.fitpro.entity.Usuario;

import java.util.List;

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {

    // EN DESARROLLO
    private int entrenador_id = 4;

    @Autowired
    protected EntrenadorClienteRepository entrenadorClienteRepository;

    @GetMapping("/clientes")
    public String doClientes(){
        return "entrenador_cross_training/clientes";
    }

    @GetMapping("/rutinas")
    public String doRutinas(){
        return "entrenador_cross_training/rutinas";
    }

    @GetMapping("/sesiones")
    public String doSesiones(){
        return "entrenador_cross_training/sesiones";
    }

    @GetMapping("/sesion")
    public String doSesion(){
        return "entrenador_cross_training/sesion";
    }

    @GetMapping("/ejercicios")
    public String doEjercicios(){
        return "entrenador_cross_training/ejercicios";
    }
}
