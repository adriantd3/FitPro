package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "desempenyo_comida")
public class DesempenyoComida {
    @EmbeddedId
    private DesempenyoComidaId id;

    @MapsId("comidaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @Column(name = "comido", nullable = false)
    private Byte comido;

    @Column(name = "gustado", nullable = false)
    private Byte gustado;

    public DesempenyoComidaId getId() {
        return id;
    }

    public void setId(DesempenyoComidaId id) {
        this.id = id;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Byte getComido() {
        return comido;
    }

    public void setComido(Byte comido) {
        this.comido = comido;
    }

    public Byte getGustado() {
        return gustado;
    }

    public void setGustado(Byte gustado) {
        this.gustado = gustado;
    }

}