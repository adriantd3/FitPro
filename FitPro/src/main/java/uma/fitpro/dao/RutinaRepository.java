package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Rutina;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.entrenador.id = :entrenador")
    List<Rutina> getRutinasByEntrenador(@Param("entrenador") Integer entrenador);

    @Query("select r from Rutina r where r.entrenador.id = :entrenador and r.nombre like concat('%', :nombre, '%') ")
    List<Rutina> getFilteredRutinasByEntrenador(@Param("entrenador") Integer entrenador, @Param("nombre") String nombre);
}
