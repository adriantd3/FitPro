package uma.fitpro.dto;

import lombok.Data;

@Data
public class EjercicioDTO implements Comparable<EjercicioDTO> {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String video;
    private TipoEjercicioDTO tipo;
    private GrupoMuscularDTO grupoMuscular;

    @Override
    public int compareTo(EjercicioDTO o) {
        return this.id.compareTo(o.id);
    }
}
