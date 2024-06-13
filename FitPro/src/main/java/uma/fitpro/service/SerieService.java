package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.Sesion;

@Service
public class SerieService {

    @Autowired
    protected SerieRepository serieRepository;

    public Serie getSerie(int id_serie) {
        Serie serie = serieRepository.findById(id_serie).orElse(null);
        return serie;
    }

    public void crearSerie(Ejercicio ejercicio, Sesion sesion, String parametro1, String parametro2){
        Serie nueva_serie = new Serie();
        nueva_serie.setEjercicio(ejercicio);
        nueva_serie.setSesion(sesion);
        guardarSerie(nueva_serie, parametro1, parametro2);
    }

    public void guardarSerie(Serie serie, String parametro1, String parametro2){
        serie.setMetrica1(Float.parseFloat(parametro1));
        serie.setMetrica2(Float.parseFloat(parametro2));
        serieRepository.save(serie);
    }

    public void borrarSerie(Serie serie){
        serieRepository.delete(serie);
    }

}
