package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DietaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dao.OrdenMenuDietaRepository;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.DietaDTO;
import uma.fitpro.dto.MenuDTO;
import uma.fitpro.dto.OrdenMenuDietaDTO;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrdenMenuDietaService extends DTOService{

    @Autowired
    private OrdenMenuDietaRepository ordenMenuDietaRepository;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private MenuRepository menuRepository;

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

    public List<OrdenMenuDietaDTO> buscarOrdenMenuDieta(DietaDTO dieta) {
        List<OrdenMenuDietaDTO> ordenMenuDietasDTO = new ArrayList<>();
        if(dieta!=null){
            List<OrdenMenuDieta> ordenMenuDietasList = ordenMenuDietaRepository.findAllById(dieta.getOrdenMenuDietaList());
            ordenMenuDietasDTO = this.entidadesADTO(ordenMenuDietasList);
            Collections.sort(ordenMenuDietasDTO);
        }
        return ordenMenuDietasDTO;
    }
}