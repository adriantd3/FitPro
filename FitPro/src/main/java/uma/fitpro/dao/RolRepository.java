package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;
import uma.fitpro.entity.Rol;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Integer> {


}