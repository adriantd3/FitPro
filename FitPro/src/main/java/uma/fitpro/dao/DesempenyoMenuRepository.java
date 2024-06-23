package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoMenu;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Adrián Torremocha Doblas (50%)
 * @author José Ángel Bueno Ruiz (50%)
 */

public interface DesempenyoMenuRepository extends JpaRepository<DesempenyoMenu,Integer> {
    @Query("SELECT dm FROM DesempenyoMenu dm WHERE dm.usuario.id = :client_id AND dm.menu.id = :menu_id")
    public List<DesempenyoMenu> findByClientIDAndMenuID(@Param("client_id") Integer client_id,
                                                        @Param("menu_id") Integer menu_id);

    @Query("SELECT dm FROM DesempenyoMenu dm WHERE dm.usuario.id = :client_id AND dm.menu.id = :menu_id AND dm.fechaCreacion >= :fecha")
    List<DesempenyoMenu> findByClientIDAndMenuIDAndDate(@Param("client_id") int clienteId, @Param("menu_id") int menuId, @Param("fecha") LocalDate localDateFecha);
}
