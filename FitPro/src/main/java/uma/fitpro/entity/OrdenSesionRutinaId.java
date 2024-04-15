package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class OrdenSesionRutinaId implements java.io.Serializable {
    private static final long serialVersionUID = -1624627268546094973L;
    @Column(name = "sesion_id", nullable = false)
    private Integer sesionId;

    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrdenSesionRutinaId entity = (OrdenSesionRutinaId) o;
        return Objects.equals(this.sesionId, entity.sesionId) &&
                Objects.equals(this.rutinaId, entity.rutinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionId, rutinaId);
    }

}