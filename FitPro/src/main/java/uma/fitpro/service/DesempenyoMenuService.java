package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DesempenyoComidaRepository;
import uma.fitpro.dao.DesempenyoMenuRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.DesempenyoComidaDTO;
import uma.fitpro.dto.DesempenyoMenuDTO;
import uma.fitpro.entity.*;
import uma.fitpro.ui.FiltroDesempenyoMenu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DesempenyoMenuService extends DTOService{

    @Autowired
    private DesempenyoMenuRepository desempenyoMenuRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DesempenyoComidaRepository desempenyoComidaRepository;

    public DesempenyoMenuDTO buscarDesempenyoMenu(Integer id){
        DesempenyoMenu desempenyoMenu = desempenyoMenuRepository.findById(id).orElse(null);
        if(desempenyoMenu == null){
            return null;
        }
        return desempenyoMenu.toDTO();
    }

    public List<DesempenyoMenuDTO> buscarDesempenyosMenuPorClienteYMenu(Integer cliente_id, Integer menu_id){
        List<DesempenyoMenu> desempenyoMenuList =
                desempenyoMenuRepository.findByClientIDAndMenuID(cliente_id, menu_id);
        return this.entidadesADTO(desempenyoMenuList);
    }

    public DesempenyoMenuDTO nuevoDesempenyoMenu(Integer menu_id, Integer cliente_id){
        Menu menu = menuRepository.findById(menu_id).orElse(null);
        Usuario cliente = usuarioRepository.findById(cliente_id).orElse(null);

        DesempenyoMenu desempenyoMenu = new DesempenyoMenu();
        desempenyoMenu.setMenu(menu);
        desempenyoMenu.setUsuario(cliente);
        desempenyoMenu.setTerminado((byte) 0);
        desempenyoMenu.setFechaCreacion(LocalDate.now());

        desempenyoMenuRepository.save(desempenyoMenu);

        for(Comida comida : menu.getComidas()){
            DesempenyoComida desempenyoComida = crearDesempenyoComidaFromComida(comida, desempenyoMenu);
            desempenyoComidaRepository.save(desempenyoComida);
        }

        return desempenyoMenu.toDTO();
    }

    public void terminarDesempenyoMenu(Integer id){
        DesempenyoMenu desempenyoMenu = desempenyoMenuRepository.findById(id).orElse(null);
        if(desempenyoMenu != null){
            desempenyoMenu.setTerminado((byte) 1);
            desempenyoMenuRepository.save(desempenyoMenu);
        }
    }

    public void cancelarDesempenyoMenu(Integer id){
        desempenyoMenuRepository.deleteById(id);
    }

    private DesempenyoComida crearDesempenyoComidaFromComida(Comida comida, DesempenyoMenu desempenyoMenu){
        DesempenyoComida desempenyoComida = new DesempenyoComida();

        desempenyoComida.setComida(comida);
        desempenyoComida.setDesempenyoMenu(desempenyoMenu);
        desempenyoComida.setComido((byte) (0));
        desempenyoComida.setGustado((byte) (0));

        return desempenyoComida;
    }

    public List<DesempenyoMenuDTO> filtrar(FiltroDesempenyoMenu filtroDesempenyoMenu) {
        List<DesempenyoMenu> desempenyosMenu = new ArrayList<>();
        if(filtroDesempenyoMenu.estaVacio()){
            desempenyosMenu = desempenyoMenuRepository.findByClientIDAndMenuID(filtroDesempenyoMenu.getClienteId(), filtroDesempenyoMenu.getMenuId());
        }else{
            desempenyosMenu = desempenyoMenuRepository.findByClientIDAndMenuIDAndDate(filtroDesempenyoMenu.getClienteId(), filtroDesempenyoMenu.getMenuId(), filtroDesempenyoMenu.getLocalDateFecha());
        }
        return this.entidadesADTO(desempenyosMenu);
    }
}
