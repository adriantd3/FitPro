package uma.fitpro.controller;

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

import java.util.List;

@Controller
@RequestMapping("/dietista")
public class DietistaController {

    @Autowired
    protected MenuRepository menuRepository;
    @Autowired
    protected ComidaRepository comidaRepository;

    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return null;
    }

    @GetMapping("/menus")
    public String doMenus(@RequestParam("id") Integer id, Model model){
        List<Menu> menus = this.menuRepository.findAll();
        Menu menu = this.menuRepository.findById(id).orElse(null);
        List<Comida> comidas = this.comidaRepository.findAll();
        if(menus.isEmpty()){
            System.out.println("AAAAAAAA");
        }

        //lista con todas las comidas del menu concreto

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidas", comidas);

        return "dietista/menus";
    }
}
