package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.DesempenyoSerie;
import java.util.List;

public interface DesempenyoSerieRepository extends JpaRepository<DesempenyoSerie, Integer> {
    @Query("select d from DesempenyoSerie d order by d.ejercicio.id asc, d.id asc")
    public List<DesempenyoSerie> findAllOrdered();
}
