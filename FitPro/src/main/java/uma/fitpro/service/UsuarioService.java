package uma.fitpro.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService  {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    public List<Usuario> getClientesEntrenador(Usuario entrenador) {

        List<Usuario> clientes = new ArrayList<>(entrenador.getClientesEntrenador());
        Collections.sort(clientes);

        return clientes;
    }

    public List<Usuario> filtrarClientes(HttpSession session, String nombre,
                                         String edad, String altura, String peso) {
        Usuario user = (Usuario) session.getAttribute("user");
        Usuario entrenador =  usuarioRepository.findById(user.getId()).orElse(null);
        Integer edadInt = Integer.parseInt(edad);
        Float pesoFloat = Float.parseFloat(peso);
        Float alturaFloat = Float.parseFloat(altura);
        List<Usuario> clientes = usuarioRepository.filtrarCliente(nombre, entrenador, edadInt,pesoFloat, alturaFloat);

        return clientes;
    }

    public void asignarRutinaCliente(Usuario cliente, Rutina rutina){
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.add(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);
    }

    public void borrarRutinaCliente(Usuario cliente, Rutina rutina){
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.remove(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);
    }


}
