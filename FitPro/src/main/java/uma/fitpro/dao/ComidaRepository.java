package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Comida;

import java.util.List;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {
    @Query("select c from Comida c where c.nombre like concat('%', :nombre, '%') and c.calorias>=:kcal")
    List<Comida> buscarConFiltro(String nombre, float kcal);
}
