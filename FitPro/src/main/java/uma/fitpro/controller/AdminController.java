package uma.fitpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value ="/admin", method = RequestMethod.POST)
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

