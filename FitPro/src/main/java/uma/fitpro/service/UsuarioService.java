package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Usuario;
import uma.fitpro.entity.Rutina;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService extends DTOService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

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

    public List<UsuarioDTO> getClientesEntrenador(UsuarioDTO entrenadorDTO) {
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        List<Usuario> clientes = new ArrayList<>(entrenador.getClientesEntrenador());
        clientes.sort((u1, u2) -> u1.getNombre().compareTo(u2.getNombre()));
        return this.entidadesADTO(clientes);
    }

    public List<UsuarioDTO> filtrarClientes(UsuarioDTO entrenadorDTO, String nombre,
                                         String edad, String altura, String peso) {
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        Integer edadInt = Integer.parseInt(edad);
        Float pesoFloat = Float.parseFloat(peso);
        Float alturaFloat = Float.parseFloat(altura);
        List<Usuario> clientes = usuarioRepository.filtrarCliente(nombre, entrenador, edadInt,pesoFloat, alturaFloat);
        return this.entidadesADTO(clientes);
    }

    public void asignarRutinaCliente(UsuarioDTO clienteDTO, RutinaDTO rutinaDTO){
        Rutina rutina = rutinaRepository.findById(rutinaDTO.getId()).orElse(null);
        Usuario cliente = usuarioRepository.findById(clienteDTO.getId()).orElse(null);
        List<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.add(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);
    }

    public void borrarRutinaCliente(UsuarioDTO clienteDTO, RutinaDTO rutinaDTO){
        Rutina rutina = rutinaRepository.findById(rutinaDTO.getId()).orElse(null);
        Usuario cliente = usuarioRepository.findById(clienteDTO.getId()).orElse(null);
        List<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.remove(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);
    }
}
