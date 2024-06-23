package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Usuario;

import java.util.List;

import java.util.List;

/**
 * @author Ezequiel Sánchez García (40%)
 * @author Víctor Pérez Armenta (40%)
 * @author David Bueno Carmona (20%)
 */

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

    @Query("select e from Ejercicio e where e.nombre like concat('%', :nombre, '%')  and e.tipo.tipo like concat('%', :tipo, '%') and e.grupoMuscular.grupoMuscular like concat('%', :grupo, '%')")
    public List<Ejercicio> filterExercise(@Param("nombre") String nombre, @Param("tipo") String tipo, @Param("grupo") String grupo);
    @Query("select e from Ejercicio e where e.nombre like concat('%', :filtro, '%')")
    List<Ejercicio> filtrarEjercicioPorNombre(@Param("filtro") String filtro);

    @Query("select e from Ejercicio e where e.nombre like concat('%', :filtro, '%') " +
            "and e.grupoMuscular.grupoMuscular like concat('%', :musculo, '%') " +
            "and  e.tipo.tipo like concat('%', :tipo, '%')")
    List<Ejercicio> filtrarEjercicioPorNombreMusculoYTipo(@Param("filtro") String filtro,
                                                    @Param("musculo") String musculo,
                                                    @Param("tipo") String tipo);

    @Query("select E from Ejercicio E where E.tipo.id = 1")
    List<Ejercicio> findAllByTipoFuerza();

    @Query("select E from Ejercicio E where E.tipo.id = 1 and lower(E.nombre) like lower(concat('%',:nombre,'%')) " +
            "and  E.grupoMuscular.grupoMuscular like concat('%',(:grupoMuscular),'%')")
    List<Ejercicio> filtrarEjercicioPorNombreYGrupoMuscular(@Param("nombre") String nombre,
                                                            @Param("grupoMuscular")String grupoMuscular);
}
