package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Serie;
;import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    @Query("select d from Serie d order by d.ejercicio.id asc, d.id asc")
    public List<Serie> findAllOrdered();
}
