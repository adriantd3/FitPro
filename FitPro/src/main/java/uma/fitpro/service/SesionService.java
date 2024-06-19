package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.dto.SesionDTO;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.Sesion;
import uma.fitpro.ui.FiltroSerie;

import java.util.*;

@Service
public class SesionService extends DTOService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private EjercicioService ejercicioService;

    public SesionDTO buscarSesion(Integer id) {
        Sesion sesion = sesionRepository.findById(id).orElse(null);
        if (sesion != null) {
            return sesion.toDTO();
        } else {
            return null;
        }
    }

    public List<SesionDTO> buscarSesiones(List<Integer> sesiones) {
        List<Sesion> sesionesList = sesionRepository.findAllById(sesiones);
        return this.entidadesADTO(sesionesList);
    }

    public List<SesionDTO> getSesiones(){
        List<Sesion> sesiones = sesionRepository.findAll();
        return this.entidadesADTO(sesiones);
    }

    public List<SesionDTO> filtrarSesiones(String nombre){
        List<Sesion> sesiones = sesionRepository.findByNombre(nombre);
        return this.entidadesADTO(sesiones);
    }

    public void borrarSesion(Integer id_sesion){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        sesionRepository.delete(sesion);
    }

    public void nuevaSesion(String nombre) {
        Sesion sesion = new Sesion();
        sesion.setNombre(nombre);
        sesionRepository.save(sesion);
    }

    public Map<EjercicioDTO, List<SerieDTO>> getEjercicioYSeries(SesionDTO sesionDTO){
        Sesion sesion = sesionRepository.findById(sesionDTO.getId()).orElse(null);
        List<Serie> series = new ArrayList<>(sesion.getSeries());
        List<SerieDTO> seriesDTOS = this.entidadesADTO(series);
        Map<EjercicioDTO, List<SerieDTO>> mapa = new TreeMap<>();
        for (SerieDTO serie : seriesDTOS){
            Integer idEjercicio = serie.getEjercicio();
            EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(idEjercicio);
            List<SerieDTO> seriesEjercicio = mapa.get(ejercicio);
            if (seriesEjercicio==null){
                seriesEjercicio = new ArrayList<>();
            }
            seriesEjercicio.add(serie);
            mapa.put(ejercicio, seriesEjercicio);
        }
        return mapa;
    }


}
