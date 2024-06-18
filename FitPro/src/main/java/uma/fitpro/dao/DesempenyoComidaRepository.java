package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoComida;

import java.util.List;

public interface DesempenyoComidaRepository extends JpaRepository<DesempenyoComida,Integer> {

    @Query("SELECT dc FROM DesempenyoComida dc WHERE dc.desempenyoMenu.id = :id order by dc.comida.id asc, dc.id asc")
    public List<DesempenyoComida> findByDesempenyoMenu(@Param("id") Integer id);

    @Query("SELECT dc FROM DesempenyoComida dc WHERE dc.desempenyoMenu.id = :des_id AND" +
            " dc.comida.nombre LIKE %:nombre% AND dc.comida.calorias >= :calorias AND " +
            "(:comido = 2 or dc.comido = :comido) AND " +
            "(:gustado = 2 or dc.gustado = :gustado) " +
            "order by dc.comida.id asc, dc.id asc")
    public List<DesempenyoComida> findByFiltroMenu(@Param("des_id") Integer des_id,
                                                   @Param("nombre") String nombre,
                                                   @Param("calorias") Integer calorias,
                                                   @Param("comido") Byte comido,
                                                   @Param("gustado") Byte gustado);
}
