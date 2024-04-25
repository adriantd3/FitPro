package uma.fitpro.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//////////////////////////////////////////////////////
/////////        ENTRENADOR FUERZA           /////////
//////////////////////////////////////////////////////

/**
 * @author Victor Perez Armenta
 */
@Controller
@RequestMapping("/entrenador_fuerza")
public class EntrenadorFuerzaController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    @PostMapping("/home")
    public String doEntrenadorFuerzaHome(@RequestParam String user, @RequestParam String password, Model model) {

        return "/entrenador_fuerza/home";
    }

    @GetMapping("/home")
    public String doHome() {
        return "/entrenador_fuerza/home";
    }

    @GetMapping("/crud-rutina")
    public String goToCrudRutina(@RequestParam("cliente")@Nullable Integer cliente_id, Model model, HttpSession session) {

        List<Rutina> rutinas;
        Usuario entrenador = (Usuario) session.getAttribute("user");
        if (cliente_id != null) {
            Usuario cliente = usuarioRepository.findById(cliente_id).orElse(null);
            model.addAttribute("cliente", cliente);
            rutinas = rutinaRepository.findByClienteByEntrenador(cliente.getRutinasCliente(), entrenador);

        }
        else rutinas = new ArrayList<>(entrenador.getRutinasEntrenador());

        System.out.println("Rutinas de " + entrenador.getNombre() +" "+ entrenador.getRutinasEntrenador().size());
        model.addAttribute("rutinas", rutinas);
        return "/entrenador_fuerza/crud-rutina";
    }

    @GetMapping("/clientes")
    public String doClientes(Model model, HttpSession session) {

        Usuario entrenador = (Usuario)session.getAttribute("user");
        Set<Usuario> clientes = entrenador.getClientesEntrenador();
        model.addAttribute("clientes", clientes);

        return "/entrenador_fuerza/clientes";
    }

    @GetMapping("/crear-sesion")
    public String doCrearSesion() {
        return "/entrenador_fuerza/crear-sesion";
    }

    @GetMapping("/rutina")
    public String doRutina() {
        return "/entrenador_fuerza/rutina";
    }
}
