package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dietista")
public class DietistaController {

    @Autowired
    protected MenuRepository menuRepository;
    @Autowired
    protected ComidaRepository comidaRepository;

    @PostMapping("/home")
    public String doHome() {
        return "home";
    }

    @PostMapping("/menus")
    public String doMenus(@RequestParam(value = "id", required = false) Integer id, Model model){
        List<Menu> menus = this.menuRepository.findAll();
        List<Comida> comidas = this.comidaRepository.findAll();
        Menu menu = null;
        List<Comida> comidasMenu = null;

        if(id!=null){
            menu = this.menuRepository.findById(id).orElse(null);
        }

        if(menu!=null){
            comidasMenu = new ArrayList<>(menu.getComidas());
        }

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("comidas", comidas);

        return "dietista/menus";
    }

    @PostMapping("/limpiar")
    public String doLimpiar(@RequestParam(value = "id", required = false) Integer id, Model model){
        List<Menu> menus = this.menuRepository.findAll();
        List<Comida> comidas = this.comidaRepository.findAll();
        Menu menu = null;
        List<Comida> comidasMenu = null;

        if(id!=null){
            menu = this.menuRepository.findById(id).orElse(null);
        }

        if(menu!=null){
            comidasMenu = new ArrayList<>(menu.getComidas());
        }

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("comidas", comidas);

        return "dietista/menus";
    }



    @PostMapping("/clientes")
    public String doClientes(@RequestParam(value = "id", required = false) Integer clienteId, Model model, HttpSession sesion){
        Usuario dietista = (Usuario) sesion.getAttribute("user");
        List<Usuario> clientes = new ArrayList<>(dietista.getClientesDietista());


        model.addAttribute("clientes", clientes);

        return "dietista/clientes";
    }
}
