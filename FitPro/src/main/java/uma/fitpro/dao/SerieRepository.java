package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Serie;
;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
}
