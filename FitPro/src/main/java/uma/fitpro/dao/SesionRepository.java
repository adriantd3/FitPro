package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Sesion;
import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {

}
