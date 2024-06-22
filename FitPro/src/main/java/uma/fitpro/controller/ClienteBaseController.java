//AUTOR: Adrián Torremocha(100%)
package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteBaseController {

    @GetMapping("")
    public String doHome(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "cliente/home";
    }
}
