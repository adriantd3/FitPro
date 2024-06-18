package uma.fitpro.ui;

public class FiltroCliente {
    private String nombre;
    private String apellidos;
    private String sourcePage;

    public String getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(String sourcePage) {
        this.sourcePage = sourcePage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean estaVacio() {
        return nombre.isEmpty() && apellidos.isEmpty();
    }
}
