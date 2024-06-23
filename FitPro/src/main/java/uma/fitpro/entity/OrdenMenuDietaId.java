package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class OrdenMenuDietaId implements java.io.Serializable {
    private static final long serialVersionUID = -155756576260801405L;
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;

    @Column(name = "dieta_id", nullable = false)
    private Integer dietaId;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    public OrdenMenuDietaId(int menuId, int dietaId, int orden) {
        setMenuId(menuId);
        setDietaId(dietaId);
        setOrden(orden);
    }

    public OrdenMenuDietaId() {

    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getDietaId() {
        return dietaId;
    }

    public void setDietaId(Integer dietaId) {
        this.dietaId = dietaId;
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
        OrdenMenuDietaId entity = (OrdenMenuDietaId) o;
        return Objects.equals(this.menuId, entity.menuId) &&
                Objects.equals(this.dietaId, entity.dietaId) &&
                Objects.equals(this.orden, entity.orden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, dietaId, orden);
    }

}