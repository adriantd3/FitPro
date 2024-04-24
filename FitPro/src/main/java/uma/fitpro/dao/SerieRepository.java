package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Serie;
import uma.fitpro.entity.SerieId;
;

public interface SerieRepository extends JpaRepository<Serie, SerieId> {
}
