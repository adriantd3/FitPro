package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //////////////////////////////////////////////////////
    /////////               ADMIN                /////////
    //////////////////////////////////////////////////////

    @PostMapping("/users")
    public String doUsers() {

        return "admin/users";
    }

    @PostMapping("/exercises")
    public String doExercises() {

        return "admin/exercises";
    }

    @PostMapping("/food")
    public String doFood() {

        return "admin/food";
    }

    @PostMapping("/assignment")
    public String doAssingment() {

        return "admin/assignment";
    }

}

