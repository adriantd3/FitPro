package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ComidaMenuId implements java.io.Serializable {
    private static final long serialVersionUID = -3089980288654169683L;
    @Column(name = "comida_id", nullable = false)
    private Integer comidaId;

    @Column(name = "menu_id", nullable = false)
    private Integer menuId;

    public Integer getComidaId() {
        return comidaId;
    }

    public void setComidaId(Integer comidaId) {
        this.comidaId = comidaId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComidaMenuId entity = (ComidaMenuId) o;
        return Objects.equals(this.menuId, entity.menuId) &&
                Objects.equals(this.comidaId, entity.comidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, comidaId);
    }

}