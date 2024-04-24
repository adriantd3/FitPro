package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Usuario;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doLogin(Model model, HttpSession session) {

        Usuario entrenador = this.usuarioRepository.findById(3).orElse(null);
        session.setAttribute("entrenador", entrenador);

        return "entrenador-fuerza/index";
    }

    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return "cliente/index";
    }
}
