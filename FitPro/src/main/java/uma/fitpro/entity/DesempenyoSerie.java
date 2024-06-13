package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "desempenyo_serie")
public class DesempenyoSerie implements SerieInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "desempenyo_sesion_id", nullable = false)
    private DesempenyoSesion desempenyoSesion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @Column(name = "metrica1")
    private Float metrica1;

    @Column(name = "metrica2")
    private Float metrica2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DesempenyoSesion getDesempenyoSesion() {
        return desempenyoSesion;
    }

    public void setDesempenyoSesion(DesempenyoSesion desempenyoSesion) {
        this.desempenyoSesion = desempenyoSesion;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Float getMetrica1() {
        return metrica1;
    }

    public void setMetrica1(Float metrica1) {
        this.metrica1 = metrica1;
    }

    public Float getMetrica2() {
        return metrica2;
    }

    public void setMetrica2(Float metrica2) {
        this.metrica2 = metrica2;
    }

}