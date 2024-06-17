package uma.fitpro.ui;

import uma.fitpro.dto.DesempenyoComidaDTO;

import java.util.List;

public class DesempenyoComidasForm {
    private List<DesempenyoComidaDTO> desempenyoComidas;

    public DesempenyoComidasForm(List<DesempenyoComidaDTO> desempenyoComidas) {
        setDesempenyoComidas(desempenyoComidas);
    }

    public List<DesempenyoComidaDTO> getDesempenyoComidas() {
        return desempenyoComidas;
    }

    public void setDesempenyoComidas(List<DesempenyoComidaDTO> desempenyoComidas) {
        this.desempenyoComidas = desempenyoComidas;
    }
}
