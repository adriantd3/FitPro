package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.FiltroSerie;
import uma.fitpro.entity.Serie;
;import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    @Query("select d from Serie d order by d.ejercicio.id asc, d.id asc")
    public List<Serie> findAllOrdered();

    @Query("select d from Serie d where d.ejercicio.nombre like %:ejercicio% and d.sesion.id = :sesion_id and " +
            "(:grupo=0 or d.ejercicio.grupoMuscular.id = :grupo) and" +
            "(:tipo=0 or d.ejercicio.tipo.id = :tipo) " +
            "order by d.ejercicio.id asc, d.id asc")
    public List<Serie> buscarPorFiltro(@Param("ejercicio") String ejercicio,
                                                 @Param("grupo") Integer grupo,
                                                 @Param("tipo") Integer tipo,
                                                 @Param("sesion_id") Integer sesion_id);

}
