package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.entity.Rutina;

import java.util.List;

@Service
public class RutinaService extends DTOService{

    @Autowired
    private RutinaRepository rutinaRepository;

    public List<RutinaDTO> buscarRutinas(List<Integer> rutinas) {
        List<Rutina> rutinasList = this.rutinaRepository.findAllById(rutinas);
        return this.entidadesADTO(rutinasList);
    }

    public RutinaDTO buscarRutina(Integer id) {
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        if(rutina != null){
            return rutina.toDTO();
        } else {
            return null;
        }
    }

}
