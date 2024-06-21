package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DietaRepository;
import uma.fitpro.dao.MenuRepository;
import uma.fitpro.dao.OrdenMenuDietaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.*;
import uma.fitpro.dto.DietaDTO;
import uma.fitpro.entity.*;
import uma.fitpro.entity.Dieta;
import uma.fitpro.ui.FiltroDieta;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietaService extends DTOService{

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrdenMenuDietaRepository ordenMenuDietaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<DietaDTO> buscarDietas(List<Integer> dietas){
        List<Dieta> dietasList = dietaRepository.findAllById(dietas);
        return this.entidadesADTO(dietasList);
    }

    public DietaDTO buscarDieta(Integer id){
        Dieta dieta = dietaRepository.findById(id).orElse(null);
        if(dieta != null){
            return dieta.toDTO();
        } else {
            return null;
        }
    }

    public DietaDTO findById(Integer dietaId){
        DietaDTO dietaDTO = null;
        if(dietaId!=null){
            Dieta dieta = dietaRepository.findById(dietaId).orElse(null);
            if(dieta != null){
                dietaDTO = dieta.toDTO();
            }
        }
        return dietaDTO;
    }

    public List<DietaDTO> findAll(){
        List<Dieta> dietas = dietaRepository.findAll();
        return this.entidadesADTO(dietas);
    }

    public void anyadirMenuADieta(Integer menuId, Integer dietaId) {
        Dieta dieta = dietaRepository.findById(dietaId).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        int orden = dieta.getOrdenMenuDietas().size()+1;

        if(orden < 8){
            OrdenMenuDietaId ordenMenuDietaId = new OrdenMenuDietaId(menuId, dietaId, orden);
            OrdenMenuDieta ordenMenuDieta = new OrdenMenuDieta(ordenMenuDietaId, menu, dieta);
            ordenMenuDietaRepository.save(ordenMenuDieta);
        }
    }

    public void eliminarMenuDeDieta(Integer menuId, Integer dietaId, Integer ordenMenu) {
        OrdenMenuDietaId ordenMenuDietaId = new OrdenMenuDietaId();
        ordenMenuDietaId.setOrden(ordenMenu);
        ordenMenuDietaId.setDietaId(dietaId);
        ordenMenuDietaId.setMenuId(menuId);

        OrdenMenuDieta ordenMenuDieta = ordenMenuDietaRepository.findById(ordenMenuDietaId).orElse(null);

        ordenMenuDietaRepository.delete(ordenMenuDieta);
    }

    public void guardarDieta(Integer dietaId, String nombre, UsuarioDTO dietistaDTO){
        Usuario dietista = usuarioRepository.findById(dietistaDTO.getId()).orElse(null);
        Dieta dieta = null;
        if(dietaId == null) {
            dieta = new Dieta();
        }else{
            dieta = dietaRepository.findById(dietaId).orElse(new Dieta());
        }
        if(!nombre.equals("")){
            dieta.setNombre(nombre);
        }
        dieta.setDietista(dietista);
        dietaRepository.save(dieta);
    }

    public void borrarDieta(Integer dietaId){
        dietaRepository.deleteById(dietaId);
    }

    public List<DietaDTO> filtrar(FiltroDieta filtroDieta) {
        List<Dieta> dietas = dietaRepository.buscarConFiltro(filtroDieta.getNombre());

        return this.entidadesADTO(dietas);
    }

    public List<DietaDTO> buscarDietasCliente(UsuarioDTO cliente) {
        List<DietaDTO> dietasDTO = new ArrayList<>();
        if(cliente!=null){
            List<Dieta> dietasList = dietaRepository.findAllById(cliente.getDietasCliente());
            dietasDTO = this.entidadesADTO(dietasList);
        }
        return dietasDTO;
    }
}
