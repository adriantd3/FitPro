package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.util.List;
import java.util.Set;

import java.time.LocalDate;
import java.util.List;

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

    @Query("select R from Rutina R where R not in :rutinasCliente")
    List<Rutina> findByNotInRutinasCliente(List<Rutina> rutinasCliente);
}
