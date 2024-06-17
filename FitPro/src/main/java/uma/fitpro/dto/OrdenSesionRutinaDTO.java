package uma.fitpro.dto;

import lombok.Data;

@Data
public class OrdenSesionRutinaDTO {
    private Integer id;
    private Integer idSesion;
    private Integer idRutina;
    private String nombreSesion;
}
