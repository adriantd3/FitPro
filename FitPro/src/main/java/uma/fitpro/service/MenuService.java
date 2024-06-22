package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.ComidaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;
import uma.fitpro.ui.FiltroMenu;

import java.util.List;

@Service
public class MenuService extends DTOService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public MenuDTO buscarMenu(Integer menuId){
        MenuDTO menuDTO = null;
        if(menuId!=null){
            Menu menu = menuRepository.findById(menuId).orElse(null);
            if(menu!=null){
                menuDTO = menu.toDTO();
            }
        }
        return menuDTO;
    }

    public List<ComidaDTO> buscarComidasMenu(List<Integer> comidas){
        List<Comida> comidasList = comidaRepository.findAllById(comidas);
        return this.entidadesADTO(comidasList);
    }

    public List<MenuDTO> findAll(){
        List<Menu> menus = menuRepository.findAll();
        return this.entidadesADTO(menus);
    }

    public void anyadirComidaAMenu(Integer menuId, Integer comidaId) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        Comida comida = comidaRepository.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();

        comidasMenu.add(comida);
        menu.setComidas(comidasMenu);
        menu.updateKcal();

        menuRepository.save(menu);
    }

    public void eliminarComidaDeMenu(Integer menuId, Integer comidaId) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        Comida comida = comidaRepository.findById(comidaId).orElse(null);
        List<Comida> comidasMenu = menu.getComidas();

        comidasMenu.remove(comida);
        menu.setComidas(comidasMenu);
        menu.updateKcal();

        menuRepository.save(menu);
    }

    public void guardarMenu(Integer menuId, String nombre){
        Menu menu = null;
        if(menuId == null) {
            menu = new Menu();
        }else{
            menu = menuRepository.findById(menuId).orElse(new Menu());
        }
        if(!nombre.equals("")){
            menu.setNombre(nombre);
        }
        menu.updateKcal();

        menuRepository.save(menu);
    }

    public void borrarMenu(Integer menuId){
        menuRepository.deleteById(menuId);
    }

    public List<MenuDTO> filtrar(FiltroMenu filtroMenu) {
        List<Menu> menus = menuRepository.buscarConFiltro(filtroMenu.getNombre(), filtroMenu.getFloatKcal(), filtroMenu.getLocalDateFecha());

        return this.entidadesADTO(menus);
    }
}
