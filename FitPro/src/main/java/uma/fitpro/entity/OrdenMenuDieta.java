package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orden_menu_dieta")
public class OrdenMenuDieta {
    @EmbeddedId
    private OrdenMenuDietaId id;

    @MapsId("dietaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    public OrdenMenuDietaId getId() {
        return id;
    }

    public void setId(OrdenMenuDietaId id) {
        this.id = id;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}