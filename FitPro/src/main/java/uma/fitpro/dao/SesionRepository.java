package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Sesion;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
}
