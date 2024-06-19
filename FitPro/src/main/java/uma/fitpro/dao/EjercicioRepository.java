package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Ejercicio;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    @Query("select e from Ejercicio e where e.nombre like concat('%', :filtro, '%')")
    List<Ejercicio> filtrarEjercicioPorNombre(@Param("filtro") String filtro);

    @Query("select e from Ejercicio e where e.nombre like concat('%', :filtro, '%') " +
            "and e.grupoMuscular.grupoMuscular like concat('%', :musculo, '%') " +
            "and  e.tipo.tipo like concat('%', :tipo, '%')")
    List<Ejercicio> filtrarEjercicioPorNombreMusculoYTipo(@Param("filtro") String filtro,
                                                    @Param("musculo") String musculo,
                                                    @Param("tipo") String tipo);

}
