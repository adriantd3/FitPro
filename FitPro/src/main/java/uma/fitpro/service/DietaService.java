package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DietaRepository;
import uma.fitpro.dto.DietaDTO;
import uma.fitpro.entity.Dieta;

import java.util.List;

@Service
public class DietaService extends DTOService{

    @Autowired
    private DietaRepository dietaRepository;

    public List<DietaDTO> buscarDietas(List<Integer> dietas){
        List<Dieta> dietasList = dietaRepository.findAllById(dietas);
        return this.entidadesADTO(dietasList);
    }

    public DietaDTO buscarDieta(Integer id){
        Dieta dieta = dietaRepository.findById(id).orElse(null);
        if(dieta != null){
            return dieta.toDTO();
        } else {
            return null;
        }
    }

}
