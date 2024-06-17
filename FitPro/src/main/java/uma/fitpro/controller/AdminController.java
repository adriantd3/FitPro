package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.*;
import uma.fitpro.dto.*;
import uma.fitpro.entity.*;
import uma.fitpro.service.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value ="/admin", method = RequestMethod.POST)
public class AdminController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    ComidaService comidaService;
    @Autowired
    GrupoMuscularService grupoMuscularService;
    @Autowired
    TipoEjercicioService tipoEjercicioService;

    //////////////////////////////////////////////////////
    /////////               HOME                 /////////
    //////////////////////////////////////////////////////

    @GetMapping("")
    public String doHome() {
        return "admin/home";
    }

    //////////////////////////////////////////////////////
    /////////               USER                 /////////
    //////////////////////////////////////////////////////

    @GetMapping("/users")
    public String doUsers(@RequestParam("id") Integer id, Model model) {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        List<RolDTO> roles = rolService.findAll();
        if(id > 0) {
            UsuarioDTO usuario = usuarioService.findById(id);
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("usuario", null);
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);

        model.addAttribute("filtroNombre", "");
        model.addAttribute("filtroApellido", "");
        model.addAttribute("filtroRol", "");

        return "admin/users";
    }

    @PostMapping("/add-users")
    public String doAddUsers(@RequestParam("Id") Integer Id, @RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos,
                             @RequestParam(value = "DNI") String dni, @RequestParam("Rol") Integer rol, @RequestParam("Sexo") Byte sexo,
                             @RequestParam(value = "Edad",required = false) Integer edad, @RequestParam(value = "Altura",required = false) Float altura,
                             @RequestParam(value = "Peso",required = false) Float peso, @RequestParam("Email") String email, @RequestParam("Contrasenya") String contrasenya, Model model) {

        if(nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || contrasenya.isEmpty() || email.isEmpty()){
            return "redirect:/admin/users?id=0";
        }
        UsuarioDTO usuario = new UsuarioDTO();
        if (Id != 0) usuario = usuarioService.findById(Id);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setAltura(altura);
        usuario.setEdad(edad);
        usuario.setCorreo(email);
        usuario.setContrasenya(contrasenya);
        usuario.setPeso(peso);
        usuario.setRol(rolService.findById(rol));
        usuario.setSexo(sexo);
        usuarioService.saveData(usuario);

        return "redirect:/admin/users?id=" + Id;
    }

    @PostMapping("/delete-user")
    public String doDeleteUsers(@RequestParam("Id") Integer id, Model model) {

        usuarioService.delete(id);
        return "redirect:/admin/users?id=0";
    }

    @PostMapping("/user/filter")
    public String doUserFilter(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido,@RequestParam("rol") String rol, Model model) {
        List<UsuarioDTO> usuarios = usuarioService.filterUsers(nombre, apellido, rol);
        List<RolDTO> roles = rolService.findAll();

        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroApellido", apellido);
        model.addAttribute("filtroRol", rol);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        model.addAttribute("usuario", null);

        return "admin/users";
    }

    //////////////////////////////////////////////////////
    /////////            EXERCISES               /////////
    //////////////////////////////////////////////////////

    @GetMapping("/exercises")
    public String doExercises(@RequestParam("id") Integer id, Model model) {
        List<EjercicioDTO> ejercicios = ejercicioService.findAll();
        List<GrupoMuscularDTO> gruposMusculares = grupoMuscularService.findAll();
        List<TipoEjercicioDTO> tiposEjercicios = tipoEjercicioService.findAll();
        if(id > 0) {
            EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id);
            model.addAttribute("ejercicio", ejercicio);
        } else {
            model.addAttribute("ejercicio", null);
        }

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("grupos", gruposMusculares);
        model.addAttribute("tipos", tiposEjercicios);

        model.addAttribute("filtroNombre", "");
        model.addAttribute("filtroTipo", "");
        model.addAttribute("filtroGrupoMuscular", "");

        return "admin/exercises";

    }

    @PostMapping("/add-exercise")
    public String doAddexercise(@RequestParam("Id") Integer Id, @RequestParam("Nombre") String nombre, @RequestParam(value = "Imagen", required = false) String imagen,
                                @RequestParam(value = "Video", required = false) String video, @RequestParam("Tipo") Integer tipo, @RequestParam("Grupo") Integer grupo,
                                @RequestParam(value = "Descripcion", required = false) String desc, Model model) {
        if(nombre.isEmpty()) {
            return "redirect:/admin/exercises?id=0";
        }
        EjercicioDTO ejercicio = new EjercicioDTO();
        if(Id != 0) ejercicio = ejercicioService.buscarEjercicio(Id);
        ejercicio.setNombre(nombre);
        ejercicio.setDescripcion(desc);
        ejercicio.setVideo(video);
        ejercicio.setImagen(imagen);
        ejercicio.setTipo(tipoEjercicioService.findById(tipo));
        ejercicio.setGrupoMuscular(grupoMuscularService.findById(grupo));
        ejercicioService.save(ejercicio);

        return "redirect:/admin/exercises?id=" + Id;
    }

    @PostMapping("/delete-exercise")
    public String doDeleteExercise(@RequestParam("Id") Integer id, Model model) {
        ejercicioService.delete(id);

        return "redirect:/admin/exercises?id=0";
    }

    @PostMapping("/exercise/filter")
    public String doExerciseFilter(@RequestParam("nombre") String nombre,@RequestParam("tipo") String tipo,@RequestParam("grupoMuscular") String grupo, Model model) {
        List<EjercicioDTO> ejercicios = ejercicioService.filterExercise(nombre, tipo, grupo);
        List<GrupoMuscularDTO> gruposMusculares = grupoMuscularService.findAll();
        List<TipoEjercicioDTO> tiposEjercicios = tipoEjercicioService.findAll();

        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroTipo", tipo);
        model.addAttribute("filtroGrupoMuscular", grupo);

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("grupos", gruposMusculares);
        model.addAttribute("tipos", tiposEjercicios);
        model.addAttribute("ejercicio", null);

        return "admin/exercises";
    }

    //////////////////////////////////////////////////////
    /////////               Food                 /////////
    //////////////////////////////////////////////////////

    @GetMapping("/food")
    public String doFood(@RequestParam("id") Integer id, Model model) {
        List<ComidaDTO> comidas = comidaService.findAll();
        ComidaDTO comida = null;
        if(id > 0) {
            comida = comidaService.findById(id);
        }
        model.addAttribute("comidas", comidas);
        model.addAttribute("comida", comida);

        model.addAttribute("filtroNombre", "");
        model.addAttribute("filtroCalorias", 0);


        return "admin/food";
    }

    @PostMapping("/add-food")
    public String doAddFood(@RequestParam("Id") Integer Id, @RequestParam("Nombre") String nombre, @RequestParam("Calorias") String cal, Model model) {
        if(nombre.isEmpty() || cal.isEmpty()) {
            return "redirect:/admin/food?id=0";
        }
        ComidaDTO comida = new ComidaDTO();
        if(Id != 0) comida = comidaService.findById(Id);
        comida.setNombre(nombre);
        comida.setCalorias(Integer.parseInt(cal));
        comidaService.save(comida);

        return "redirect:/admin/food?id=" + Id;
    }

    @PostMapping("/delete-food")
    public String doDeleteFood(@RequestParam("Id") Integer id, Model model) {
        comidaService.delete(id);

        return "redirect:/admin/food?id=0";
    }

    @PostMapping("/food/filter")
    public String doFoodFilter(@RequestParam("nombre") String nombre, @RequestParam("calorias") int calorias, Model model) {
        List<ComidaDTO> comidas = comidaService.filterComida(nombre, calorias);

        model.addAttribute("comidas", comidas);
        model.addAttribute("comida", null);

        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroCalorias", calorias);

        return "admin/food";
    }

    //////////////////////////////////////////////////////
    /////////             ASSIGNMENT             /////////
    //////////////////////////////////////////////////////

    @GetMapping("/assignment")
    public String doAssingment(@RequestParam("id") Integer id, @RequestParam(value = "id_propio",required = false) Integer id_trabajador_propio,
                               @RequestParam(value = "id_nuevo",required = false) Integer id_trabajdor_nuevo, Model model) {

        List<UsuarioDTO> clientes = usuarioService.findAllByRol(5); // ID del rol. TODO enumerado
        List<UsuarioDTO> todos_trabajadores = new ArrayList<>();
        Set<UsuarioDTO> cliente_trabajadores = new HashSet<>();

        UsuarioDTO cliente = null;
        UsuarioDTO trabajador_propio = null;
        UsuarioDTO trabajador_nuevo = null;
        if(id > 0) {
            cliente = usuarioService.findById(id);

            todos_trabajadores = usuarioService.findAllByRol(2); // ID del rol. TODO enumerado
            todos_trabajadores.addAll(usuarioService.findAllByRol(3)); // ID del rol. TODO enumerado
            todos_trabajadores.addAll(usuarioService.findAllByRol(4)); // ID del rol. TODO enumerado

            cliente_trabajadores = cliente.getDiestistasCliente();
            cliente_trabajadores.addAll(cliente.getEntrenadoresCliente());
            todos_trabajadores.removeAll(cliente_trabajadores);
        }

        if(id_trabajador_propio != null){
            trabajador_propio = usuarioService.findById(id_trabajador_propio);
        }
        if(id_trabajdor_nuevo != null){
            trabajador_nuevo = usuarioService.findById(id_trabajdor_nuevo);
        }


        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", cliente);
        model.addAttribute("cliente_trabajadores", cliente_trabajadores);
        model.addAttribute("todos_trabajadores", todos_trabajadores);
        model.addAttribute("trabajador_propio", trabajador_propio);
        model.addAttribute("trabajador_nuevo", trabajador_nuevo);
        model.addAttribute("filtroNombre", "");

        return "admin/assignment";
    }

    @PostMapping("/add_trabajador_propio")
    public String doAddTrabajdorPropio(@RequestParam("clienteId") Integer clienteId, @RequestParam("trabajadorId") Integer trabajadorId, Model model) {
        UsuarioDTO trabajador = usuarioService.findById(trabajadorId);
        UsuarioDTO cliente = usuarioService.findById(clienteId);
        if(trabajador.getRol().getId() == 4){
            Set<UsuarioDTO> dietistas = cliente.getDiestistasCliente();
            dietistas.add(trabajador);
            cliente.setDiestistasCliente(dietistas);
        } else {
            Set<UsuarioDTO> entrenadores = cliente.getEntrenadoresCliente();
            entrenadores.add(trabajador);
            cliente.setEntrenadoresCliente(entrenadores);
        }
        usuarioService.saveWorkers(cliente);

        return "redirect:/admin/assignment?id="+clienteId;
    }

    @PostMapping("/delete_trabajador_propio")
    public String doDeleteTrabajdorPropio(@RequestParam("clienteId") Integer clienteId, @RequestParam("trabajadorId") Integer trabajadorId, Model model) {
        UsuarioDTO trabajador = usuarioService.findById(trabajadorId);
        UsuarioDTO cliente = usuarioService.findById(clienteId);
        if(trabajador.getRol().getId() == 4){
            Set<UsuarioDTO> dietistas = cliente.getDiestistasCliente();
            dietistas.remove(trabajador);
            cliente.setDiestistasCliente(dietistas);
        } else {
            Set<UsuarioDTO> entrenadores = cliente.getEntrenadoresCliente();
            entrenadores.remove(trabajador);
            cliente.setEntrenadoresCliente(entrenadores);
        }
        usuarioService.saveWorkers(cliente);

        return "redirect:/admin/assignment?id="+clienteId;
    }

    @PostMapping("/assignment/filter")
    public String doAssignmentFilter(@RequestParam("nombre") String nombre, Model model) {
        List<UsuarioDTO> clientes = usuarioService.filterUsers(nombre,"","cliente");

        model.addAttribute("clientes", clientes);
        model.addAttribute("filtroNombre", nombre);

        return "admin/assignment";
    }


}

