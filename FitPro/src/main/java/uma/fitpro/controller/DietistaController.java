package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dietista")
public class DietistaController {
    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return null;
    }

    @GetMapping("/menus")
    public String doMenus(){
        return "dietista/menus";
    }
}
