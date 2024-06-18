package uma.fitpro.ui;

public class FiltroDieta {
    private String nombre;
    private int clienteId;

    public FiltroDieta(){
        nombre ="";
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

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
