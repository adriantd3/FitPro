package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.dto.TipoEjercicioDTO;
import uma.fitpro.entity.TipoEjercicio;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoEjercicioService {

    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;

    public List<TipoEjercicioDTO> findAll() {
        List<TipoEjercicioDTO> tiposEjercicioDTO = new ArrayList<TipoEjercicioDTO>();
        List<TipoEjercicio> tiposEjercicio = this.tipoEjercicioRepository.findAll();
        tiposEjercicio.forEach(tipoEjercicio -> tiposEjercicioDTO.add(tipoEjercicio.toDTO()));
        return tiposEjercicioDTO;
    }

    public TipoEjercicioDTO findById(Integer id){
        TipoEjercicio tipoEjercicio = this.tipoEjercicioRepository.findById(id).orElse(null);
        if (tipoEjercicio != null) {
            return tipoEjercicio.toDTO();
        } else {
            return null;
        }
    }
}
