package uma.fitpro.dto;

import lombok.Data;

@Data
public class DesempenyoComidaDTO {
    private Integer id;
    private ComidaDTO comida;
    private Integer desempenyoMenu;
    private boolean comido;
    private boolean gustado;
}
