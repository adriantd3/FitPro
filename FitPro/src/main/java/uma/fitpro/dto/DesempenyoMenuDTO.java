package uma.fitpro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DesempenyoMenuDTO {
    private Integer id;
    private Integer menuId;
    private String nombreMenu;
    private Float caloriasMenu;
    private LocalDate fechaCreacion;
    private boolean terminado;
    private List<Integer> desempenyoComidas;
}
