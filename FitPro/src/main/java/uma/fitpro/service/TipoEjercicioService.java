package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.entity.TipoEjercicio;

import java.util.List;

@Service
public class TipoEjercicioService {
    @Autowired
    protected TipoEjercicioRepository tipoEjercicioRepository;

    public List<TipoEjercicio> getTiposEjercicios() {
        List<TipoEjercicio> tipos = tipoEjercicioRepository.findAll();
        return tipos;
    }
}
