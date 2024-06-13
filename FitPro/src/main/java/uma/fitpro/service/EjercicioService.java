package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.GrupoMuscular;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.TipoEjercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EjercicioService {
    @Autowired
    protected EjercicioRepository ejercicioRepository;

    public Ejercicio getEjercicio(Integer id_ejercicio) {
        Ejercicio ejercicio = ejercicioRepository.findById(id_ejercicio).orElse(null);
        return ejercicio;
    }

    public List<Ejercicio> filtrarEjercicioPorNombreMusculoYTipo(String nombre_ejercicio, String musculo, String tipo) {
        List<Ejercicio> ejercicios = ejercicios = ejercicioRepository.filtrarEjercicioPorNombreMusculoYTipo(nombre_ejercicio, musculo, tipo);
        return ejercicios;
    }

    public List<Ejercicio> filtrarEjercicioPorNombre(String nombre_ejercicio) {
        List<Ejercicio> ejercicios = ejercicios = ejercicioRepository.filtrarEjercicioPorNombre(nombre_ejercicio);
        return ejercicios;
    }

    public void anyadirEjercicio(Map<Ejercicio, List<Serie>> mapa, Ejercicio ejercicio) {
        if (!mapa.containsKey(ejercicio)){
            mapa.put(ejercicio, new ArrayList<>());
        }
    }

}
