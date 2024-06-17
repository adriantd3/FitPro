package uma.fitpro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RutinaDTO {
    private Integer id;
    private UsuarioDTO entrenador;
    private String nombre;
    private LocalDate fechaCreacion;
    private List<OrdenSesionRutinaDTO> ordenSesionRutinaList;
}
