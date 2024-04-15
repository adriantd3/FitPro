package uma.fitpro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LoginController {
    @GetMapping("/")
    public String doLogin() {
        return "login";
    }

    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return "admin/home";
    }
}
