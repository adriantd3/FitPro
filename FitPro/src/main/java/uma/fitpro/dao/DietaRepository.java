package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
}
