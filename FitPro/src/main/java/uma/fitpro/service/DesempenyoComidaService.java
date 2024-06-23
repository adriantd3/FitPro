package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DesempenyoComidaRepository;
import uma.fitpro.dto.DesempenyoComidaDTO;
import uma.fitpro.dto.DesempenyoMenuDTO;
import uma.fitpro.dto.OrdenMenuDietaDTO;
import uma.fitpro.entity.DesempenyoComida;
import uma.fitpro.ui.FiltroDesempenyoComida;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesempenyoComidaService extends DTOService{

    @Autowired
    private DesempenyoComidaRepository desempenyoComidaRepository;

    public DesempenyoComidaDTO buscarDesempenyoComida(Integer id){
        DesempenyoComida desempenyoComida = desempenyoComidaRepository.findById(id).orElse(null);
        if(desempenyoComida == null) {
            return null;
        }
        return desempenyoComida.toDTO();
    }

    public List<DesempenyoComidaDTO> buscarDesempenyosComida(List<Integer> ids){
        List<DesempenyoComida> desempenyoComidaList = desempenyoComidaRepository.findAllById(ids);
        return this.entidadesADTO(desempenyoComidaList);
    }

    public List<DesempenyoComidaDTO> filtroBuscarDesempenyosComida(FiltroDesempenyoComida filtro){
        Byte comido = filtro.getComido() == null ? (byte) 2 : (byte) (filtro.getComido() ? 1 : 0);
        Byte gustado = filtro.getGustado() == null ? (byte) 2 : (byte) (filtro.getGustado() ? 1 : 0);
        List<DesempenyoComida> desempenyoComidaList = desempenyoComidaRepository.findByFiltroMenu(filtro.getDesMenuId(),
                filtro.getNombre(), filtro.getCalorias(), comido, gustado);
        return this.entidadesADTO(desempenyoComidaList);
    }

    public void guardarDesempenyoComida(DesempenyoComidaDTO desComida){
        DesempenyoComida desempenyoComida = desempenyoComidaRepository.findById(desComida.getId()).orElse(null);

        desempenyoComida.setComido(desComida.isComido() ? (byte) 1 : (byte) 0);
        desempenyoComida.setGustado(desComida.isGustado() ? (byte) 1 : (byte) 0);

        desempenyoComidaRepository.save(desempenyoComida);
    }


    public List<DesempenyoComidaDTO> buscarDesempenyosComidaDesempenyoMenu(DesempenyoMenuDTO desempenyoMenu) {
        List<DesempenyoComidaDTO> desempenyoComidasDTO = new ArrayList<>();
        if(desempenyoMenu!=null){
            List<DesempenyoComida> desempenyoComidas = desempenyoComidaRepository.findAllById(desempenyoMenu.getDesempenyoComidas());
            desempenyoComidasDTO = this.entidadesADTO(desempenyoComidas);
        }
        return desempenyoComidasDTO;
    }
}
