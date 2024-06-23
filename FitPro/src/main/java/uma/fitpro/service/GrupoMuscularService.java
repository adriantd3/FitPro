package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.dto.GrupoMuscularDTO;
import uma.fitpro.entity.GrupoMuscular;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Bueno Carmona (100%)
 */

@Service
public class GrupoMuscularService {

    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;

    public List<GrupoMuscularDTO> findAll() {
        List<GrupoMuscularDTO> gruposMuscularesDTO = new ArrayList<>();
        List<GrupoMuscular> gruposMusculares = this.grupoMuscularRepository.findAll();
        gruposMusculares.forEach(grupoMuscular -> gruposMuscularesDTO.add(grupoMuscular.toDTO()));
        return gruposMuscularesDTO;
    }

    public GrupoMuscularDTO findById(Integer id){
        GrupoMuscular grupoMuscular = this.grupoMuscularRepository.findById(id).orElse(null);
        if(grupoMuscular != null){
            return grupoMuscular.toDTO();
        } else {
            return null;
        }
    }
}
