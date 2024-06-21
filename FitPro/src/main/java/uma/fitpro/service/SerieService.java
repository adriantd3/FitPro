package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.dto.SesionDTO;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.Sesion;
import uma.fitpro.ui.FiltroSerie;
import uma.fitpro.utils.ComparatorSerie;
import uma.fitpro.utils.SortedList;

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
    private EjercicioRepository ejercicioRepository;

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

    public Map<EjercicioDTO, SortedList<SerieDTO>> buscarSeriesDictionary(List<Integer> series) {
        List<SerieDTO> seriesList = this.buscarSeries(series);
        return this.dictFromSeries(seriesList);
    }

    public Map<EjercicioDTO, SortedList<SerieDTO>> filtroBuscarSeriesDictionary(FiltroSerie filtro) {
        List<SerieDTO> seriesList = this.filtroBuscarSeries(filtro);
        return this.dictFromSeries(seriesList);
    }

    private Map<EjercicioDTO, SortedList<SerieDTO>> dictFromSeries(List<SerieDTO> seriesList) {
        Map<EjercicioDTO, SortedList<SerieDTO>> sesion_dict = new LinkedHashMap<>();
        for (SerieDTO serie : seriesList) {
            EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(serie.getEjercicio());
            sesion_dict.computeIfAbsent(ejercicio, k -> new SortedList<>(new ComparatorSerie()));
            sesion_dict.get(ejercicio).add(serie);
        }

        return sesion_dict;
    }

    public SerieDTO buscarSerie(int id_serie) {
        Serie serie = serieRepository.findById(id_serie).orElse(null);
        if (serie != null) {
            return serie.toDTO();
        } else {
            return null;
        }
    }

    public Integer crearSerie(EjercicioDTO ejercicioDTO, SesionDTO sesionDTO, String parametro1, String parametro2){
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicioDTO.getId()).orElse(null);
        Sesion sesion = sesionRepository.findById(sesionDTO.getId()).orElse(null);
        Serie nueva_serie = new Serie();
        nueva_serie.setEjercicio(ejercicio);
        nueva_serie.setSesion(sesion);
        nueva_serie.setMetrica1(Float.parseFloat(parametro1));
        nueva_serie.setMetrica2(Float.parseFloat(parametro2));
        serieRepository.save(nueva_serie);

        return nueva_serie.getId();
    }

    public void guardarSerie(SerieDTO serieDTO, String parametro1, String parametro2){
        Serie serie = serieRepository.findById(serieDTO.getId()).orElse(null);
        serie.setMetrica1(Float.parseFloat(parametro1));
        serie.setMetrica2(Float.parseFloat(parametro2));
        serieRepository.save(serie);
    }

    public void borrarSerie(SerieDTO serieDTO){
        Serie serie = serieRepository.findById(serieDTO.getId()).orElse(null);
        serieRepository.delete(serie);
    }
}
