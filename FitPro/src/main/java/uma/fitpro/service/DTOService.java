package uma.fitpro.service;

import uma.fitpro.dto.DTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián Torremocha Doblas (100%)
 */

public abstract class DTOService<DTOClass, EntityClass> {
    /**
        * Método que convierte una lista de entidades en una lista de DTOs
     */
    protected List<DTOClass> entidadesADTO (List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            lista.add(clase.toDTO());
        }
        return lista;
    }

}
