package uma.fitpro.dto;

import lombok.Data;

@Data
public class OrdenMenuDietaDTO {
    private Integer orden;
    private MenuDTO menu;
    private Integer dietaId;
    private String nombreMenu;
}
