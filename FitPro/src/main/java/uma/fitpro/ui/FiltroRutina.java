package uma.fitpro.ui;

import java.time.LocalDate;
import java.util.Objects;

public class FiltroRutina {
    private String nombre;
    private LocalDate fechaCreacion;
    private Integer numeroSesiones;

    public FiltroRutina() {
        setNombre("");
        setFechaCreacion(LocalDate.of(2024, 1, 1));
        setNumeroSesiones(0);
    }

    public FiltroRutina(String nombre, LocalDate fechaCreacion, Integer numero_sesiones) {
        setNombre(nombre);
        setFechaCreacion(fechaCreacion);
        setNumeroSesiones(numero_sesiones);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion){
        this.fechaCreacion = Objects.requireNonNullElseGet(fechaCreacion, () -> LocalDate.of(2024, 1, 1));
        System.out.printf("FECHA: %s\n", this.fechaCreacion);
    }

    public Integer getNumeroSesiones() {
        return numeroSesiones;
    }

    public void setNumeroSesiones(Integer numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }
}
