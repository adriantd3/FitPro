package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uma.fitpro.dao.EntrenadorClienteRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.EntrenadorCliente;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/entrenador_cross_training")
@Controller
public class EntrenadorCrossTrainingController {

    // EN DESARROLLO
    private int entrenador_id = 4;

    @Autowired
    protected EntrenadorClienteRepository entrenadorClienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/clientes")
    public String doClientes(Model model){
        Usuario entrenador = usuarioRepository.findById(entrenador_id).orElse(null);
        List<EntrenadorCliente> lista = entrenadorClienteRepository.findByEntrenador(entrenador.getId());
        List<Usuario> clientes = new ArrayList<>();
        for (EntrenadorCliente e : lista) {
            clientes.add(e.getCliente());
        }
        model.addAttribute("clientes", clientes);
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
