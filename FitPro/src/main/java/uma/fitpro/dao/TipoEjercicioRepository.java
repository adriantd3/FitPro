package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Sesion;
import uma.fitpro.entity.TipoEjercicio;

import java.util.List;

public interface TipoEjercicioRepository extends JpaRepository<TipoEjercicio, Integer> {

    @Query("select t from TipoEjercicio t where t.tipo like concat('%', :nombre, '%')  and t.metrica1 like concat('%', :m1, '%') and t.metrica2 like concat('%', :m2, '%')")
    public List<TipoEjercicio> filterTipos(@Param("nombre") String nombre, @Param("m1") String m1, @Param("m2") String m2);
}
