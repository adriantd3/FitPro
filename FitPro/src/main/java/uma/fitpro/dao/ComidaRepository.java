package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Comida;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {
}
