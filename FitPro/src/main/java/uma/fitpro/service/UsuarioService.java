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
}
