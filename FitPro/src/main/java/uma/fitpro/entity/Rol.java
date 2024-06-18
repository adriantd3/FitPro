package uma.fitpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.RolDTO;

import java.io.Serializable;

@Entity
@Table(name = "rol")
public class Rol implements Serializable, DTO<RolDTO> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public RolDTO toDTO() {
        RolDTO rol = new RolDTO();
        rol.setId(id);
        rol.setNombre(nombre);

        return rol;
    }
}