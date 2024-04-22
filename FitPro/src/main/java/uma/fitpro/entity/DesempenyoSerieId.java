package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DesempenyoSerieId implements java.io.Serializable {
    private static final long serialVersionUID = 7292908933193740579L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "desempenyo_sesion_id", nullable = false)
    private Integer desempenyoSesionId;

    @Column(name = "ejercicio_id", nullable = false)
    private Integer ejercicioId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDesempenyoSesionId() {
        return desempenyoSesionId;
    }

    public void setDesempenyoSesionId(Integer desempenyoSesionId) {
        this.desempenyoSesionId = desempenyoSesionId;
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
        DesempenyoSerieId entity = (DesempenyoSerieId) o;
        return Objects.equals(this.desempenyoSesionId, entity.desempenyoSesionId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ejercicioId, entity.ejercicioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desempenyoSesionId, id, ejercicioId);
    }

}