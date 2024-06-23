package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Menu;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * @author José Ángel Bueno Ruiz (100%)
 */

public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query("select m from Menu m where m.nombre like concat('%', :nombre, '%') and m.calorias>=:kcal and m.fechaCreacion >= :fecha")
    List<Menu> buscarConFiltro(String nombre, float kcal, LocalDate fecha);

    @Query("select m from Menu m where m.nombre like concat('%', :nombre, '%') and m.calorias>=:kcal")
    List<Menu> buscarConFiltro(String nombre, float kcal);
}
