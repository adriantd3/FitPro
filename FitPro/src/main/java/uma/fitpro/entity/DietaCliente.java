package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "dieta_cliente")
public class DietaCliente {
    @EmbeddedId
    private DietaClienteId id;

    @MapsId("dietaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;

    @MapsId("clienteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    public DietaClienteId getId() {
        return id;
    }

    public void setId(DietaClienteId id) {
        this.id = id;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

}