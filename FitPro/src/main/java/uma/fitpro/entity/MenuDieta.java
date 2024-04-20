package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orden_menu_dieta")
public class MenuDieta {
    @Id
    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Id
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;

    @Id
    @Column(name = "dieta_id", nullable = false)
    private Integer dietaId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Integer getOrden() {
        return orden;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Integer getDietaId() {
        return dietaId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}