package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.OrdenSesionRutinaDTO;
import uma.fitpro.dto.RutinaDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rutina")
public class Rutina implements Serializable, DTO<RutinaDTO> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Usuario entrenador;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "rutina")
    private List<OrdenSesionRutina> ordenSesionRutinas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Usuario entrenador) {
        this.entrenador = entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<OrdenSesionRutina> getOrdenSesionRutinas() {
        return ordenSesionRutinas;
    }

    public void setOrdenSesionRutinas(List<OrdenSesionRutina> ordenSesionRutinas) {
        this.ordenSesionRutinas = ordenSesionRutinas;
    }

    @Override
    public RutinaDTO toDTO() {
        RutinaDTO rutinaDTO = new RutinaDTO();
        rutinaDTO.setId(this.id);
        rutinaDTO.setEntrenador(this.entrenador.toDTO());
        rutinaDTO.setNombre(this.nombre);
        rutinaDTO.setFechaCreacion(this.fechaCreacion);

        List<OrdenSesionRutinaDTO> ordenSesionRutinaList = new ArrayList<>();
        for (OrdenSesionRutina ordenSesionRutina : this.ordenSesionRutinas) {
            ordenSesionRutinaList.add(ordenSesionRutina.toDTO());
        }
        rutinaDTO.setOrdenSesionRutinaList(ordenSesionRutinaList);
        
        return rutinaDTO;
    }
}