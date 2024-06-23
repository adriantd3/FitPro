package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Rutina;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Sesion;
import java.util.List;

import java.util.List;

/**
 * @author Víctor Pérez Armenta (100%)
 */

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
    @Query("select s from Sesion s where s.nombre like concat('%', :nombre , '%') ")
    List<Sesion> findByNombre(@Param("nombre") String nombre);

    @Query("select S from Sesion S, OrdenSesionRutina OSR where OSR.sesion = S and OSR.rutina = :rutina")
    List<Sesion> findAllByOrdenSesionRutina(Rutina rutina);
}
