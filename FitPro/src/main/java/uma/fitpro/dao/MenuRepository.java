package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query("select c from Comida c, ComidaMenu cm, Menu m  where m.id = :menuId AND cm.menu.id = m.id AND cm.comida.id = c.id")
    List<Comida> findComidasMenuById(@Param("menuId") Integer id);

}
