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

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doLogin(HttpSession sesion) {

        Usuario dietista = this.usuarioRepository.findById(1).orElse(null);
        sesion.setAttribute("user", dietista);
        return "dietista/home";
    }

    @PostMapping("/home")
    public String doHome(@RequestParam String mail, @RequestParam String password, Model model, HttpSession session) {
        List<Usuario> user = usuarioRepository.findByMail(mail);
        if (!user.isEmpty()) {
            Usuario usuario = user.get(0);
            if (usuario != null && usuario.getContrasenya().equals(password)) {
                session.setAttribute("user", usuario);
                return usuario.getRol().getNombre()+"/home";
            }
        }
        return "redirect:/";

    }
}
