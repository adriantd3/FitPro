package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class SerieId implements java.io.Serializable {
    private static final long serialVersionUID = -5319968942561611421L;
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sesion_id", nullable = false)
    private Integer sesionId;

    @Column(name = "ejercicio_id", nullable = false)
    private Integer ejercicioId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSesionId() {
        return sesionId;
    }

    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }

    public Integer getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Integer ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SerieId entity = (SerieId) o;
        return Objects.equals(this.sesionId, entity.sesionId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ejercicioId, entity.ejercicioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionId, id, ejercicioId);
    }

}