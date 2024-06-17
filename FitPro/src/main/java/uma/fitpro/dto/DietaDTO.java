package uma.fitpro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DietaDTO {
    private Integer id;
    private UsuarioDTO dietista;
    private String nombre;
    private LocalDate fechaCreacion;
    private List<OrdenMenuDietaDTO> ordenMenuDietaList;
}
