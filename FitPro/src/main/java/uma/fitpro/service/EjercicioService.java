package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.EjercicioRepository;
import uma.fitpro.dao.GrupoMuscularRepository;
import uma.fitpro.dao.TipoEjercicioRepository;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.GrupoMuscularDTO;
import uma.fitpro.dto.TipoEjercicioDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<EjercicioDTO> findAll() {
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        List<Ejercicio> ejercicios = this.ejercicioRepository.findAll();
        ejercicios.forEach(ejercicio -> ejerciciosDTO.add(ejercicio.toDTO()));
        return ejerciciosDTO;
    }

    public void save(EjercicioDTO ejercicioDTO) {
        Ejercicio ejercicio = null;
        if(ejercicioDTO.getId() != null) {
            ejercicio = ejercicioRepository.findById(ejercicioDTO.getId()).orElse(null);
        }
        if(ejercicio == null) {
            ejercicio = new Ejercicio();
        }
        ejercicio.setNombre(ejercicioDTO.getNombre());
        ejercicio.setDescripcion(ejercicioDTO.getDescripcion());
        ejercicio.setImagen(ejercicioDTO.getImagen());
        ejercicio.setVideo(ejercicioDTO.getVideo());
        ejercicio.setTipo(tipoEjercicioRepository.findById(ejercicioDTO.getTipo().getId()).orElse(null));
        ejercicio.setGrupoMuscular(grupoMuscularRepository.findById(ejercicioDTO.getGrupoMuscular().getId()).orElse(null));
        ejercicioRepository.save(ejercicio);
    }

    public void delete(int id) {
        ejercicioRepository.deleteById(id);
    }

    public List<EjercicioDTO> filterExercise(String nombre,String tipo,String grupo){
        List<Ejercicio> ejercicios = this.ejercicioRepository.filterExercise(nombre,tipo,grupo);
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        ejercicios.forEach(ejercicio -> ejerciciosDTO.add(ejercicio.toDTO()));
        return ejerciciosDTO;
    }

}
