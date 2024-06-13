package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.entity.GrupoMuscular;

import java.util.List;

@Service
public class GrupoMuscularService {
    @Autowired
    protected GrupoMuscularRepository grupoMuscularRepository;

    public List<GrupoMuscular> getGruposMusculares() {
        List<GrupoMuscular> grupos = grupoMuscularRepository.findAll();
        return grupos;
    }
}
