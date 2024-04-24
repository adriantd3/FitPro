package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSesion;

import java.util.List;

public interface DesempenyoSesionRepository extends JpaRepository<DesempenyoSesion, Integer> {
    @Query("select d from DesempenyoSesion d where d.sesion.id = :sesion_id " +
            "and d.usuario.id = :cliente_id order by d.fecha asc")
    public List<DesempenyoSesion> findByClientIDAndSesionID(@Param("sesion_id") Integer sesion_id,
                                                            @Param("cliente_id") Integer cliente_id);
}
