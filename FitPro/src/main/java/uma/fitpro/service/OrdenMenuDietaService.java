package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.OrdenMenuDietaRepository;
import uma.fitpro.dto.OrdenMenuDietaDTO;
import uma.fitpro.entity.OrdenMenuDieta;
import uma.fitpro.entity.OrdenMenuDietaId;

@Service
public class OrdenMenuDietaService {

    @Autowired
    private OrdenMenuDietaRepository ordenMenuDietaRepository;

    public OrdenMenuDietaDTO findById(Integer ordenMenu, Integer dietaId, Integer menuId){
        OrdenMenuDietaId ordenMenuDietaId = new OrdenMenuDietaId();
        ordenMenuDietaId.setOrden(ordenMenu);
        ordenMenuDietaId.setDietaId(dietaId);
        ordenMenuDietaId.setMenuId(menuId);


        OrdenMenuDieta ordenMenuDieta = this.ordenMenuDietaRepository.findById(ordenMenuDietaId).orElse(null);
        if(ordenMenuDieta != null){
            return ordenMenuDieta.toDTO();
        } else {
            return null;
        }
    }
}