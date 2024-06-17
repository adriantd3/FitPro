package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.RolRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Rol;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService extends DTOService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

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

        Set<Usuario> diestistas = new HashSet<>();
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
