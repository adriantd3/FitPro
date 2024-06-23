package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.dto.TipoEjercicioDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.TipoEjercicio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Bueno Carmona (100%)
 */

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

    public void save(TipoEjercicioDTO tipoDTO) {
        TipoEjercicio tipo = null;
        if(tipoDTO.getId() != null) tipo = this.tipoEjercicioRepository.findById(tipoDTO.getId()).orElse(null);
        if (tipo == null) {
            tipo = new TipoEjercicio();
        }
        tipo.setTipo(tipoDTO.getTipo());
        tipo.setMetrica1(tipoDTO.getMetrica1());
        tipo.setMetrica2(tipoDTO.getMetrica2());

        this.tipoEjercicioRepository.save(tipo);
    }

    public void delete(Integer id) {
        this.tipoEjercicioRepository.deleteById(id);
    }

    public List<TipoEjercicioDTO> filterTipos(String nombre, String m1, String m2) {
        List<TipoEjercicioDTO> tiposEjercicioDTO = new ArrayList<TipoEjercicioDTO>();
        List<TipoEjercicio> tiposEjercicio = this.tipoEjercicioRepository.filterTipos(nombre, m1, m2);
        tiposEjercicio.forEach(tipoEjercicio -> tiposEjercicioDTO.add(tipoEjercicio.toDTO()));
        return tiposEjercicioDTO;
    }
}
