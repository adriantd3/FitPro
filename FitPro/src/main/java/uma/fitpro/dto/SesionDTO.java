package uma.fitpro.dto;

import lombok.Data;

import java.util.List;

@Data
public class SesionDTO {
    private Integer id;
    private Integer rutinaId;
    private String nombre;
    private List<Integer> series;
}
