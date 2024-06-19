package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dto.*;
import uma.fitpro.service.*;
import uma.fitpro.ui.FiltroDesempenyoComida;

import java.util.List;

@Controller
@RequestMapping("/cliente/dietas")
public class ClienteDietasController {

    @Autowired
    private DietaService dietaService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DesempenyoMenuService desempenyoMenuService;

    @Autowired
    private DesempenyoComidaService desempenyoComidaService;

    @GetMapping("/")
    public String doDietas(Model model, HttpSession session){
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
        if(cliente == null){
            return "redirect:/";
        }

        List<DietaDTO> dietasList = dietaService.buscarDietas(cliente.getDietasCliente());
        model.addAttribute("dietas", dietasList);

        return "cliente/dietas/dietas";
    }

    @GetMapping("/menus_dieta")
    public String doMenusDieta(@RequestParam("id") Integer dieta_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        DietaDTO dieta = dietaService.buscarDieta(dieta_id);
        model.addAttribute("dieta", dieta);

        return "cliente/dietas/menus_dieta";
    }

    @GetMapping("desempenyos_menu")
    public String doDesempenyosMenu(@RequestParam("id") Integer menu_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        MenuDTO menu = menuService.findById(menu_id);
        Integer cliente_id = ((UsuarioDTO) session.getAttribute("user")).getId();
        List<DesempenyoMenuDTO> desempenyosMenu =
                desempenyoMenuService.buscarDesempenyosMenuPorClienteYMenu(cliente_id, menu_id);

        session.setAttribute("menu", menu);
        model.addAttribute("desempenyos", desempenyosMenu);

        return "cliente/dietas/desempenyos_menu";
    }

    @GetMapping("/resultados_menu")
    public String doResultadosMenu(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        DesempenyoMenuDTO desempenyoMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        if(!desempenyoMenu.isTerminado()){
            return "redirect:/cliente/dietas/ingesta?id=" + desempenyo_id;
        }

        List<DesempenyoComidaDTO> des_comidas = desempenyoComidaService.buscarDesempenyosComida(desempenyoMenu.getDesempenyoComidas());

        model.addAttribute("filtro",new FiltroDesempenyoComida(desempenyoMenu.getId()));

        model.addAttribute("desempenyo_menu", desempenyoMenu);
        model.addAttribute("des_comidas", des_comidas);

        return "cliente/dietas/resultados_menu";
    }

    @GetMapping("/ingesta")
    public String doIngesta(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        DesempenyoMenuDTO desempenyoMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        List<DesempenyoComidaDTO> des_comidas = desempenyoComidaService.buscarDesempenyosComida(desempenyoMenu.getDesempenyoComidas());

        model.addAttribute("desempenyo_menu", desempenyoMenu);
        model.addAttribute("des_comidas", des_comidas);

        return "cliente/dietas/ingesta";
    }

    @PostMapping("/prev_ingesta")
    public String doPrevDesempenyoSesion(Model model,HttpSession session) {
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        List<ComidaDTO> menu_comidas = menuService.buscarComidasMenu(menu.getComidas());

        model.addAttribute("menu_comidas", menu_comidas);

        return "cliente/dietas/prev_ingesta";
    }

    @PostMapping("/nueva_ingesta")
    public String doNuevaIngesta(Model model, HttpSession session){
        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        if(cliente == null || menu == null){
            return "redirect:/";
        }

        DesempenyoMenuDTO desMenu = desempenyoMenuService.nuevoDesempenyoMenu(menu.getId(), cliente.getId());

        return "redirect:/cliente/dietas/ingesta?id=" + desMenu.getId();
    }

    @PostMapping("/editar_des_comida")
    public String doEditarDesemepnyoComida(@RequestParam("id") Integer desComidaId, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        DesempenyoComidaDTO desComida = desempenyoComidaService.buscarDesempenyoComida(desComidaId);
        model.addAttribute("des_comida", desComida);

        return "cliente/dietas/editar_des_comida";
    }

    @PostMapping("/guardar_des_comida")
    public String doEditarDesemepnyoComida(@ModelAttribute("des_comida") DesempenyoComidaDTO desComida,
                                           Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        desempenyoComidaService.guardarDesempenyoComida(desComida);
        return "redirect:/cliente/dietas/ingesta?id=" + desComida.getDesempenyoMenu();
    }

    @PostMapping("/terminar_ingesta")
    public String doTerminarIngesta(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        desempenyoMenuService.terminarDesempenyoMenu(desempenyo_id);
        return "redirect:/cliente/dietas/desempenyos_menu?id=" + menu.getId();
    }

    @PostMapping("/cancelar_ingesta")
    public String doCancelarIngesta(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        desempenyoMenuService.cancelarDesempenyoMenu(desempenyo_id);

        return "redirect:/cliente/dietas/desempenyos_menu?id=" + menu.getId();
    }

    @PostMapping("/filtro_menu")
    public String doFiltroMenu(@ModelAttribute("filtro") FiltroDesempenyoComida filtro, Model model, HttpSession session){
        if(session.getAttribute("user") == null || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(filtro.getDesMenuId());
        List<DesempenyoComidaDTO> des_comidas = desempenyoComidaService.filtroBuscarDesempenyosComida(filtro);

        model.addAttribute("filtro",filtro);
        model.addAttribute("des_comidas", des_comidas);
        model.addAttribute("desempenyo_menu", desMenu);

        return "cliente/dietas/resultados_menu";
    }

}
