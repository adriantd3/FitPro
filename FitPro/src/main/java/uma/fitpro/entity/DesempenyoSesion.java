package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "desempenyo_sesion")
public class DesempenyoSesion {

    @EmbeddedId
    private DesempenyoSesionId id;

    @MapsId("sesionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sesion_id", nullable = false)
    private Sesion sesion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "desempenyo_sesion_id", referencedColumnName = "id", nullable = false)
    private Set<DesempenyoSerie> desempenyosSeries = new LinkedHashSet<>();

    public DesempenyoSesionId getId() {
        return id;
    }

    public void setId(DesempenyoSesionId id) {
        this.id = id;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<DesempenyoSerie> getDesempenyosSeries() {
        return desempenyosSeries;
    }

    public void setDesempenyosSeries(Set<DesempenyoSerie> desempenyosSeries) {
        this.desempenyosSeries = desempenyosSeries;
    }

}