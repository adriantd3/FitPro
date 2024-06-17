package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.dto.SesionDTO;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.Sesion;
import uma.fitpro.ui.FiltroSerie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SesionService extends DTOService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private SerieRepository serieRepository;

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

    public Sesion getSesion(Integer id_sesion){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        return sesion;
    }

    public List<Sesion> getSesiones(){
        List<Sesion> sesiones = sesionRepository.findAll();
        return sesiones;
    }

    public List<Sesion> filtrarSesiones(String nombre){
        List<Sesion> sesiones = sesionRepository.findByNombre(nombre);
        return sesiones;
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

    public Map<Integer,List<String>> getEjercicioParametros(){
        HashMap<Integer, List<String>> mapa = new HashMap<>();
        mapa.put(1, Arrays.asList("Peso (kg)", "Repeticiones"));
        mapa.put(2, Arrays.asList("Distancia (m)", "Duracion (seg)"));
        mapa.put(3, Arrays.asList("Duracion (seg)", "Descanso (seg)"));
        mapa.put(4, Arrays.asList("Repeticiones", "Descanso (seg)"));
        mapa.put(5, Arrays.asList("Repeticiones", "Descanso (seg)"));

        return mapa;
    }

    public Map<Ejercicio, List<Serie>> getEjercicioYSeries(Sesion sesion){

        List<Serie> series = new ArrayList<>(sesion.getSeries());
        Map<Ejercicio, List<Serie>> mapa = new TreeMap<>();
        for (Serie e : series){
            List<Serie> s = mapa.get(e.getEjercicio());
            if (s==null){
                s = new ArrayList<>();
            }
            s.add(e);
            mapa.put(e.getEjercicio(), s);
        }
        return mapa;
    }


}
