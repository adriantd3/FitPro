package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DietistaClienteId implements java.io.Serializable {
    private static final long serialVersionUID = -8825886229126603720L;
    @Column(name = "dietista_id", nullable = false)
    private Integer dietistaId;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    public Integer getDietistaId() {
        return dietistaId;
    }

    public void setDietistaId(Integer dietistaId) {
        this.dietistaId = dietistaId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DietistaClienteId entity = (DietistaClienteId) o;
        return Objects.equals(this.dietistaId, entity.dietistaId) &&
                Objects.equals(this.clienteId, entity.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dietistaId, clienteId);
    }

}