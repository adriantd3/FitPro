package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.OrdenSesionRutina;
import uma.fitpro.entity.OrdenSesionRutinaId;

import java.util.List;

/**
 * @author Adrián Torremocha Doblas (100%)
 */

public interface OrdenSesionRutinaRepository extends JpaRepository<OrdenSesionRutina, OrdenSesionRutinaId> {
    @Query("select o from OrdenSesionRutina o where o.id.rutinaId = :id order by o.id.orden asc")
    public List<OrdenSesionRutina> findOrdenSesionRutinaByRutinaID(@Param("id") Integer id);
}
