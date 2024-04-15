package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class EntrenadorClienteId implements java.io.Serializable {
    private static final long serialVersionUID = 1662596679100751008L;
    @Column(name = "entrenador_id", nullable = false)
    private Integer entrenadorId;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    public Integer getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(Integer entrenadorId) {
        this.entrenadorId = entrenadorId;
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
        EntrenadorClienteId entity = (EntrenadorClienteId) o;
        return Objects.equals(this.clienteId, entity.clienteId) &&
                Objects.equals(this.entrenadorId, entity.entrenadorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, entrenadorId);
    }

}