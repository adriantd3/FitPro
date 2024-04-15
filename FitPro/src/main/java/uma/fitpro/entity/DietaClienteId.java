package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DietaClienteId implements java.io.Serializable {
    private static final long serialVersionUID = 4224760261265079211L;
    @Column(name = "dieta_id", nullable = false)
    private Integer dietaId;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    public Integer getDietaId() {
        return dietaId;
    }

    public void setDietaId(Integer dietaId) {
        this.dietaId = dietaId;
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
        DietaClienteId entity = (DietaClienteId) o;
        return Objects.equals(this.clienteId, entity.clienteId) &&
                Objects.equals(this.dietaId, entity.dietaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, dietaId);
    }

}