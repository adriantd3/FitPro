package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Rutina;

import java.time.LocalDate;
import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.entrenador.id = :entrenador and r not in :rutinas")
    List<Rutina> getRestantesRutinasByEntrenador(@Param("entrenador") Integer entrenador,
                                                 @Param("rutinas") List<Rutina> rutinas);

    @Query("select r from Rutina r where r.entrenador.id = :entrenador")
    List<Rutina> getRutinasByEntrenador(@Param("entrenador") Integer entrenador);

    @Query("select r from Rutina r where r.entrenador.id = :entrenador " +
            "and r.nombre like concat('%', :nombre, '%') and r.fechaCreacion >= :fecha")
    List<Rutina> getFilteredRutinasByEntrenadorAndFecha(@Param("entrenador") Integer entrenador,
                                                        @Param("nombre") String nombre,
                                                        @Param("fecha") LocalDate fecha);

}
