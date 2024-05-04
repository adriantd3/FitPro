package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.DesempenyoSerieId;

public interface DesempenyoSerieRepository extends JpaRepository<DesempenyoSerie, DesempenyoSerieId> {
}
