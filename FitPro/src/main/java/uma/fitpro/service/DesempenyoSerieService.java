package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.DesempenyoSerieRepository;
import uma.fitpro.dao.DesempenyoSesionRepository;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dto.DesempenyoSerieDTO;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.DesempenyoSesion;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;
import uma.fitpro.ui.FiltroSerie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DesempenyoSerieService extends DTOService{

    @Autowired
    private DesempenyoSerieRepository desempenyoSerieRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private DesempenyoSesionRepository desempenyoSesionRepository;

    public DesempenyoSerieDTO buscarDesempenyoSerie(Integer id){
        DesempenyoSerie desempenyoSerie = desempenyoSerieRepository.findById(id).orElse(null);
        if(desempenyoSerie != null){
            return desempenyoSerie.toDTO();
        } else {
            return null;
        }
    }

    public List<DesempenyoSerieDTO> buscarDesempenyoSeries(List<Integer> desempenyoSeries){
        List<DesempenyoSerie> desempenyoSerieList = new ArrayList<>();
        for(Integer id: desempenyoSeries){
            DesempenyoSerie desempenyoSerie = desempenyoSerieRepository.findById(id).orElse(null);
            if(desempenyoSerie != null){
                desempenyoSerieList.add(desempenyoSerie);
            }
        }
        return this.entidadesADTO(desempenyoSerieList);
    }

    public List<DesempenyoSerieDTO> filtroBuscarSeries(FiltroSerie filtro) {
        List<DesempenyoSerie> seriesList = desempenyoSerieRepository.buscarPorFiltro(filtro.getEjercicio(),
                filtro.getGrupoMuscular(), filtro.getTipoEjercicio(), filtro.getDesSesionId());
        return this.entidadesADTO(seriesList);
    }

    public Map<EjercicioDTO,List<DesempenyoSerieDTO>> buscarDesempenyoSeriesDictionary(List<Integer> series){
        List<DesempenyoSerieDTO> seriesList = this.buscarDesempenyoSeries(series);
        return this.dictFromDesempenyoSeries(seriesList);
    }

    public Map<EjercicioDTO, List<DesempenyoSerieDTO>> filtroBuscarDesempenyoSeriesDictionary(FiltroSerie filtro) {
        List<DesempenyoSerieDTO> seriesList = this.filtroBuscarSeries(filtro);
        return this.dictFromDesempenyoSeries(seriesList);
    }

    public DesempenyoSerieDTO nuevoDesempenyoSerie(Integer desempenyoSesionID, Integer ejercicioId){
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicioId).orElse(null);
        DesempenyoSesion desempenyoSesion = desempenyoSesionRepository.findById(desempenyoSesionID).orElse(null);

        DesempenyoSerie desempenyoSerie = new DesempenyoSerie();
        desempenyoSerie.setDesempenyoSesion(desempenyoSesion);
        desempenyoSerie.setEjercicio(ejercicio);
        desempenyoSerieRepository.save(desempenyoSerie);

        return desempenyoSerie.toDTO();
    }

    public void borrarDesempenyoSerie(Integer id){
        desempenyoSerieRepository.deleteById(id);
    }

    public void guardarDesempenyoSerie(DesempenyoSerieDTO desempenyoSerieDTO){
        DesempenyoSerie desempenyoSerie = desempenyoSerieRepository.findById(desempenyoSerieDTO.getId()).orElse(null);
        if(desempenyoSerie != null){
            desempenyoSerie.setMetrica1(desempenyoSerieDTO.getMetrica1());
            desempenyoSerie.setMetrica2(desempenyoSerieDTO.getMetrica2());
            desempenyoSerieRepository.save(desempenyoSerie);
        }
    }

    private Map<EjercicioDTO, List<DesempenyoSerieDTO>> dictFromDesempenyoSeries(List<DesempenyoSerieDTO> seriesList){
        Map<EjercicioDTO,List<DesempenyoSerieDTO>> sesion_dict = new LinkedHashMap<>();
        for(DesempenyoSerieDTO serie: seriesList) {
            EjercicioDTO ejercicio = ejercicioRepository.findById(serie.getEjercicio()).orElse(null).toDTO();
            if(!sesion_dict.containsKey(ejercicio)) {
                sesion_dict.put(ejercicio, new ArrayList<>());
            }
            sesion_dict.get(ejercicio).add(serie);
        }

        return sesion_dict;
    }
}
