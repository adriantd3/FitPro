package uma.fitpro.dto;

import lombok.Data;

@Data
public class OrdenMenuDietaDTO implements Comparable<OrdenMenuDietaDTO>{
    private Integer orden;
    private MenuDTO menu;
    private Integer dietaId;
    private String nombreMenu;

    @Override
    public int compareTo(OrdenMenuDietaDTO omdDTO) {
        return orden.compareTo(omdDTO.getOrden());
    }
}
