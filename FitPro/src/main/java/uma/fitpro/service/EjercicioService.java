package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.GrupoMuscular;
import uma.fitpro.entity.TipoEjercicio;

import java.util.ArrayList;
import java.util.List;

@Service
public class EjercicioService {
    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @Autowired
    protected TipoEjercicioRepository tipoEjercicioRepository;

    @Autowired
    protected GrupoMuscularRepository grupoMuscularRepository;

    public List<Ejercicio> filtrarEjercicioPorNombreMusculoYTipo(String nombre_ejercicio, String musculo, String tipo) {
        List<Ejercicio> ejercicios = ejercicios = ejercicioRepository.filtrarEjercicioPorNombreMusculoYTipo(nombre_ejercicio, musculo, tipo);
        return ejercicios;
    }

    public List<Ejercicio> filtrarEjercicioPorNombre(String nombre_ejercicio) {
        List<Ejercicio> ejercicios = new ArrayList<>();
        return ejercicios;
    }

    public List<TipoEjercicio> getTiposEjercicios() {
        List<TipoEjercicio> tipos = tipoEjercicioRepository.findAll();
        return tipos;
    }

    public List<GrupoMuscular> getGruposMusculares() {
        List<GrupoMuscular> grupos = grupoMuscularRepository.findAll();
        return grupos;
    }
}
