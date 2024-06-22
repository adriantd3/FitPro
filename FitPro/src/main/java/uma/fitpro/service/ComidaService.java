package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Comida;
import uma.fitpro.ui.FiltroComida;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComidaService extends DTOService{

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
        List<Comida> comidas = comidaRepository.findAll();
        return this.entidadesADTO(comidas);
    }

    public List<ComidaDTO> buscarComidasMenu(MenuDTO menu){
        List<ComidaDTO> comidasDTO = new ArrayList<>();
        if(menu!=null){
            List<Comida> comidasList = comidaRepository.findAllById(menu.getComidas());
            comidasDTO = this.entidadesADTO(comidasList);
        }
        return comidasDTO;
    }

    public List<ComidaDTO> filtrar(FiltroComida filtroComida){
        List<Comida> comidas = comidaRepository.buscarConFiltro(filtroComida.getNombre(), filtroComida.getFloatKcal());

        return this.entidadesADTO(comidas);
    }
}
