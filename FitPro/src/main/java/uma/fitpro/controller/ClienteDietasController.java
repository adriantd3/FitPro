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

    @GetMapping("")
    public String doDietas(Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }

        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
        List<DietaDTO> dietasList = dietaService.buscarDietas(cliente.getDietasCliente());
        model.addAttribute("dietas", dietasList);

        return "cliente/dietas/dietas";
    }

    @GetMapping("/menus_dieta")
    public String doMenusDieta(@RequestParam("id") Integer dieta_id, Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }

        DietaDTO dieta = dietaService.buscarDieta(dieta_id);
        model.addAttribute("dieta", dieta);

        return "cliente/dietas/menus_dieta";
    }

    @GetMapping("desempenyos_menu")
    public String doDesempenyosMenu(@RequestParam("id") Integer menu_id,
                                    @RequestParam(value="dieta_id", required = false) Integer dieta_id
                                    ,Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }

        MenuDTO menu = menuService.buscarMenu(menu_id);
        if(dieta_id != null){
            menu.setDietaId(dieta_id);
        }

        Integer cliente_id = ((UsuarioDTO) session.getAttribute("user")).getId();
        List<DesempenyoMenuDTO> desempenyosMenu =
                desempenyoMenuService.buscarDesempenyosMenuPorClienteYMenu(cliente_id, menu_id);

        model.addAttribute("desempenyos", desempenyosMenu);
        session.setAttribute("menu", menu);

        return "cliente/dietas/desempenyos_menu";
    }

    @GetMapping("/resultados_menu")
    public String doResultadosMenu(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        DesempenyoMenuDTO desempenyoMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        if(!estaAutenticado(session) || !mismoMenu(session, desempenyoMenu)){
            return "redirect:/";
        }

        if(!desempenyoMenu.isTerminado()){
            // Si el desempenyoMenu no esta terminado, redirigir a la vista de ingesta
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
        DesempenyoMenuDTO desempenyoMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        if(!estaAutenticado(session) || !mismoMenu(session, desempenyoMenu)){
            return "redirect:/";
        }

        if(desempenyoMenu.isTerminado()){
            // Si el desempenyoMenu esta terminado, redirigir a la vista de resultados
            return "redirect:/cliente/dietas/resultados_menu?id=" + desempenyo_id;
        }

        List<DesempenyoComidaDTO> des_comidas = desempenyoComidaService.buscarDesempenyosComida(desempenyoMenu.getDesempenyoComidas());

        model.addAttribute("desempenyo_menu", desempenyoMenu);
        model.addAttribute("des_comidas", des_comidas);

        return "cliente/dietas/ingesta";
    }

    @PostMapping("/prev_ingesta")
    public String doPrevDesempenyoSesion(Model model,HttpSession session) {
        if(!estaAutenticado(session)){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        List<ComidaDTO> menu_comidas = menuService.buscarComidasMenu(menu.getComidas());

        model.addAttribute("menu_comidas", menu_comidas);

        return "cliente/dietas/prev_ingesta";
    }

    @PostMapping("/nueva_ingesta")
    public String doNuevaIngesta(Model model, HttpSession session){
        if(!estaAutenticado(session) || session.getAttribute("menu") == null){
            return "redirect:/";
        }

        UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        DesempenyoMenuDTO desMenu = desempenyoMenuService.nuevoDesempenyoMenu(menu.getId(), cliente.getId());

        return "redirect:/cliente/dietas/ingesta?id=" + desMenu.getId();
    }

    @PostMapping("/editar_des_comida")
    public String doEditarDesemepnyoComida(@RequestParam("id") Integer desComidaId, Model model, HttpSession session){
        DesempenyoComidaDTO desComida = desempenyoComidaService.buscarDesempenyoComida(desComidaId);
        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(desComida.getDesempenyoMenu());
        if(!estaAutenticado(session) || !mismoMenu(session, desMenu) || desMenu.isTerminado()){
            return "redirect:/";
        }

        model.addAttribute("des_comida", desComida);

        return "cliente/dietas/editar_des_comida";
    }

    @PostMapping("/guardar_des_comida")
    public String doEditarDesemepnyoComida(@ModelAttribute("des_comida") DesempenyoComidaDTO desComida,
                                           Model model, HttpSession session){
        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(desComida.getDesempenyoMenu());
        if(!estaAutenticado(session) || !mismoMenu(session, desMenu) || desMenu.isTerminado()){
            return "redirect:/";
        }

        desempenyoComidaService.guardarDesempenyoComida(desComida);
        return "redirect:/cliente/dietas/ingesta?id=" + desComida.getDesempenyoMenu();
    }

    @PostMapping("/terminar_ingesta")
    public String doTerminarIngesta(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        if(!estaAutenticado(session) || !mismoMenu(session, desMenu) || desMenu.isTerminado()){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        desempenyoMenuService.terminarDesempenyoMenu(desempenyo_id);
        return "redirect:/cliente/dietas/desempenyos_menu?id=" + menu.getId();
    }

    @PostMapping("/cancelar_ingesta")
    public String doCancelarIngesta(@RequestParam("id") Integer desempenyo_id, Model model, HttpSession session){
        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyo_id);
        if(!estaAutenticado(session) || !mismoMenu(session, desMenu) || desMenu.isTerminado()){
            return "redirect:/";
        }

        MenuDTO menu = (MenuDTO) session.getAttribute("menu");
        desempenyoMenuService.cancelarDesempenyoMenu(desempenyo_id);

        return "redirect:/cliente/dietas/desempenyos_menu?id=" + menu.getId();
    }

    @PostMapping("/filtro_menu")
    public String doFiltroMenu(@ModelAttribute("filtro") FiltroDesempenyoComida filtro, Model model, HttpSession session){
        DesempenyoMenuDTO desMenu = desempenyoMenuService.buscarDesempenyoMenu(filtro.getDesMenuId());
        if(!estaAutenticado(session) || !mismoMenu(session, desMenu)){
            return "redirect:/";
        }
        
        List<DesempenyoComidaDTO> des_comidas = desempenyoComidaService.filtroBuscarDesempenyosComida(filtro);

        model.addAttribute("filtro",filtro);
        model.addAttribute("des_comidas", des_comidas);
        model.addAttribute("desempenyo_menu", desMenu);

        return "cliente/dietas/resultados_menu";
    }

    /**
     * Comprueba si el usuario esta autenticado y es un cliente
     */
    private boolean estaAutenticado(HttpSession session){
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
        return user != null && user.getRol().getId() == 5;
    }

    /**
     * Comprueba si el Menu almacenada en la sesion http es el misma que el del DesempenyoMenu
     */
    private boolean mismoMenu(HttpSession session, DesempenyoMenuDTO desMenu){
        MenuDTO sesion = (MenuDTO) session.getAttribute("menu");
        return sesion != null && sesion.getId().equals(desMenu.getMenuId());
    }

}
