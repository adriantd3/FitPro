package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;

import java.util.List;

@Service
public class MenuService extends DTOService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public MenuDTO buscarMenu(Integer id){
        Menu menu = menuRepository.findById(id).orElse(null);
        return menu.toDTO();
    }

    public List<ComidaDTO> buscarComidasMenu(List<Integer> comidas){
        List<Comida> comidasList = comidaRepository.findAllById(comidas);
        return this.entidadesADTO(comidasList);
    }
}
