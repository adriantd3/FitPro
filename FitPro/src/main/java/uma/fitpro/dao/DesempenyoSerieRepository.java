package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.FiltroSerie;

import java.util.List;

public interface DesempenyoSerieRepository extends JpaRepository<DesempenyoSerie, Integer> {
    @Query("select d from DesempenyoSerie d order by d.ejercicio.id asc, d.id asc")
    public List<DesempenyoSerie> findAllOrdered();

    @Query("select s from DesempenyoSerie s " +
            "where s.ejercicio.nombre like %:#{#filtro.ejercicio}% and" +
            " s.peso >= :#{#filtro.peso} and " +
            " s.repeticiones >= :#{#filtro.repeticiones} and " +
            " s.distancia >= :#{#filtro.distancia} and " +
            " s.duracion >= :#{#filtro.duracion} and " +
            " s.descanso >= :#{#filtro.descanso}")
    public List<DesempenyoSerie> buscarPorFiltro(@Param("filtro") FiltroSerie filtro);
}
