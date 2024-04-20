package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DesempenyoSerieId implements java.io.Serializable {
    private static final long serialVersionUID = -5979552480324033094L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "desempenyo_sesion_id", nullable = false)
    private Integer desempenyoSesionId;

    @Column(name = "serie_id", nullable = false)
    private Integer serieId;

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

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DesempenyoSerieId entity = (DesempenyoSerieId) o;
        return Objects.equals(this.desempenyoSesionId, entity.desempenyoSesionId) &&
                Objects.equals(this.serieId, entity.serieId) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desempenyoSesionId, serieId, id);
    }

}