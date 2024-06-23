package uma.fitpro.ui;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FiltroEjercicio {
    private String nombre;
    private String grupoMuscular;

    public FiltroEjercicio() {
        setNombre("");
        setGrupoMuscular("");
    }
}
