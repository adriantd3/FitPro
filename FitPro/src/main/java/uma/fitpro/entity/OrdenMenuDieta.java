package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orden_menu_dieta")
public class OrdenMenuDieta {
    @EmbeddedId
    private OrdenMenuDietaId id;

    @MapsId("menuId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @MapsId("dietaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;

    public OrdenMenuDieta(OrdenMenuDietaId id, Menu menu, Dieta dieta) {
        setId(id);
        setMenu(menu);
        setDieta(dieta);
    }

    public OrdenMenuDieta() {

    }

    public OrdenMenuDietaId getId() {
        return id;
    }

    public void setId(OrdenMenuDietaId id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

}