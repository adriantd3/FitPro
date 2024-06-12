package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Sesion;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
    @Query("select s from Sesion s where s.nombre like concat('%', :nombre , '%') ")
    public List<Sesion> findByNombre(@Param("nombre") String nombre);
}
