package uma.fitpro.dto;

import lombok.Data;

@Data
public class OrdenMenuDietaDTO {
    private Integer id;
    private Integer menuId;
    private Integer dietaId;
    private String nombreMenu;
}
