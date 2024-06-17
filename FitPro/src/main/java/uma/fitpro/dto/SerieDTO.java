package uma.fitpro.dto;

import lombok.Data;

@Data
public class SerieDTO implements SerieInterface{
    private Integer id;
    private Float metrica1;
    private Float metrica2;
    private Integer ejercicio;
    private Integer sesion;
}
