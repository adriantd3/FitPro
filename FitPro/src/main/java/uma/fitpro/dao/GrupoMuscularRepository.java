package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.GrupoMuscular;
import uma.fitpro.entity.Sesion;

public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Integer> {
}