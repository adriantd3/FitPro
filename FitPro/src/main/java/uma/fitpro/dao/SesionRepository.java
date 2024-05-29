package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.OrdenSesionRutina;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Sesion;

import java.util.List;
import java.util.Set;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {

    @Query("select S from Sesion S, OrdenSesionRutina OSR where OSR.sesion = S and OSR.rutina = :rutina")
    List<Sesion> findAllByOrdenSesionRutina(Rutina rutina);
}
