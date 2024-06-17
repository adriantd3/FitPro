package uma.fitpro.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class DesempenyoSerieDTO implements SerieInterface{
    private Integer id;
    private Float metrica1;
    private Float metrica2;
    private Integer ejercicio;
    private Integer desempenyoSesion;

    public void setMetrica1(Float metrica1){
        this.metrica1 = Objects.requireNonNullElse(metrica1, 0f);
    }

    public void setMetrica2(Float metrica2){
        this.metrica2 = Objects.requireNonNullElse(metrica2, 0f);
    }
}
