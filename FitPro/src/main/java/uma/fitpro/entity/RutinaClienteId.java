package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class RutinaClienteId implements java.io.Serializable {
    private static final long serialVersionUID = -865324923329763198L;
    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    public Integer getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(Integer rutinaId) {
        this.rutinaId = rutinaId;
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
        RutinaClienteId entity = (RutinaClienteId) o;
        return Objects.equals(this.clienteId, entity.clienteId) &&
                Objects.equals(this.rutinaId, entity.rutinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, rutinaId);
    }

}