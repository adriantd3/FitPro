package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Ejercicio;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    @Query("select e from Ejercicio e where e.nombre like concat('%', :filtro, '%')")
    List<Ejercicio> filtrarEjercicio(@Param("filtro") String filtro);

}
