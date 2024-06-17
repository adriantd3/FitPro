package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String doLogin(Model model, HttpSession session) {
        if (estaAutenticado(session)) {
            return "redirect:/home";
        }
        return "login/login";
    }

    @PostMapping("/autenticar")
    public String doHome(@RequestParam String mail, @RequestParam String password, Model model, HttpSession session) {
        String strTo = "redirect:/";
        UsuarioDTO usuario = usuarioService.autenticar(mail, password);
        if(usuario != null){
            session.setAttribute("user", usuario);
            strTo += "home";
        }
        return strTo;
    }

    @GetMapping("/home")
    public String doHome(Model model, HttpSession session) {
        if (estaAutenticado(session)) {
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
            return "redirect:/"+usuario.getRol().getNombre() + "/";
        }
        return "redirect:/";
    }

    @GetMapping("/salir")
    public String doSalir (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    protected boolean estaAutenticado(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
