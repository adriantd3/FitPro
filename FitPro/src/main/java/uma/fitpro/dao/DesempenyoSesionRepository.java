package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSesion;

import java.util.List;

/**
 * @author Adrián Torremocha Doblas (100%)
 */

public interface DesempenyoSesionRepository extends JpaRepository<DesempenyoSesion, Integer> {
    @Query("select d from DesempenyoSesion d where d.sesion.id = :sesion_id " +
            "and d.usuario.id = :cliente_id order by d.fecha asc, d.id asc")
    public List<DesempenyoSesion> findByClientIDAndSesionID(@Param("cliente_id") Integer cliente_id,
                                                            @Param("sesion_id") Integer sesion_id);
}
