package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dto.*;
import uma.fitpro.service.*;
import uma.fitpro.ui.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dietista")
public class DietistaController {

    private HttpSession httpSession;
    @Autowired
    protected MenuService menuService;
    @Autowired
    protected ComidaService comidaService;
    @Autowired
    protected DietaService dietaService;
    @Autowired
    protected OrdenMenuDietaService ordenMenuDietaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DesempenyoMenuService desempenyoMenuService;
    @Autowired
    private DesempenyoComidaService desempenyoComidaService;


    private boolean usuarioTienePermisosDietista(UsuarioDTO usuario){
        boolean permits = true;
        if(usuario==null){
            return false;
        }else{
            if(!usuario.getRol().getNombre().equals("dietista")){
                return false;
            }
        }
        return permits;
    }

    @GetMapping("")
    public String doHome(HttpSession session) {
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        return "dietista/home";
    }

    //---------------------------------------- MENUS -----------------------------------------------------------------

    @GetMapping("/menus")
    public String doMenus(@RequestParam(value = "menuId", required = false) Integer menuId, Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        List<MenuDTO> menus = this.menuService.findAll();
        List<ComidaDTO> comidas = this.comidaService.findAll();
        MenuDTO menu = this.menuService.buscarMenu(menuId);
        List<ComidaDTO> comidasMenu = this.comidaService.buscarComidasMenu(menu);

        model.addAttribute("menus", menus);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("comidas", comidas);
        model.addAttribute("filtroMenu", new FiltroMenu());
        model.addAttribute("filtroComida", new FiltroComida());

        return "dietista/menus";
    }

    @PostMapping("/anyadirComida")
    public String doAnyadirComida(@RequestParam(value = "menuId") Integer menuId, @RequestParam(value = "comidaId") Integer comidaId, Model model){

        this.menuService.anyadirComidaAMenu(menuId, comidaId);

        return "redirect:/dietista/menus?menuId="+menuId;
    }

    @PostMapping("/eliminarComida")
    public String doEliminarComida(@RequestParam(value = "menuId") Integer menuId, @RequestParam(value = "comidaId") Integer comidaId, Model model){

        this.menuService.eliminarComidaDeMenu(menuId, comidaId);

        return "redirect:/dietista/menus?menuId="+menuId;
    }

    @PostMapping("/guardarMenu")
    public String doGuardarMenu(@RequestParam(value = "menuId") Integer menuId, @RequestParam("nombre") String nombre){

        menuService.guardarMenu(menuId, nombre);

        return "redirect:./menus";
    }

    @GetMapping("/limpiarMenu")
    public String doLimpiarMenu(HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        return "redirect:./menus";
    }

    @PostMapping("/borrarMenu")
    public String doBorrarMenu(@RequestParam(value = "menuId", required = false) Integer menuId){

        menuService.borrarMenu(menuId);

        return "redirect:./menus";
    }

    @GetMapping("/filtrarMenu")
    public String doFiltrarMenu(@ModelAttribute("filtroMenu") FiltroMenu filtroMenu, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/menus";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroMenu.estaVacio()) {
            strTo = "redirect:./menus";
        } else {
            List<MenuDTO> menus = this.menuService.filtrar(filtroMenu);
            List<ComidaDTO> comidas = this.comidaService.findAll();

            model.addAttribute("menus", menus);
            model.addAttribute("menu", null);
            model.addAttribute("comidasMenu", new ArrayList<ComidaDTO>());
            model.addAttribute("comidas", comidas);
            model.addAttribute("filtroMenu", filtroMenu);
            model.addAttribute("filtroComida", new FiltroComida());
        }

