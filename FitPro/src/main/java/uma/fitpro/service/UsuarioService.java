package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.RolRepository;
import uma.fitpro.dao.DietaRepository;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Rol;
import uma.fitpro.entity.Dieta;
import uma.fitpro.entity.Menu;
import uma.fitpro.entity.Usuario;
import uma.fitpro.entity.Rutina;
import uma.fitpro.ui.FiltroCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Adrián Torremocha Doblas (20%)
 * @author Ezequiel Sánchez García (20%)
 * @author José Ángel Bueno Ruiz (20%)
 * @author David Bueno Carmona (40%)
 */

@Service
public class UsuarioService extends DTOService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

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
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
        rutinas_cliente.add(rutina);
        cliente.setRutinasCliente(rutinas_cliente);
        usuarioRepository.save(cliente);
    }

    public void borrarRutinaCliente(UsuarioDTO clienteDTO, RutinaDTO rutinaDTO){
        Rutina rutina = rutinaRepository.findById(rutinaDTO.getId()).orElse(null);
        Usuario cliente = usuarioRepository.findById(clienteDTO.getId()).orElse(null);
        Set<Rutina> rutinas_cliente = cliente.getRutinasCliente();
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

    public List<UsuarioDTO> findAll() {
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        usuarios.forEach(usuario -> usuariosDTO.add(usuario.toDTO()));
        return usuariosDTO;
    }

    public void saveData(UsuarioDTO usuarioDTO) {
        Rol rol = rolRepository.findById(usuarioDTO.getRol().getId()).orElse(null);
        Usuario usuario = null;
        if(usuarioDTO.getId() != null) usuario = usuarioRepository.findById(usuarioDTO.getId()).orElse(null);
        if(usuario == null) {
            usuario = new Usuario();
        }
        usuario.setPeso(usuarioDTO.getPeso());
        usuario.setAltura(usuarioDTO.getAltura());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setContrasenya(usuarioDTO.getContrasenya());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setRol(rol);
        usuario.setSexo(usuarioDTO.getSexo());

        this.usuarioRepository.save(usuario);
    }

    public void saveWorkers(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).orElse(null);

        Set<Usuario> entrenadores = new HashSet<>();
        usuarioDTO.getEntrenadoresCliente().forEach(entrenador -> {
            Usuario entrenadorUsuario = this.usuarioRepository.findById(entrenador.getId()).orElse(null);
            entrenadores.add(entrenadorUsuario);
        });
        usuario.setEntrenadores(entrenadores);

        List<Usuario> diestistas = new ArrayList<>();
        usuarioDTO.getDiestistasCliente().forEach(dietista -> {
            Usuario diestistaUsuario = this.usuarioRepository.findById(dietista.getId()).orElse(null);
            diestistas.add(diestistaUsuario);
        });
        usuario.setDietistas(diestistas);

        this.usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        this.usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> filterUsers(String nombre,String apellido,String rol) {
        List<Usuario> usuarios = this.usuarioRepository.filterUsers(nombre,apellido,rol);
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        usuarios.forEach(usuario -> usuariosDTO.add(usuario.toDTO()));
        return usuariosDTO;
    }

    public List<UsuarioDTO> findAllByRol(int idRol) {
        List<Usuario> usuarios = this.usuarioRepository.findAllByRol(idRol);
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        usuarios.forEach(usuario -> usuariosDTO.add(usuario.toDTO()));
        return usuariosDTO;
    }
}
