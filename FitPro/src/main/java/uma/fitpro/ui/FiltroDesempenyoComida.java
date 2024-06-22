package uma.fitpro.ui;

import java.time.LocalDate;

<<<<<<<< HEAD:FitPro/src/main/java/uma/fitpro/ui/FiltroMenu.java
public class FiltroMenu {
    String nombre;
    String kcal;
    String fecha;
    int dietaId;

    public int getDietaId() {
        return dietaId;
    }

    public void setDietaId(int dietaId) {
        this.dietaId = dietaId;
    }


    public FiltroMenu() {
========
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
>>>>>>>> main:FitPro/src/main/java/uma/fitpro/ui/FiltroDesempenyoComida.java
        nombre = "";
        kcal = "";
        fecha = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getKcal() {
        return kcal;
    }

    public float getFloatKcal() {
        if(kcal.isEmpty()){
            return 0f;
        }else {
            return Float.parseFloat(kcal);
        }
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public boolean estaVacio () {
        return nombre.isEmpty() && kcal.isEmpty() && fecha.isEmpty();

    }
}
