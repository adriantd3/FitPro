package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.Sesion;
import uma.fitpro.ui.FiltroSerie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SerieService extends DTOService{

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private EjercicioService ejercicioService;

    public List<SerieDTO> buscarSeriesSesion(Integer id) {
        Sesion sesion = sesionRepository.findById(id).orElse(null);
        if (sesion == null) {
            return null;
        }
        return this.entidadesADTO(sesion.getSeries());
    }

    public List<SerieDTO> buscarSeries(List<Integer> series) {
        List<Serie> seriesList = serieRepository.findAllById(series);
        return this.entidadesADTO(seriesList);
    }

    public List<SerieDTO> filtroBuscarSeries(FiltroSerie filtro) {
        List<Serie> seriesList = serieRepository.buscarPorFiltro(filtro.getEjercicio(),
                filtro.getGrupoMuscular(), filtro.getTipoEjercicio(), filtro.getSesionId());
        return this.entidadesADTO(seriesList);
    }

    public Map<EjercicioDTO, List<SerieDTO>> buscarSeriesDictionary(List<Integer> series) {
        List<SerieDTO> seriesList = this.buscarSeries(series);
        return this.dictFromSeries(seriesList);
    }

    public Map<EjercicioDTO, List<SerieDTO>> filtroBuscarSeriesDictionary(FiltroSerie filtro) {
        List<SerieDTO> seriesList = this.filtroBuscarSeries(filtro);
        return this.dictFromSeries(seriesList);
    }

    private Map<EjercicioDTO, List<SerieDTO>> dictFromSeries(List<SerieDTO> seriesList) {
        Map<EjercicioDTO, List<SerieDTO>> sesion_dict = new LinkedHashMap<>();
        for (SerieDTO serie : seriesList) {
            EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(serie.getEjercicio());
            if (!sesion_dict.containsKey(ejercicio)) {
                sesion_dict.put(ejercicio, new ArrayList<>());
            }
            sesion_dict.get(ejercicio).add(serie);
        }

        return sesion_dict;
    }
}
