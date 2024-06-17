package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Usuario;

@Service
public class UsuarioService extends DTOService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO autenticar(String mail, String password) {
        Usuario usuario = this.usuarioRepository.autenticar(mail, password);
        if(usuario != null){
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public UsuarioDTO findById(Integer id){
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public Usuario getUsuario(int id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElse(null);
        return usuario;
    }

    public List<Usuario> getClientesEntrenador(Usuario entrenador) {

        List<Usuario> clientes = new ArrayList<>(entrenador.getClientesEntrenador());
        Collections.sort(clientes);

        return clientes;
    }

    public List<Usuario> filtrarClientes(Usuario entrenador, String nombre,
                                         String edad, String altura, String peso) {
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
