package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Usuario;

@Controller
public class ClienteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/")
    public String doClienteHome(Model model){

        Usuario dietista = this.usuarioRepository.findById(1).orElse(null);
        model.addAttribute("usuario", dietista);

        System.out.println(dietista.toString());

        return "cliente/index";
    }

    @GetMapping("/rutinas")
    public String doRutinas(){
        return "cliente/rutinas";
    }

    @GetMapping("/dietas")
    public String doDietas(){
        return "cliente/dietas";
    }



}
