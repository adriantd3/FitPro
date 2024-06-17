package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.TipoEjercicioDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.TipoEjercicio;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComidaService extends DTOService{

    @Autowired
    ComidaRepository comidaRepository;

    public List<ComidaDTO> findAll() {
        List<ComidaDTO> comidasDTO = new ArrayList<ComidaDTO>();
        List<Comida> comidas = this.comidaRepository.findAll();
        comidas.forEach(comida -> comidasDTO.add(comida.toDTO()));
        return comidasDTO;
    }

    public ComidaDTO findById(Integer id){
        Comida comida = this.comidaRepository.findById(id).orElse(null);
        if (comida != null) {
            return comida.toDTO();
        } else {
            return null;
        }
    }

    public void save(ComidaDTO comidaDTO) {
        Comida comida = null;
        if(comidaDTO.getId() != null) comida = this.comidaRepository.findById(comidaDTO.getId()).orElse(null);
        if (comida == null) {
            comida = new Comida();
        }
        comida.setNombre(comidaDTO.getNombre());
        comida.setCalorias(comidaDTO.getCalorias());

        this.comidaRepository.save(comida);
    }

    public void delete(int id) {
        this.comidaRepository.deleteById(id);
    }

    public List<ComidaDTO> filterComida(String nombre, int calorias) {
        List<ComidaDTO> comidasDTO = new ArrayList<ComidaDTO>();
        List<Comida> comidas = this.comidaRepository.filterFood(nombre,calorias);
        comidas.forEach(comida -> comidasDTO.add(comida.toDTO()));
        return comidasDTO;
    }
}
