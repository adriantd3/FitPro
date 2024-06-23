package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;
import uma.fitpro.ui.FiltroRutina;

import java.util.List;
import java.util.Set;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Ezequiel Sánchez García (40%)
 * @author Víctor Pérez Armenta (60%)
 */

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.entrenador.id = :entrenador and r.id not in :rutinas")
    List<Rutina> getRestantesRutinasByEntrenador(@Param("entrenador") Integer entrenador,
                                                 @Param("rutinas") List<Integer> rutinas);

    @Query("select r from Rutina r where r.entrenador.id = :entrenador")
    List<Rutina> getRutinasByEntrenador(@Param("entrenador") Integer entrenador);

    @Query("select r from Rutina r where r.entrenador.id = :entrenador " +
            "and r.nombre like concat('%', :nombre, '%') and r.fechaCreacion >= :fecha")
    List<Rutina> getFilteredRutinasByEntrenadorAndFecha(@Param("entrenador") Integer entrenador,
                                                        @Param("nombre") String nombre,
                                                        @Param("fecha") LocalDate fecha);

    @Query("select R from Rutina R where R.id not in :rutinasCliente")
    List<Rutina> getRutinasByNotInRutinasCliente(List<Integer> rutinasCliente);

    @Query("select R from Rutina R where R.id in :rutinasCliente " +
            "and R.nombre like concat('%', :nombre, '%') " +
            "and R.fechaCreacion >= :fecha " +
            "and size(R.ordenSesionRutinas) >= :numeroSesiones")
    List<Rutina> getRutinasByClienteAndFiltro(@Param("nombre") String nombre,
                                              @Param("fecha") LocalDate fecha,
                                              @Param("numeroSesiones") Integer numeroSesiones,
                                              @Param("rutinasCliente") List<Integer> rutinasCliente);

    @Query("select R from Rutina R where R.id not in :rutinasCliente " +
            "and R.nombre like concat('%', :nombre, '%') " +
            "and R.fechaCreacion >= :fecha " +
            "and size(R.ordenSesionRutinas) >= :numeroSesiones")
    List<Rutina> getRutinasByNotInRutinasClienteAndFiltro(@Param("rutinasCliente") List<Integer> rutinasCliente,
                                                          @Param("nombre") String nombre,
                                                          @Param("fecha") LocalDate fecha,
                                                          @Param("numeroSesiones") Integer numeroSesiones);

    @Query("select R from Rutina R where R.entrenador.id = :entrenador " +
            "and R.nombre like concat('%', :nombre, '%') "+
            "and R.fechaCreacion >= :fecha " +
            "and size(R.ordenSesionRutinas) >= :numeroSesiones")
    List<Rutina> getRutinasByEntrenadorAndFiltro(@Param("entrenador") Integer entrenador,
                                                 @Param("nombre") String nombre,
                                                 @Param("fecha") LocalDate fecha,
                                                 @Param("numeroSesiones") Integer numeroSesiones);
}
