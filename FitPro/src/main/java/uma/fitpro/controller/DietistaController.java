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
import uma.fitpro.dao.SerieRepository;
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
    @Autowired
    private SerieRepository serieRepository;

<<<<<<< Updated upstream
    @PostMapping("/home")
    public String doHome(@RequestParam String user, @RequestParam String password) {

        return null;
    }

    @GetMapping("/menus")
    public String doMenus(@RequestParam("id") Integer id, Model model){
        List<Menu> menus = this.menuRepository.findAll();
        Menu menu = this.menuRepository.findById(id).orElse(null);
        List<Comida> comidasMenu = null;
=======
    @GetMapping("/home")
    public String doHome() {
        return "home";
    }

    @GetMapping("/menus")
    public String doMenus(@RequestParam(value = "id", required = false) Integer id, Model model){
        List<Menu> menus = this.menuRepository.findAll();
        List<Comida> comidas = this.comidaRepository.findAll();
        Menu menu = null;
        List<Comida> comidasMenu = new ArrayList<>();

        if(id!=null){
            menu = this.menuRepository.findById(id).orElse(null);
        }

>>>>>>> Stashed changes
        if(menu!=null){
            comidasMenu = menu.getComidas();
        }
        List<Comida> comidas = this.comidaRepository.findAll();


        //lista con todas las comidas del menu concreto

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("comidas", comidas);

        return "dietista/menus";
    }
<<<<<<< Updated upstream
=======

    @PostMapping("/anyadirComida")
    public String doAnyadirComida(@RequestParam(value = "menuId") Integer menuId, @RequestParam(value = "comidaId") Integer comidaId, Model model){
        Menu menu = menuRepository.findById(menuId).orElse(new Menu());
        Comida comida = comidaRepository.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();
        if(comida!=null){
            comidasMenu.add(comida);
        }
        menu.setComidas(comidasMenu);
        menuRepository.save(menu);
        return "redirect:/dietista/menus?id="+menu.getId();
    }

    @PostMapping("/guardar")
    public String doGuardar(@RequestParam(value = "id") Integer id, @RequestParam("nombre") String nombre){
           Menu menu = menuRepository.findById(id).orElse(new Menu());
           menu.setNombre(nombre);
           menuRepository.save(menu);
        return "redirect:./menus";
    }

    @GetMapping("/limpiar")
    public String doLimpiar(Model model){
        return "redirect:./menus";
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam(value = "id", required = false) Integer id){
        if(id!=null){
            menuRepository.deleteById(id);
        }
        return "redirect:./menus";
    }



    @PostMapping("/clientes")
    public String doClientes(@RequestParam(value = "id", required = false) Integer clienteId, Model model, HttpSession sesion){
        Usuario dietista = (Usuario) sesion.getAttribute("user");
        List<Usuario> clientes = new ArrayList<>(dietista.getClientesDietista());


        model.addAttribute("clientes", clientes);

        return "dietista/clientes";
    }
>>>>>>> Stashed changes
}
