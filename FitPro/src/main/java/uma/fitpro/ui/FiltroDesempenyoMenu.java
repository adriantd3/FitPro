package uma.fitpro.ui;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class FiltroDesempenyoMenu {
    String fecha;
    int clienteId;
    int dietaId;
    int menuId;

    public FiltroDesempenyoMenu() {
        fecha = "";
    }

    public LocalDate getLocalDateFecha() {
        if(fecha.isEmpty()){
            return LocalDate.of(1,1,1);
        }else {
            String[] datosFecha = fecha.split("-");
            if(datosFecha.length != 3){
                return LocalDate.of(1,1,1);
            }else{
                return LocalDate.of(Integer.parseInt(datosFecha[0]), Integer.parseInt(datosFecha[1]), Integer.parseInt(datosFecha[2]));
            }
        }
    }

    public boolean estaVacio () {
        return fecha.isEmpty();
    }
}
