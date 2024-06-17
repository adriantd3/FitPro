package uma.fitpro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MenuDTO {
    private Integer id;
    private String nombre;
    private Float calorias;
    private LocalDate fechaCreacion;
    private List<Integer> comidas;
}
