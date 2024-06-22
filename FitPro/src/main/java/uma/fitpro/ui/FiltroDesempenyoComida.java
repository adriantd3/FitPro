package uma.fitpro.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class FiltroDesempenyoComida {
    private Integer desMenuId;
    private String nombre;
    private Integer calorias;
    private Boolean comido;
    private Boolean gustado;

    public FiltroDesempenyoComida(Integer desMenuId){
        this.desMenuId = desMenuId;
        this.calorias = 0;
        nombre = "";
    }

    public void setCalorias(Integer calorias){
        this.calorias = Objects.requireNonNullElse(calorias, 0);
    }
}
