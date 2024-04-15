package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DesempenyoComidaId implements java.io.Serializable {
    private static final long serialVersionUID = 1913994913560191093L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comida_id", nullable = false)
    private Integer comidaId;

    @Column(name = "desempenyo_menu_id", nullable = false)
    private Integer desempenyoMenuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComidaId() {
        return comidaId;
    }

    public void setComidaId(Integer comidaId) {
        this.comidaId = comidaId;
    }

    public Integer getDesempenyoMenuId() {
        return desempenyoMenuId;
    }

    public void setDesempenyoMenuId(Integer desempenyoMenuId) {
        this.desempenyoMenuId = desempenyoMenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DesempenyoComidaId entity = (DesempenyoComidaId) o;
        return Objects.equals(this.desempenyoMenuId, entity.desempenyoMenuId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.comidaId, entity.comidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desempenyoMenuId, id, comidaId);
    }

}