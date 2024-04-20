package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orden_sesion_rutina")
public class SesionRutina {
    @Id
    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Id
    @Column(name = "sesion_id", nullable = false)
    private Integer sesionId;

    @Id
    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sesion_id", nullable = false)
    private Sesion sesion;

    public Integer getOrden() {
        return orden;
    }

    public Integer getSesionId() {
        return sesionId;
    }

    public Integer getRutinaId() {
        return rutinaId;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

}