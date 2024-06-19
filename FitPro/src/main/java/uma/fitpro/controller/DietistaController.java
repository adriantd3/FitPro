package uma.fitpro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.*;
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

    @GetMapping("/")
    public String doHome(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "dietista/home";
    }

    //---------------------------------------- MENUS -----------------------------------------------------------------

    @GetMapping("/menus")
    public String doMenus(@RequestParam(value = "id", required = false) Integer id, Model model){
        List<MenuDTO> menus = this.menuService.findAll();
        List<ComidaDTO> comidas = this.comidaService.findAll();
        MenuDTO menu = null;
        List<ComidaDTO> comidasMenu = new ArrayList<>();
        menu = this.menuService.findById(id);

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
        Menu menu = menuService.findById(id).orElse(new Menu());
        Comida comida = comidaService.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();
        if(comida!=null){
            comidasMenu.add(comida);
        }
        menu.setComidas(comidasMenu);
        menu.updateKcal();
        menuService.save(menu);
        return "redirect:/dietista/menus?id="+menu.getId();
    }

    @PostMapping("/eliminarComida")
    public String doEliminarComida(@RequestParam(value = "id") Integer id, @RequestParam(value = "comidaId") Integer comidaId, Model model){
        Menu menu = menuService.findById(id).orElse(null);
        Comida comida = comidaService.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();
        if(comida!=null){
            comidasMenu.remove(comida);
        }
        menu.setComidas(comidasMenu);
        menu.updateKcal();
        menuService.save(menu);
        return "redirect:/dietista/menus?id="+menu.getId();
    }

    @PostMapping("/guardarMenu")
    public String doGuardarMenu(@RequestParam(value = "id") Integer id, @RequestParam("nombre") String nombre){
           Menu menu = menuService.findById(id).orElse(new Menu());
           if(!nombre.equals("")){
               menu.setNombre(nombre);
           }
           menu.updateKcal();
           menuService.save(menu);
        return "redirect:./menus";
    }

    @GetMapping("/limpiarMenu")
    public String doLimpiarMenu(Model model){
        return "redirect:./menus";
    }

    @PostMapping("/borrarMenu")
    public String doBorrarMenu(@RequestParam(value = "id", required = false) Integer id){
        if(id!=null){
            menuService.deleteById(id);
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
            List<Menu> menus = this.menuService.buscarConFiltro(filtroMenu.getNombre(), filtroMenu.getFloatKcal(), filtroMenu.getLocalDateFecha());
            List<Comida> comidas = this.comidaService.findAll();

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
            List<Menu> menus = this.menuService.findAll();
            List<Comida> comidas = this.comidaService.buscarConFiltro(filtroComida.getNombre(), filtroComida.getFloatKcal());
            Menu menu = menuService.findById(filtroComida.getMenuId()).orElse(null);
            List<Comida> comidasMenu = menu!=null? menu.getComidas() : new ArrayList<Comida>();

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
    public String doDietas(@RequestParam(value = "dietaId", required = false) Integer dietaId, @RequestParam(value = "menuId", required = false) Integer menuId, Model model){
        List<Dieta> dietas = this.dietaService.findAll();
        Dieta dieta = null;
        List<Menu> menus = this.menuService.findAll();
        List<OrdenMenuDieta> menusDieta = new ArrayList<>();
        Menu menu = null;
        List<Comida> comidasMenu = new ArrayList<>();

        if(dietaId!=null){
            dieta = this.dietaService.findById(dietaId).orElse(null);
        }
        if(dieta!=null){
            menusDieta = dieta.getOrdenMenuDietas();
        }
        if(menuId!=null){
            menu = this.menuService.findById(menuId).orElse(null);
        }

        if(menu!=null){
            comidasMenu = menu.getComidas();
        }

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
    public String doAnyadirMenuDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam(value = "menuId") Integer menuId, Model model, HttpSession session){
        Dieta dieta = dietaService.findById(dietaId).orElse(null);
        if(dieta==null){
            dieta = new Dieta();
            UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
            dieta.setDietista(dietista);
            dietaService.save(dieta);
        }

        Menu menu = menuService.findById(menuId).orElse(null);
        List<OrdenMenuDieta> menusDieta = dieta.getOrdenMenuDietas();

        if(menu!=null && dieta.getOrdenMenuDietas().size()<7){
            OrdenMenuDietaId ordenMenuDietaId = new OrdenMenuDietaId(menuId, dieta.getId(), dieta.getOrdenMenuDietas().size()+1);
            OrdenMenuDieta ordenMenuDieta = new OrdenMenuDieta(ordenMenuDietaId, menu, dieta);
            ordenMenuDietaService.save(ordenMenuDieta);
            //menusDieta.add(ordenMenuDieta);
        }

        //dieta.setOrdenMenuDietas(menusDieta);
        return "redirect:/dietista/dietas?dietaId="+dieta.getId();
    }

    @PostMapping("/eliminarMenuDieta")
    public String doEliminarMenuDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam(value = "ordenMenu") Integer ordenMenu, @RequestParam(value="menuId") Integer menuId, Model model){
        OrdenMenuDietaId ordenMenuDietaId = new OrdenMenuDietaId();
        ordenMenuDietaId.setOrden(ordenMenu);
        ordenMenuDietaId.setDietaId(dietaId);
        ordenMenuDietaId.setMenuId(menuId);

        OrdenMenuDieta ordenMenuDieta = ordenMenuDietaService.findById(ordenMenuDietaId).orElse(null);

        ordenMenuDietaService.delete(ordenMenuDieta);

        return "redirect:/dietista/dietas?dietaId="+dietaId;
    }

    @PostMapping("/guardarDieta")
    public String doGuardarDieta(@RequestParam(value = "dietaId") Integer dietaId, @RequestParam("nombre") String nombre, HttpSession session){
        Dieta dieta = dietaService.findById(dietaId).orElse(new Dieta());
        if(!nombre.equals("")){
            dieta.setNombre(nombre);
        }

        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        dieta.setDietista(dietista);

        dietaService.save(dieta);
        return "redirect:./dietas";
    }

    @GetMapping("/limpiarDieta")
    public String doLimpiarDieta(Model model){
        return "redirect:./dietas";
    }

    @PostMapping("/borrarDieta")
    public String doBorrarDieta(@RequestParam(value = "dietaId", required = false) Integer dietaId){
        if(dietaId!=null){
            dietaService.deleteById(dietaId);
        }
        return "redirect:./dietas";
    }

    @GetMapping("/filtrarDietas")
    public String doFiltrarDietas(@ModelAttribute("filtroDieta") FiltroDieta filtroDieta, BindingResult result, ModelMap model, HttpSession session){
        String strTo = "dietista/dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroDieta.estaVacio()) {
            strTo = "redirect:./dietas";
        } else {
            List<Dieta> dietas = this.dietaService.buscarConFiltro(filtroDieta.getNombre());
            List<Menu> menus = this.menuService.findAll();

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
        String strTo = "dietista/dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroMenu.estaVacio()) {
            strTo = "redirect:./dietas";
        } else {
            List<Dieta> dietas = this.dietaService.findAll();
            Dieta dieta = dietaService.findById(filtroMenu.getDietaId()).orElse(null);
            List<Menu> menus = this.menuService.buscarConFiltro(filtroMenu.getNombre(), filtroMenu.getFloatKcal());
            List<OrdenMenuDieta> menusDieta = new ArrayList<>();
            if(dieta!=null){
                menusDieta = dieta.getOrdenMenuDietas();
            }

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
        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = dietista.getClientesDietista();

        UsuarioDTO cliente = null;
        if(clienteId!=null){
            cliente = usuarioService.findById(clienteId).orElse(null);
        }


        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("filtroCliente", new FiltroCliente());

        return "dietista/clientes";
    }

    @GetMapping("/asignarDietasClientes")
    public String doAsignarDietasClientes(@RequestParam(value = "clienteId", required = false) Integer clienteId, @RequestParam(value = "dietaId", required = false) Integer dietaId, Model model, HttpSession session){
        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = dietista.getClientesDietista();

        UsuarioDTO cliente = null;
        List<Dieta> dietas = this.dietaService.findAll();
        List<Dieta> dietasCliente = new ArrayList<>();
        Dieta dieta = null;
        List<OrdenMenuDieta> menusDieta = new ArrayList<>();

        if(clienteId!=null){
            cliente = usuarioService.findById(clienteId).orElse(null);
        }
        if(cliente!=null){
            dietasCliente = cliente.getDietasCliente();
        }
        if(dietaId!=null){
            dieta = this.dietaService.findById(dietaId).orElse(null);
        }
        if(dieta!=null){
            menusDieta = dieta.getOrdenMenuDietas();
        }


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
        UsuarioDTO cliente = usuarioService.findById(clienteId).orElse(null);
        Dieta dieta = dietaService.findById(dietaId).orElse(null);
        List<Dieta> dietasCliente = cliente.getDietasCliente();

        if(dieta!=null){
            dietasCliente.add(dieta);
        }
        cliente.setDietasCliente(dietasCliente);
        usuarioService.save(cliente);

        return "redirect:/dietista/asignarDietasClientes?clienteId="+cliente.getId();
    }

    @PostMapping("/eliminarDietaCliente")
    public String doEliminarDietaCliente(@RequestParam(value = "clienteId") Integer clienteId, @RequestParam(value = "dietaId") Integer dietaId, Model model){
        UsuarioDTO cliente = usuarioService.findById(clienteId).orElse(null);
        Dieta dieta = dietaService.findById(dietaId).orElse(null);
        List<Dieta> dietasCliente = cliente.getDietasCliente();
        if(dieta!=null){
            dietasCliente.remove(dieta);
        }
        cliente.setDietasCliente(dietasCliente);
        usuarioService.save(cliente);

        return "redirect:/dietista/asignarDietasClientes?clienteId="+cliente.getId();
    }

    @GetMapping("/filtrarClientes")
    public String doFiltrarClientes(@ModelAttribute("filtroCliente") FiltroCliente filtroCliente, BindingResult result, ModelMap model, HttpSession session){
        String strTo = "dietista/"+filtroCliente.getSourcePage();
        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroCliente.estaVacio()) {
            if(filtroCliente.getSourcePage().equals("clientes_asignar_dietas")){
                strTo = "redirect:./asignarDietasClientes";
            } else if (filtroCliente.getSourcePage().equals("clientes_desempenyo")) {
                strTo = "redirect:./desempenyoClientes";
            }else {
                strTo = "redirect:./" + filtroCliente.getSourcePage();
            }
        } else {
            List<UsuarioDTO> clientes = this.usuarioService.buscarClientesDietistaConFiltro(dietista, filtroCliente.getNombre(), filtroCliente.getApellidos());
            List<Dieta> dietas = this.dietaService.findAll();

            model.addAttribute("clientes", clientes);
            model.addAttribute("cliente", null);
            model.addAttribute("dietas", dietas);
            model.addAttribute("dietasCliente", new ArrayList<>());
            model.addAttribute("dieta", null);
            model.addAttribute("menusDieta", new ArrayList<>());
            model.addAttribute("filtroCliente", filtroCliente);
            model.addAttribute("filtroDieta", new FiltroDieta());
        }

        return strTo;
    }

    @GetMapping("/filtrarDietasCliente")
    public String doFiltrarDietasCliente(@ModelAttribute("filtroDieta") FiltroDieta filtroDieta, BindingResult result, ModelMap model, HttpSession session){
        String strTo = "dietista/clientes_asignar_dietas";
        //if (dietistaAutenticado(session) == false) {
        //    strTo = "redirect:/";
        //} else if
        if (filtroDieta.estaVacio()) {
            strTo = "redirect:./asignarDietasClientes";
        } else {
            UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
            List<UsuarioDTO> clientes = dietista.getClientesDietista();
            UsuarioDTO cliente = usuarioService.findById(filtroDieta.getClienteId()).orElse(null);
            List<Dieta> dietas = this.dietaService.buscarConFiltro(filtroDieta.getNombre());
            List<Dieta> dietasCliente = new ArrayList<>();

            if(cliente!=null){
                dietasCliente = cliente.getDietasCliente();
            }

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


    @GetMapping("/desempenyoClientes")
    public String doDesempanyoClientes(@RequestParam(value = "clienteId", required = false) Integer clienteId,
                                       @RequestParam(value = "dietaId", required = false) Integer dietaId,
                                       @RequestParam(value = "menuId", required = false) Integer menuId,
                                       Model model, HttpSession session){
        UsuarioDTO dietista = (UsuarioDTO) session.getAttribute("user");
        List<UsuarioDTO> clientes = dietista.getClientesDietista();

        UsuarioDTO cliente = null;
        List<Dieta> dietasCliente = new ArrayList<>();
        Dieta dieta = null;
        List<OrdenMenuDieta> menusDieta = new ArrayList<>();
        List<DesempenyoMenu> desempenyosMenu = new ArrayList<>();

        if(clienteId!=null){
            cliente = usuarioService.findById(clienteId).orElse(null);
            if(cliente!=null){
                dietasCliente = cliente.getDietasCliente();
            }
        }
        if(dietaId!=null){
            dieta = this.dietaService.findById(dietaId).orElse(null);
            if(dieta!=null){
                menusDieta = dieta.getOrdenMenuDietas();
            }
        }
        if(clienteId!=null && menuId!=null){
            desempenyosMenu = desempenyoMenuService.findByClientIDAndMenuID(clienteId, menuId);
        }


        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dietasCliente", dietasCliente);
        model.addAttribute("dieta", dieta);
        model.addAttribute("menusDieta", menusDieta);
        model.addAttribute("desempenyosMenu", desempenyosMenu);
        model.addAttribute("filtroCliente", new FiltroCliente());
        model.addAttribute("filtroDesempenyoMenu", new FiltroDesempenyoMenu());

        return "dietista/clientes_desempenyo_menus";
    }

    //AÑADIR METODO QUE COMPRUEBE QUE USER == NULL AL PRINCIPIO DE CADA METODO Y EN CASO DE QUE LO SEA REDIRIGIR A LOGIN
}
