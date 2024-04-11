package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FitProController {

    @GetMapping("/")
    public String doLogin() {
        return "entrenador-cross-training/index";
    }

    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return "admin/admin-home";
    }

    //////////////////////////////////////////////////////
    /////////               ADMIN                /////////
    //////////////////////////////////////////////////////

    @PostMapping("/admin-users")
    public String doUsers() {

        return "admin/admin-users";
    }

    @PostMapping("/admin-exercises")
    public String doExercises() {

        return "admin/admin-exercises";
    }

    @PostMapping("/admin-food")
    public String doFood() {

        return "admin/admin-food";
    }

    @PostMapping("/admin-assignment")
    public String doAssingment() {

        return "admin/admin-assignment";
    }

}
