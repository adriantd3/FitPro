package uma.fitpro.entity;

import jakarta.persistence.*;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.SesionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sesion")
public class Sesion implements Serializable, DTO<SesionDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @OneToMany(mappedBy = "sesion",fetch = FetchType.EAGER)
    @OrderBy("ejercicio.id ASC, id ASC")
    private List<Serie> series = new ArrayList<>();

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

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    @Override
    public SesionDTO toDTO() {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(this.id);
        sesionDTO.setNombre(this.nombre);

        List<Integer> series = new ArrayList<>();
        for (Serie serie : this.series) {
            series.add(serie.getId());
        }
        sesionDTO.setSeries(series);

        return sesionDTO;
    }
}