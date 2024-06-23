package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Dieta;

import java.util.List;

/**
 * @author David Bueno Carmona (100%)
 */

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    @Query("select d from Dieta d where d.nombre like concat('%', :nombre, '%') ")
    List<Dieta> buscarConFiltro(String nombre);
}
