package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.OrdenSesionRutinaDTO;

import java.io.Serializable;

@Entity
@Table(name = "orden_sesion_rutina")
public class OrdenSesionRutina implements Serializable, DTO<OrdenSesionRutinaDTO> {
    @EmbeddedId
    private OrdenSesionRutinaId id;

    @MapsId("sesionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sesion_id", nullable = false)
    private Sesion sesion;

    @MapsId("rutinaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;

    public OrdenSesionRutinaId getId() {
        return id;
    }

    public void setId(OrdenSesionRutinaId id) {
        this.id = id;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    @Override
    public OrdenSesionRutinaDTO toDTO() {
        OrdenSesionRutinaDTO ordenSesionRutinaDTO = new OrdenSesionRutinaDTO();
        ordenSesionRutinaDTO.setId(this.id.getOrden());
        ordenSesionRutinaDTO.setIdSesion(this.sesion.getId());
        ordenSesionRutinaDTO.setIdRutina(this.rutina.getId());
        ordenSesionRutinaDTO.setNombreSesion(this.sesion.getNombre());
        return ordenSesionRutinaDTO;
    }
}