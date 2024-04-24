package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.RolRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Rol;
import uma.fitpro.entity.Usuario;

import java.util.List;

@Controller
@RequestMapping(value ="/admin", method = RequestMethod.POST)
public class AdminController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    EjercicioRepository ejercicioRepository;
    @Autowired
    ComidaRepository comidaRepository;

    //////////////////////////////////////////////////////
    /////////               ADMIN                /////////
    //////////////////////////////////////////////////////

    @GetMapping("/users")
    public String doUsers(@RequestParam("id") Integer id, Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Rol> roles = rolRepository.findAll();
        if(id != 0) {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("usuario", null);
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);

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
        List<Usuario> clientes = usuarioRepository.findAllByRol(5);

        return "admin/assignment";
    }

}

