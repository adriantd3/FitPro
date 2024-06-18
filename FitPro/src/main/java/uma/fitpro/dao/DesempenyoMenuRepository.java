package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoMenu;

import java.util.List;

public interface DesempenyoMenuRepository extends JpaRepository<DesempenyoMenu, Integer> {
    @Query("select desempenyo from DesempenyoMenu desempenyo where desempenyo.menu.id = :menuId AND desempenyo.usuario.id = :clienteId")
    List<DesempenyoMenu> getDesempenyosOfMenuByClient(@Param("menuId") Integer menuId,@Param("clienteId") Integer clienteId);
}
