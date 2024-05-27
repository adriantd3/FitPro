package uma.fitpro.ui;

public class FiltroDieta {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean estaVacio() {
        return nombre.isEmpty();
    }
}
