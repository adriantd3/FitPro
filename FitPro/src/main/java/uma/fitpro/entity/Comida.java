package uma.fitpro.entity;

import jakarta.persistence.*;
import uma.fitpro.dto.ComidaDTO;
import uma.fitpro.dto.DTO;

import java.io.Serializable;

@Entity
@Table(name = "comida")
public class Comida implements Serializable, DTO<ComidaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "calorias", nullable = false)
    private Integer calorias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    @Override
    public ComidaDTO toDTO() {
        ComidaDTO comidaDTO = new ComidaDTO();
        comidaDTO.setId(id);
        comidaDTO.setNombre(nombre);
        comidaDTO.setCalorias(calorias);
        return comidaDTO;
    }
}