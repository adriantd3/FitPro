package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.FiltroSerie;
import uma.fitpro.entity.Serie;
import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    @Query("select d from Serie d order by d.ejercicio.id asc, d.id asc")
    public List<Serie> findAllOrdered();

    @Query("select s from Serie s " +
            "where s.ejercicio.nombre like %:#{#filtro.ejercicio}% and" +
            " s.peso >= :#{#filtro.peso} and " +
            " s.repeticiones >= :#{#filtro.repeticiones} and " +
            " s.distancia >= :#{#filtro.distancia} and " +
            " s.duracion >= :#{#filtro.duracion} and " +
            " s.descanso >= :#{#filtro.descanso}")
    public List<Serie> buscarPorFiltro(@Param("filtro") FiltroSerie filtro);
}
