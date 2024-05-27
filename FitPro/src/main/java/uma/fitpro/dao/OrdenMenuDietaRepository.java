package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.OrdenMenuDieta;
import uma.fitpro.entity.OrdenMenuDietaId;

public interface OrdenMenuDietaRepository extends JpaRepository<OrdenMenuDieta, OrdenMenuDietaId> {
}
