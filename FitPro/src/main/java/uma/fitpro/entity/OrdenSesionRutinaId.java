package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class OrdenSesionRutinaId implements java.io.Serializable {
    private static final long serialVersionUID = -757661686870980449L;
    @Column(name = "sesion_id", nullable = false)
    private Integer sesionId;

    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    public Integer getSesionId() {
        return sesionId;
    }

    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }

    public Integer getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(Integer rutinaId) {
        this.rutinaId = rutinaId;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrdenSesionRutinaId entity = (OrdenSesionRutinaId) o;
        return Objects.equals(this.sesionId, entity.sesionId) &&
                Objects.equals(this.rutinaId, entity.rutinaId) &&
                Objects.equals(this.orden, entity.orden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionId, rutinaId, orden);
    }

}