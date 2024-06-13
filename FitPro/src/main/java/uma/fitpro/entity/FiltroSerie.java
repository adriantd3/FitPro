package uma.fitpro.entity;

import java.util.Objects;

public class FiltroSerie {
    private int sesionId;
    private int desSesionId;
    private String ejercicio;
    private int grupoMuscular;
    private int tipoEjercicio;

    public FiltroSerie(int sesionId, int desSesionId){
        this.sesionId = sesionId;
        this.desSesionId = desSesionId;
        ejercicio = "";
        grupoMuscular = 0;
        tipoEjercicio = 0;
    }

    public int getSesionId() {
        return sesionId;
    }

    public int getDesSesionId() {
        return desSesionId;
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
}
