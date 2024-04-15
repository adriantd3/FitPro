package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "dietista_cliente")
public class DietistaCliente {
    @EmbeddedId
    private DietistaClienteId id;

    @MapsId("dietistaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dietista_id", nullable = false)
    private Usuario dietista;

    @MapsId("clienteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    public DietistaClienteId getId() {
        return id;
    }

    public void setId(DietistaClienteId id) {
        this.id = id;
    }

    public Usuario getDietista() {
        return dietista;
    }

    public void setDietista(Usuario dietista) {
        this.dietista = dietista;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

}