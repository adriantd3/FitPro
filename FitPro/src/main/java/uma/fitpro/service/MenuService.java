package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends DTOService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public MenuDTO findById(Integer id){
        MenuDTO menuDTO = null;
        if(id!=null){
            Menu menu = menuRepository.findById(id).orElse(null);
            menuDTO = menu.toDTO();
        }
        return menuDTO;
    }

    public List<ComidaDTO> buscarComidasMenu(List<Integer> comidas){
        List<Comida> comidasList = comidaRepository.findAllById(comidas);
        return this.entidadesADTO(comidasList);
    }

    public List<MenuDTO> findAll(){
        List<MenuDTO> menusDTO = new ArrayList<>();
        List<Menu> menus = menuRepository.findAll();
        for(Menu menu : menus){
            menusDTO.add(menu.toDTO());
        }
        return menusDTO;
    }
}
