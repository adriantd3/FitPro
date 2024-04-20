package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "desempenyo_menu")
public class DesempenyoMenu {
    @EmbeddedId
    private DesempenyoMenuId id;

    @MapsId("menuId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "desempenyo_menu_id", referencedColumnName = "id", nullable = false)
    private Set<DesempenyoComida> desempenyosComidas = new LinkedHashSet<>();

    public DesempenyoMenuId getId() {
        return id;
    }

    public void setId(DesempenyoMenuId id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<DesempenyoComida> getDesempenyosComidas() {
        return desempenyosComidas;
    }

    public void setDesempenyosComidas(Set<DesempenyoComida> desempenyosComidas) {
        this.desempenyosComidas = desempenyosComidas;
    }

}