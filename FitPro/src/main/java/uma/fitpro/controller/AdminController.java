package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.*;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value ="/admin", method = RequestMethod.POST)
public class AdminController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    EjercicioRepository ejercicioRepository;
    @Autowired
    ComidaRepository comidaRepository;
    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;

    //////////////////////////////////////////////////////
    /////////               USER                 /////////
    //////////////////////////////////////////////////////

    @GetMapping("/users")
    public String doUsers(@RequestParam("id") Integer id, Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Rol> roles = rolRepository.findAll();
        if(id > 0) {
            Usuario usuario = usuarioRepository.getReferenceById(id);
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
        Usuario usuario = new Usuario();
        if (Id != 0) usuario = usuarioRepository.getReferenceById(Id);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setAltura(altura);
        usuario.setEdad(edad);
        usuario.setCorreo(email);
        usuario.setContrasenya(contrasenya);
        usuario.setPeso(peso);
        usuario.setRol(rolRepository.getReferenceById(rol));
        usuario.setSexo(sexo);
        usuarioRepository.save(usuario);

        return "redirect:/admin/users?id=" + Id;
    }

    @PostMapping("/delete-user")
    public String doDeleteUsers(@RequestParam("Id") Integer id, Model model) {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuarioRepository.delete(usuario);
        return "redirect:/admin/users?id=0";
    }

    @PostMapping("/user/filter")
    public String doUserFilter(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido,@RequestParam("rol") String rol, Model model) {
        List<Usuario> usuarios = usuarioRepository.filterUsers(nombre, apellido, rol);
        List<Rol> roles = rolRepository.findAll();

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
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        List<GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = tipoEjercicioRepository.findAll();
        if(id > 0) {
            Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
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
        Ejercicio ejercicio = new Ejercicio();
        if(Id != 0) ejercicio = ejercicioRepository.getReferenceById(Id);
        ejercicio.setNombre(nombre);
        ejercicio.setDescripcion(desc);
        ejercicio.setVideo(video);
        ejercicio.setImagen(imagen);
        ejercicio.setTipo(tipoEjercicioRepository.getReferenceById(tipo));
        ejercicio.setGrupoMuscular(grupoMuscularRepository.getReferenceById(grupo));
        ejercicioRepository.save(ejercicio);

        return "redirect:/admin/exercises?id=" + Id;
    }

    @PostMapping("/delete-exercise")
    public String doDeleteExercise(@RequestParam("Id") Integer id, Model model) {
        Ejercicio ejercicio = ejercicioRepository.findById(id).orElse(null);
        ejercicioRepository.delete(ejercicio);

        return "redirect:/admin/exercises?id=0";
    }

    @PostMapping("/exercise/filter")
    public String doExerciseFilter(@RequestParam("nombre") String nombre,@RequestParam("tipo") String tipo,@RequestParam("grupoMuscular") String grupo, Model model) {
        List<Ejercicio> ejercicios = ejercicioRepository.filterExercise(nombre, tipo, grupo);
        List<GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = tipoEjercicioRepository.findAll();

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
        List<Comida> comidas = comidaRepository.findAll();
        Comida comida = null;
        if(id > 0) {
            comida = comidaRepository.getReferenceById(id);
        }
        model.addAttribute("comidas", comidas);
        model.addAttribute("comida", comida);

        model.addAttribute("filtroNombre", "");
        model.addAttribute("filtroCalorias", 0);


        return "admin/food";
    }

    @PostMapping("/add-food")
    public String doAddFood(@RequestParam("Id") Integer Id, @RequestParam("Nombre") String nombre, @RequestParam("Calorias") Integer cal, Model model) {
        if(nombre.isEmpty() || cal.describeConstable().isEmpty()) {
            return "redirect:/admin/food?id=0";
        }
        Comida comida = new Comida();
        if(Id != 0) comida = comidaRepository.getReferenceById(Id);
        comida.setNombre(nombre);
        comida.setCalorias(cal);
        comidaRepository.save(comida);

        return "redirect:/admin/food?id=" + Id;
    }

    @PostMapping("/delete-food")
    public String doDeleteFood(@RequestParam("Id") Integer id, Model model) {
        Comida comida = comidaRepository.findById(id).orElse(null);
        comidaRepository.delete(comida);

        return "redirect:/admin/food?id=0";
    }

    @PostMapping("/food/filter")
    public String doFoodFilter(@RequestParam("nombre") String nombre, @RequestParam("calorias") int calorias, Model model) {
        List<Comida> comidas = comidaRepository.filterFood(nombre, calorias);

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

        List<Usuario> clientes = usuarioRepository.findAllByRol(5); // ID del rol. TODO enumerado
        List<Usuario> todos_trabajadores = new ArrayList<>();
        Set<Usuario> cliente_trabajadores = new HashSet<>();

        Usuario cliente = null;
        Usuario trabajador_propio = null;
        Usuario trabajador_nuevo = null;
        if(id > 0) {
            cliente = usuarioRepository.getReferenceById(id);

            todos_trabajadores = usuarioRepository.findAllByRol(2); // ID del rol. TODO enumerado
            todos_trabajadores.addAll(usuarioRepository.findAllByRol(3)); // ID del rol. TODO enumerado
            todos_trabajadores.addAll(usuarioRepository.findAllByRol(4)); // ID del rol. TODO enumerado

            cliente_trabajadores = cliente.getDietistas();
            cliente_trabajadores.addAll(cliente.getEntrenadores());
            todos_trabajadores.removeAll(cliente_trabajadores);
        }

        if(id_trabajador_propio != null){
            trabajador_propio = usuarioRepository.getReferenceById(id_trabajador_propio);
        }
        if(id_trabajdor_nuevo != null){
            trabajador_nuevo = usuarioRepository.getReferenceById(id_trabajdor_nuevo);
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
        Usuario trabajador = usuarioRepository.getReferenceById(trabajadorId);
        Usuario cliente = usuarioRepository.getReferenceById(clienteId);
        if(trabajador.getRol().getId() == 4){
            Set<Usuario> dietistas = cliente.getDietistas();
            dietistas.add(trabajador);
            cliente.setDietistas(dietistas);
        } else {
            Set<Usuario> entrenadores = cliente.getEntrenadores();
            entrenadores.add(trabajador);
            cliente.setEntrenadores(entrenadores);
        }
        usuarioRepository.save(cliente);

        return "redirect:/admin/assignment?id="+clienteId;
    }

    @PostMapping("/delete_trabajador_propio")
    public String doDeleteTrabajdorPropio(@RequestParam("clienteId") Integer clienteId, @RequestParam("trabajadorId") Integer trabajadorId, Model model) {
        Usuario trabajador = usuarioRepository.getReferenceById(trabajadorId);
        Usuario cliente = usuarioRepository.getReferenceById(clienteId);
        if(trabajador.getRol().getId() == 4){
            Set<Usuario> dietistas = cliente.getDietistas();
            dietistas.remove(trabajador);
            cliente.setDietistas(dietistas);
        } else {
            Set<Usuario> entrenadores = cliente.getEntrenadores();
            entrenadores.remove(trabajador);
            cliente.setEntrenadores(entrenadores);
        }
        usuarioRepository.save(cliente);

        return "redirect:/admin/assignment?id="+clienteId;
    }

    @PostMapping("/assignment/filter")
    public String doAssignmentFilter(@RequestParam("nombre") String nombre, Model model) {
        List<Usuario> clientes = usuarioRepository.filterUsers(nombre,"","cliente");

        model.addAttribute("clientes", clientes);
        model.addAttribute("filtroNombre", nombre);

        return "admin/assignment";
    }


}

