package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.GrupoMuscularDTO;
import uma.fitpro.dto.TipoEjercicioDTO;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.GrupoMuscular;
import uma.fitpro.entity.TipoEjercicio;

import java.util.List;

@Service
public class EjercicioService extends DTOService{

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    public EjercicioDTO buscarEjercicio(Integer id){
        Ejercicio ejercicio = ejercicioRepository.findById(id).orElse(null);
        if(ejercicio == null){
            return null;
        }
        return ejercicio.toDTO();
    }

    public List<TipoEjercicioDTO> listarTiposEjercicio(){
        List<TipoEjercicio> tiposEjercicio = tipoEjercicioRepository.findAll();
        return this.entidadesADTO(tiposEjercicio);
    }

    public List<GrupoMuscularDTO> listarGruposMusculares(){
        List<GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();
        return this.entidadesADTO(gruposMusculares);
    }

}
