package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Comida;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public ComidaDTO findById(Integer id){
        Comida comida = this.comidaRepository.findById(id).orElse(null);
        if(comida != null){
            return comida.toDTO();
        } else {
            return null;
        }
    }
    public List<ComidaDTO> findAll(){
        List<ComidaDTO> comidasDTO = new ArrayList<>();
        List<Comida> comidas = comidaRepository.findAll();
        for(Comida comida : comidas){
            comidasDTO.add(comida.toDTO());
        }
        return comidasDTO;
    }
    
}
