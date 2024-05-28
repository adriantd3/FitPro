package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Comida;
import uma.fitpro.entity.Usuario;

import java.util.List;

import java.util.List;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {
    @Query("select c from Comida c where c.nombre like concat('%', :nombre, '%') and c.calorias>=:kcal")
    List<Comida> buscarConFiltro(String nombre, float kcal);

    @Query("select c from Comida c where c.nombre like concat('%', :nombre, '%')  and c.calorias >= :calorias")
    public List<Comida> filterFood(@Param("nombre") String nombre, @Param("calorias") int calorias);
}
