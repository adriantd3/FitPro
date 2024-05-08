package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.entity.*;
import uma.fitpro.ui.FiltroComida;
import uma.fitpro.ui.FiltroMenu;

import javax.swing.*;
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
        if(menu!=null){
            comidasMenu = menu.getComidas();
        }

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("comidas", comidas);
        model.addAttribute("filtroMenu", new FiltroMenu());
        model.addAttribute("filtroComida", new FiltroComida());

        return "dietista/menus";
    }

    @PostMapping("/anyadirComida")
    public String doAnyadirComida(@RequestParam(value = "id") Integer id, @RequestParam(value = "comidaId") Integer comidaId, Model model){
        Menu menu = menuRepository.findById(id).orElse(new Menu());
        Comida comida = comidaRepository.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();
        if(comida!=null){
            comidasMenu.add(comida);
        }
        menu.setComidas(comidasMenu);
        menu.updateKcal();
        menuRepository.save(menu);
        return "redirect:/dietista/menus?id="+menu.getId();
    }

    @PostMapping("/eliminarComida")
    public String doEliminarComida(@RequestParam(value = "id") Integer id, @RequestParam(value = "comidaId") Integer comidaId, Model model){
        Menu menu = menuRepository.findById(id).orElse(null);
        Comida comida = comidaRepository.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();
        if(comida!=null){
            comidasMenu.remove(comida);
        }
        menu.setComidas(comidasMenu);
        menu.updateKcal();
        menuRepository.save(menu);
        return "redirect:/dietista/menus?id="+menu.getId();
    }

    @PostMapping("/guardarMenu")
    public String doGuardarMenu(@RequestParam(value = "id") Integer id, @RequestParam("nombre") String nombre){
           Menu menu = menuRepository.findById(id).orElse(new Menu());
           if(!nombre.equals("")){
               menu.setNombre(nombre);
           }
           menu.updateKcal();
           menuRepository.save(menu);
        return "redirect:./menus";
    }

    @GetMapping("/limpiarMenu")
    public String doLimpiarMenu(Model model){
        return "redirect:./menus";
    }

    @PostMapping("/borrarMenu")
    public String doBorrarMenu(@RequestParam(value = "id", required = false) Integer id){
        if(id!=null){
            menuRepository.deleteById(id);
        }
        return "redirect:./menus";
    }

    @GetMapping("/filtrarMenu")
    public String doFiltrarMenu(@ModelAttribute("filtroMenu") FiltroMenu filtroMenu, BindingResult result, ModelMap model, HttpSession session){
        String strTo = "dietista/menus";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroMenu.estaVacio()) {
            strTo = "redirect:./menus";
        } else {
            List<Menu> menus = this.menuRepository.buscarConFiltro(filtroMenu.getNombre(), filtroMenu.getFloatKcal(), filtroMenu.getLocalDateFecha());
            List<Comida> comidas = this.comidaRepository.findAll();

            model.addAttribute("menus", menus);
            model.addAttribute("menu", null);
            model.addAttribute("comidasMenu", new ArrayList<Comida>());
            model.addAttribute("comidas", comidas);
            model.addAttribute("filtroMenu", filtroMenu);
            model.addAttribute("filtroComida", new FiltroComida());
        }

        return strTo;
    }

    @GetMapping("/filtrarComida")
    public String doFiltrarComida(@ModelAttribute("filtroComida") FiltroComida filtroComida, BindingResult result, ModelMap model, HttpSession session){
        String strTo = "dietista/menus";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroComida.estaVacio()) {
            strTo = "redirect:./menus";
        } else {
            List<Menu> menus = this.menuRepository.findAll();
            List<Comida> comidas = this.comidaRepository.buscarConFiltro(filtroComida.getNombre(), filtroComida.getFloatKcal());

            model.addAttribute("menus", menus);
            model.addAttribute("menu", null);
            model.addAttribute("comidasMenu", new ArrayList<Comida>());
            model.addAttribute("comidas", comidas);
            model.addAttribute("filtroMenu", new FiltroMenu());
            model.addAttribute("filtroComida", filtroComida);
        }

        return strTo;
    }



    @PostMapping("/clientes")
    public String doClientes(@RequestParam(value = "id", required = false) Integer clienteId, Model model, HttpSession sesion){
        Usuario dietista = (Usuario) sesion.getAttribute("user");
        List<Usuario> clientes = new ArrayList<>(dietista.getClientesDietista());


        model.addAttribute("clientes", clientes);

        return "dietista/clientes";
    }
}
