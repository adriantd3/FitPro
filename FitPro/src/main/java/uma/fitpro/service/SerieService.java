package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.SerieRepository;
import uma.fitpro.dao.SesionRepository;

@Service
public class SerieService {

    @Autowired
    protected SesionRepository sesionRepository;

    @Autowired
    protected SerieRepository serieRepository;

    @Autowired
    protected EjercicioRepository ejercicioRepository;






}
