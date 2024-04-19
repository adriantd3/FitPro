package uma.fitpro.controller;

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
    public String doLogin(Model model) {

        Usuario dietista = this.usuarioRepository.findById(1).orElse(null);
        model.addAttribute("usuario", dietista);

        return "cliente/index";
    }

    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return "cliente/index";
    }
}
