package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
