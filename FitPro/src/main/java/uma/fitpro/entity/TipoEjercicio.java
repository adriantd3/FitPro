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

    @Column(name = "metrica1", nullable = false, length = 45)
    private String metrica1;

    @Column(name = "metrica2", nullable = false, length = 45)
    private String metrica2;

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

    public String getMetrica1() {
        return metrica1;
    }

    public void setMetrica1(String metrica1) {
        this.metrica1 = metrica1;
    }

    public String getMetrica2() {
        return metrica2;
    }

    public void setMetrica2(String metrica2) {
        this.metrica2 = metrica2;
    }

    @Override
    public TipoEjercicioDTO toDTO() {
        TipoEjercicioDTO dto = new TipoEjercicioDTO();
        dto.setId(id);
        dto.setTipo(tipo);
        dto.setMetrica1(metrica1);
        dto.setMetrica2(metrica2);
        return dto;
    }
}