        return strTo;
    }

    @GetMapping("/filtrarComida")
    public String doFiltrarComida(@ModelAttribute("filtroComida") FiltroComida filtroComida, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/menus";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroComida.estaVacio()) {
            strTo = "redirect:./menus?menuId=" + filtroComida.getMenuId();
        } else {
            List<MenuDTO> menus = this.menuService.findAll();
            List<ComidaDTO> comidas = this.comidaService.filtrar(filtroComida);
            MenuDTO menu = menuService.buscarMenu(filtroComida.getMenuId());
            List<ComidaDTO> comidasMenu = this.comidaService.buscarComidasMenu(menu);

            model.addAttribute("menus", menus);
            model.addAttribute("menu", menu);
            model.addAttribute("comidasMenu", comidasMenu);
            model.addAttribute("comidas", comidas);
            model.addAttribute("filtroMenu", new FiltroMenu());
            model.addAttribute("filtroComida", filtroComida);
        }

        return strTo;
    }

    //---------------------------------------- DIETAS -----------------------------------------------------------------

    @GetMapping("/dietas")
    public String doDietas(@RequestParam(value = "dietaId", required = false) Integer dietaId, @RequestParam(value = "menuId", required = false) Integer menuId, Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        List<DietaDTO> dietas = this.dietaService.findAll();
        DietaDTO dieta = this.dietaService.findById(dietaId);
        List<MenuDTO> menus = this.menuService.findAll();
        List<OrdenMenuDietaDTO> menusDieta = this.ordenMenuDietaService.buscarOrdenMenuDieta(dieta);
        MenuDTO menu = this.menuService.buscarMenu(menuId);
        List<ComidaDTO> comidasMenu = this.comidaService.buscarComidasMenu(menu);

        model.addAttribute("dietas", dietas);
        model.addAttribute("dieta", dieta);
        model.addAttribute("menus", menus);
        model.addAttribute("menusDieta", menusDieta);
        model.addAttribute("menu", menu);
        model.addAttribute("comidasMenu", comidasMenu);
        model.addAttribute("filtroDieta", new FiltroDieta());
        model.addAttribute("filtroMenu", new FiltroMenu());

        return "dietista/dietas";
    }

    @PostMapping("/anyadirMenuDieta")
    public String doAnyadirMenuDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam(value = "menuId") Integer menuId, @RequestParam(value = "ordenMenu") Integer ordenMenu, Model model, HttpSession session){

        this.dietaService.anyadirMenuADieta(menuId, dietaId, ordenMenu);

        return "redirect:/dietista/dietas?dietaId="+dietaId;
    }

    @PostMapping("/eliminarMenuDieta")
    public String doEliminarMenuDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam(value = "ordenMenu") Integer ordenMenu, @RequestParam(value="menuId") Integer menuId, Model model){

        this.dietaService.eliminarMenuDeDieta(menuId, dietaId, ordenMenu);

        return "redirect:/dietista/dietas?dietaId="+dietaId;
    }

    @PostMapping("/guardarDieta")
    public String doGuardarDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam("nombre") String nombre, HttpSession session){
        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        dietaService.guardarDieta(dietaId, nombre, dietista);

        return "redirect:./dietas";
    }

    @GetMapping("/limpiarDieta")
    public String doLimpiarDieta(HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        return "redirect:./dietas";
    }

    @PostMapping("/borrarDieta")
    public String doBorrarDieta(@RequestParam(value = "dietaId", required = false) Integer dietaId){

        dietaService.borrarDieta(dietaId);

        return "redirect:./dietas";
    }

    @GetMapping("/filtrarDietas")
    public String doFiltrarDietas(@ModelAttribute("filtroDieta") FiltroDieta filtroDieta, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroDieta.estaVacio()) {
            strTo = "redirect:./dietas";
        } else {
            List<DietaDTO> dietas = this.dietaService.filtrar(filtroDieta);
            List<MenuDTO> menus = this.menuService.findAll();

            model.addAttribute("dietas", dietas);
            model.addAttribute("dieta", null);
            model.addAttribute("menus", menus);
            model.addAttribute("menusDieta", new ArrayList<>());
            model.addAttribute("menu", null);
            model.addAttribute("comidasMenu", new ArrayList<>());
            model.addAttribute("filtroDieta", filtroDieta);
            model.addAttribute("filtroMenu", new FiltroMenu());
        }

        return strTo;
    }

    @GetMapping("/filtrarMenusDieta")
    public String doFiltrarMenusDieta(@ModelAttribute("filtroMenu") FiltroMenu filtroMenu, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroMenu.estaVacio()) {
            strTo = "redirect:./dietas?dietaId="+filtroMenu.getDietaId();
        } else {
            List<DietaDTO> dietas = this.dietaService.findAll();
            DietaDTO dieta = dietaService.findById(filtroMenu.getDietaId());
            List<MenuDTO> menus = this.menuService.filtrar(filtroMenu);
            List<OrdenMenuDietaDTO> menusDieta = this.ordenMenuDietaService.buscarOrdenMenuDieta(dieta);

            model.addAttribute("dietas", dietas);
            model.addAttribute("dieta", dieta);
            model.addAttribute("menus", menus);
            model.addAttribute("menusDieta", menusDieta);
            model.addAttribute("menu", null);
            model.addAttribute("comidasMenu", new ArrayList<>());
            model.addAttribute("filtroDieta", new FiltroDieta());
            model.addAttribute("filtroMenu", filtroMenu);
        }

        return strTo;
    }


    //---------------------------------------- CLIENTES -----------------------------------------------------------------

    @GetMapping("/clientes")
    public String doClientes(@RequestParam(value = "clienteId", required = false) Integer clienteId, Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
        UsuarioDTO cliente = usuarioService.findById(clienteId);

        if(cliente != null && !clientes.contains(cliente)) return "redirect:./clientes";

        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("filtroCliente", new FiltroCliente());

        return "dietista/clientes";
    }

    //--------------------------------------ASIGNAR DIETAS---------------------------------------------------------------
    @GetMapping("/asignarDietasCliente")
    public String doAsignarDietasCliente(@RequestParam(value = "clienteId", required = false) Integer clienteId, @RequestParam(value = "dietaId", required = false) Integer dietaId, Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
        UsuarioDTO cliente = usuarioService.findById(clienteId);

        if(cliente != null && !clientes.contains(cliente)) return "redirect:./clientes";

        List<DietaDTO> dietas = this.dietaService.findAll();
        List<DietaDTO> dietasCliente = this.dietaService.buscarDietasCliente(cliente);
        DietaDTO dieta = this.dietaService.findById(dietaId);
        List<OrdenMenuDietaDTO> menusDieta = this.ordenMenuDietaService.buscarOrdenMenuDieta(dieta);;

        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dietas", dietas);
        model.addAttribute("dietasCliente", dietasCliente);
        model.addAttribute("dieta", dieta);
        model.addAttribute("menusDieta", menusDieta);
        model.addAttribute("filtroCliente", new FiltroCliente());
        model.addAttribute("filtroDieta", new FiltroDieta());

        return "dietista/clientes_asignar_dietas";
    }

    @PostMapping("/asignarDietaCliente")
    public String doAsignarDietaCliente(@RequestParam(value = "clienteId") Integer clienteId, @RequestParam(value = "dietaId") Integer dietaId, Model model, HttpSession session){

        usuarioService.asignarDietaCliente(clienteId, dietaId);

        return "redirect:/dietista/asignarDietasCliente?clienteId="+clienteId;
    }

    @PostMapping("/eliminarDietaCliente")
    public String doEliminarDietaCliente(@RequestParam(value = "clienteId") Integer clienteId, @RequestParam(value = "dietaId") Integer dietaId, Model model){

        usuarioService.eliminarDietaCliente(clienteId, dietaId);

        return "redirect:/dietista/asignarDietasCliente?clienteId="+clienteId;
    }

    @GetMapping("/filtrarClientes")
    public String doFiltrarClientes(@ModelAttribute("filtroCliente") FiltroCliente filtroCliente, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/"+filtroCliente.getSourcePage();
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroCliente.estaVacio()) {
            if(filtroCliente.getSourcePage().equals("clientes_asignar_dietas")){
                strTo = "redirect:./asignarDietasCliente";
            } else if (filtroCliente.getSourcePage().equals("clientes_desempenyo_menus")) {
                strTo = "redirect:./desempenyoCliente";
            } else if (filtroCliente.getSourcePage().equals("clientes_desempenyo_comidas")){
                strTo = "redirect:./desempenyoComidasMenuCliente";
            } else {
                strTo = "redirect:./" + filtroCliente.getSourcePage();
            }
        } else {
            UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
            UsuarioDTO cliente = (UsuarioDTO) this.usuarioService.findById(filtroCliente.getClienteId());
            List<UsuarioDTO> clientes = this.usuarioService.filtrarClientesDietista(dietista, filtroCliente);
            List<DietaDTO> dietas = this.dietaService.findAll();

            model.addAttribute("clientes", clientes);
            model.addAttribute("cliente", cliente);
            model.addAttribute("dietas", dietas);
            model.addAttribute("dietasCliente", new ArrayList<>());
            model.addAttribute("dieta", null);
            model.addAttribute("menusDieta", new ArrayList<>());
            model.addAttribute("filtroCliente", filtroCliente);
            model.addAttribute("filtroDieta", new FiltroDieta());
            model.addAttribute("filtroDesempenyoMenu", new FiltroDesempenyoMenu());
            model.addAttribute("menu", null);
            model.addAttribute("desempenyosMenu", new ArrayList<>());
            model.addAttribute("desempenyoMenu", null);
        }
        return strTo;
    }

    @GetMapping("/filtrarDietasCliente")
    public String doFiltrarDietasCliente(@ModelAttribute("filtroDieta") FiltroDieta filtroDieta, BindingResult result, ModelMap model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/clientes_asignar_dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/salir";
        //} else if
        if (filtroDieta.estaVacio()) {
            strTo = "redirect:./asignarDietasCliente?clienteId="+filtroDieta.getClienteId();
        } else {
            UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
            List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
            UsuarioDTO cliente = usuarioService.findById(filtroDieta.getClienteId());
            List<DietaDTO> dietas = this.dietaService.filtrar(filtroDieta);
            List<DietaDTO> dietasCliente = dietaService.buscarDietasCliente(cliente);

            model.addAttribute("clientes", clientes);
            model.addAttribute("cliente", cliente);
            model.addAttribute("dietas", dietas);
            model.addAttribute("dietasCliente", dietasCliente);
            model.addAttribute("dieta", null);
            model.addAttribute("menusDieta", new ArrayList<>());
            model.addAttribute("filtroCliente", new FiltroCliente());
            model.addAttribute("filtroDieta", filtroDieta);
        }
        return strTo;
    }

    //--------------------------------------DESEMPENYO---------------------------------------------------------------
    @GetMapping("/desempenyoCliente")
    public String doDesempanyoCliente(@RequestParam(value = "clienteId", required = false) Integer clienteId,
                                       @RequestParam(value = "dietaId", required = false) Integer dietaId,
                                       @RequestParam(value = "menuId", required = false) Integer menuId,
                                       Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
        UsuarioDTO cliente = usuarioService.findById(clienteId);

        if(cliente != null && !clientes.contains(cliente)) return "redirect:./clientes";

        List<DietaDTO> dietasCliente = dietaService.buscarDietasCliente(cliente);
        DietaDTO dieta = dietaService.findById(dietaId);
        List<OrdenMenuDietaDTO> menusDieta = ordenMenuDietaService.buscarOrdenMenuDieta(dieta);
        MenuDTO menu = menuService.buscarMenu(menuId);
        List<DesempenyoMenuDTO> desempenyosMenu = desempenyoMenuService.buscarDesempenyosMenuPorClienteYMenu(clienteId, menuId);

        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dietasCliente", dietasCliente);
        model.addAttribute("dieta", dieta);
        model.addAttribute("menusDieta", menusDieta);
        model.addAttribute("menu", menu);
        model.addAttribute("desempenyosMenu", desempenyosMenu);
        model.addAttribute("filtroCliente", new FiltroCliente());
        model.addAttribute("filtroDesempenyoMenu", new FiltroDesempenyoMenu());

        return "dietista/clientes_desempenyo_menus";
    }

    @GetMapping("/filtrarDesempenyosMenuCliente")
    public String doFiltrarDesempanyosCliente(@ModelAttribute("filtroDieta") FiltroDesempenyoMenu filtroDesempenyoMenu,
                                    Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        String strTo = "dietista/clientes_desempenyo_menus";

            UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
            List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
            UsuarioDTO cliente = usuarioService.findById(filtroDesempenyoMenu.getClienteId());
            List<DietaDTO> dietasCliente = dietaService.buscarDietasCliente(cliente);
            DietaDTO dieta = dietaService.findById(filtroDesempenyoMenu.getDietaId());
            List<OrdenMenuDietaDTO> menusDieta = ordenMenuDietaService.buscarOrdenMenuDieta(dieta);
            MenuDTO menu = menuService.buscarMenu(filtroDesempenyoMenu.getMenuId());
            List<DesempenyoMenuDTO> desempenyosMenu = this.desempenyoMenuService.filtrar(filtroDesempenyoMenu);

            model.addAttribute("clientes", clientes);
            model.addAttribute("cliente", cliente);
            model.addAttribute("dietasCliente", dietasCliente);
            model.addAttribute("dieta", dieta);
            model.addAttribute("menusDieta", menusDieta);
            model.addAttribute("menu", menu);
            model.addAttribute("desempenyosMenu", desempenyosMenu);
            model.addAttribute("filtroCliente", new FiltroCliente());
            model.addAttribute("filtroDesempenyoMenu", filtroDesempenyoMenu);
        return strTo;
    }

    @GetMapping("/desempenyoComidasMenuCliente")
    public String doDesempanyoComidasCliente(@RequestParam(value = "clienteId") Integer clienteId,
                                        @RequestParam(value = "dietaId") Integer dietaId,
                                        @RequestParam(value = "menuId") Integer menuId,
                                        @RequestParam(value = "desempenyoMenuId") Integer desempenyoMenuId,
                                        Model model, HttpSession session){
        if (!usuarioTienePermisosDietista((UsuarioDTO) session.getAttribute("user"))) return "redirect:/salir";

        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = usuarioService.getClientesDietista(dietista);
        UsuarioDTO cliente = usuarioService.findById(clienteId);
        List<DietaDTO> dietasCliente = dietaService.buscarDietasCliente(cliente);
        DietaDTO dieta = dietaService.findById(dietaId);
        List<OrdenMenuDietaDTO> menusDieta = ordenMenuDietaService.buscarOrdenMenuDieta(dieta);
        DesempenyoMenuDTO desempenyoMenu = desempenyoMenuService.buscarDesempenyoMenu(desempenyoMenuId);
        List<DesempenyoComidaDTO> desempenyosComida = desempenyoComidaService.buscarDesempenyosComidaDesempenyoMenu(desempenyoMenu);

        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dietasCliente", dietasCliente);
        model.addAttribute("dieta", dieta);
        model.addAttribute("menusDieta", menusDieta);
        model.addAttribute("desempenyoMenu", desempenyoMenu);
        model.addAttribute("desempenyosComida", desempenyosComida);
        model.addAttribute("filtroCliente", new FiltroCliente());
        model.addAttribute("filtroDesempenyoMenu", new FiltroDesempenyoMenu());

        return "dietista/clientes_desempenyo_comidas";
    }
}
