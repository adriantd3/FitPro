package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rutina")
public class Rutina {
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
    private Set<OrdenSesionRutina> ordenSesionRutinas = new LinkedHashSet<>();

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

    public Set<OrdenSesionRutina> getOrdenSesionRutinas() {
        return ordenSesionRutinas;
    }

    public void setOrdenSesionRutinas(Set<OrdenSesionRutina> ordenSesionRutinas) {
        this.ordenSesionRutinas = ordenSesionRutinas;
    }

}