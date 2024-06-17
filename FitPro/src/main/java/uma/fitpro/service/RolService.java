package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.RolRepository;
import uma.fitpro.dto.RolDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.Rol;
import uma.fitpro.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService extends DTOService {

    @Autowired
    public RolRepository rolRepository;

    public RolDTO findById(Integer id){
        Rol rol = this.rolRepository.findById(id).orElse(null);
        if (rol != null) {
            return rol.toDTO();
        } else {
            return null;
        }
    }

    public List<RolDTO> findAll() {
        List<RolDTO> rolesDTO = new ArrayList<RolDTO>();
        List<Rol> roles = this.rolRepository.findAll();
        roles.forEach(rol -> rolesDTO.add(rol.toDTO()));
        return rolesDTO;
    }
}
