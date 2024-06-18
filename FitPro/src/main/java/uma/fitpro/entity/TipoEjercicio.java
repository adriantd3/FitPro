package uma.fitpro.entity;

import jakarta.persistence.*;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.TipoEjercicioDTO;

import java.io.Serializable;

@Entity
@Table(name = "tipo_ejercicio")
public class TipoEjercicio implements Serializable, DTO<TipoEjercicioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public TipoEjercicioDTO toDTO() {
        TipoEjercicioDTO dto = new TipoEjercicioDTO();
        dto.setId(id);
        dto.setTipo(tipo);
        return dto;
    }
}