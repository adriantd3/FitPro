package uma.fitpro.ui;

import java.time.LocalDate;

public class FiltroDesempenyoMenu {
    String fecha;
    int clienteId;
    int dietaId;
    int menuId;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDietaId() {
        return dietaId;
    }

    public void setDietaId(int dietaId) {
        this.dietaId = dietaId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public FiltroDesempenyoMenu() {
        fecha = "";
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


    public boolean estaVacio () {
        return fecha.isEmpty();

    }
}
