package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DietaRepository;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Dieta;
import uma.fitpro.entity.Menu;
import uma.fitpro.entity.Usuario;
import uma.fitpro.entity.Rutina;
import uma.fitpro.ui.FiltroCliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService extends DTOService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private DietaRepository dietaRepository;

    public UsuarioDTO autenticar(String mail, String password) {
        Usuario usuario = this.usuarioRepository.autenticar(mail, password);
        if(usuario != null){
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public UsuarioDTO findById(Integer id){
        UsuarioDTO usuarioDTO = null;
        if(id != null){
            Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
            if(usuario != null){
                usuarioDTO = usuario.toDTO();
            }
        }
        return usuarioDTO;
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

    public List<UsuarioDTO> getClientesDietista(UsuarioDTO dietistaDTO) {
        List<Usuario> clientes = usuarioRepository.findAllById(dietistaDTO.getClientesDietista());
        return this.entidadesADTO(clientes);
    }

    public void asignarDietaCliente(Integer clienteId, Integer dietaId){
        Dieta dieta = dietaRepository.findById(dietaId).orElse(null);
        Usuario cliente = usuarioRepository.findById(clienteId).orElse(null);
        List<Dieta> dietasCliente = cliente.getDietasCliente();
        dietasCliente.add(dieta);
        cliente.setDietasCliente(dietasCliente);
        usuarioRepository.save(cliente);
    }

    public void eliminarDietaCliente(Integer clienteId, Integer dietaId) {
        Dieta dieta = dietaRepository.findById(dietaId).orElse(null);
        Usuario cliente = usuarioRepository.findById(clienteId).orElse(null);
        List<Dieta> dietasCliente = cliente.getDietasCliente();
        dietasCliente.remove(dieta);
        cliente.setDietasCliente(dietasCliente);
        usuarioRepository.save(cliente);
    }

    public List<UsuarioDTO> filtrarClientesDietista(UsuarioDTO dietistaDTO, FiltroCliente filtroCliente) {
        Usuario dietista = usuarioRepository.findById(dietistaDTO.getId()).orElse(null);
        List<Usuario> clientesDietista = usuarioRepository.buscarClientesDietistaConFiltro(dietista, filtroCliente.getNombre(), filtroCliente.getApellidos());

        return this.entidadesADTO(clientesDietista);
    }
}
