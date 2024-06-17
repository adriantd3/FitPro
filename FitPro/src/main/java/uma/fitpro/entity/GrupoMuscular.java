package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.GrupoMuscularDTO;

import java.io.Serializable;

@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular implements Serializable, DTO<GrupoMuscularDTO> {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "grupo_muscular", nullable = false, length = 45)
    private String grupoMuscular;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    @Override
    public GrupoMuscularDTO toDTO() {
        GrupoMuscularDTO dto = new GrupoMuscularDTO();
        dto.setId(id);
        dto.setGrupoMuscular(grupoMuscular);
        return dto;
    }
}