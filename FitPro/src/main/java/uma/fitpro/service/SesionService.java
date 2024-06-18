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


}
