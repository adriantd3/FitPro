package uma.fitpro.entity;

import java.util.Objects;

public class FiltroSerie {
    private String ejercicio;
    private float peso;
    private int repeticiones;
    private float distancia;
    private int duracion;
    private int descanso;
    private int grupoMuscular;
    private int tipoEjercicio;

    public FiltroSerie(){
        ejercicio = "";
        peso = 0;
        repeticiones = 0;
        distancia = 0;
        duracion = 0;
        descanso = 0;
        grupoMuscular = 0;
        tipoEjercicio = 0;
    }

    public int getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(int grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public int getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(int tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = Objects.requireNonNullElse(ejercicio, "");
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }
}
