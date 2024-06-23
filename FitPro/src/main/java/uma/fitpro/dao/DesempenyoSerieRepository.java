package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSerie;

import java.util.List;

/**
 * @author Adrián Torremocha Doblas (100%)
 */

public interface DesempenyoSerieRepository extends JpaRepository<DesempenyoSerie, Integer> {
    @Query("select d from DesempenyoSerie d order by d.ejercicio.id asc, d.id asc")
    public List<DesempenyoSerie> findAllOrdered();

    @Query("select d from DesempenyoSerie d where d.ejercicio.nombre like %:ejercicio% and d.desempenyoSesion.id = :desSesion_id and " +
            "(:grupo=0 or d.ejercicio.grupoMuscular.id = :grupo) and" +
            "(:tipo=0 or d.ejercicio.tipo.id = :tipo) " +
            "order by d.ejercicio.id asc, d.id asc")
    public List<DesempenyoSerie> buscarPorFiltro(@Param("ejercicio") String ejercicio,
                                                 @Param("grupo") Integer grupo,
                                                 @Param("tipo") Integer tipo,
                                                 @Param("desSesion_id") Integer desSesion_id);
}
