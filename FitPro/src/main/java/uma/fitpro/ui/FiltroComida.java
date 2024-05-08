package uma.fitpro.ui;

public class FiltroComida {
    String nombre;
    String kcal;

    public String getKcal() {
        return kcal;
    }

    public float getFloatKcal() {
        return kcal.isEmpty()? 0f : Float.parseFloat(kcal);
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean estaVacio() {
        return nombre.isEmpty() && kcal.isEmpty();
    }
}
