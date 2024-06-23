package uma.fitpro.ui;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * @Author Víctor Pérez Armenta
 */
public class FiltroEjercicio {
    private String nombre;
    private String grupoMuscular;

    public FiltroEjercicio() {
        setNombre("");
        setGrupoMuscular("");
    }
}
