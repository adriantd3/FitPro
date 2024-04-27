package uma.fitpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.RolRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Rol;
import uma.fitpro.entity.Usuario;

import java.util.List;

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
        if (Id != 0){
            usuario = usuarioRepository.getReferenceById(Id);
        }
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
        System.out.println("USUARIO: "+ usuario.getId());
        usuarioRepository.delete(usuario);
        return "redirect:/admin/users?id=0";
    }

    //////////////////////////////////////////////////////
    /////////            EXERCISES               /////////
    //////////////////////////////////////////////////////

    @PostMapping("/exercises")
    public String doExercises() {

        return "admin/exercises";
    }

    @PostMapping("/food")
    public String doFood() {

        return "admin/food";
    }

    @PostMapping("/assignment")
    public String doAssingment() {
        List<Usuario> clientes = usuarioRepository.findAllByRol(5);

        return "admin/assignment";
    }

}

