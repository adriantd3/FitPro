package uma.fitpro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DesempenyoSesionDTO {
    private Integer id;
    private Integer idSesion;
    private String nombreSesion;
    private LocalDate fecha;
    private boolean terminado;
    private List<Integer> desempenyoSeries;
}
