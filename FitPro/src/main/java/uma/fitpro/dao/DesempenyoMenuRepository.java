package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.DesempenyoMenu;
import java.util.List;

public interface DesempenyoMenuRepository extends JpaRepository<DesempenyoMenu,Integer> {
    @Query("SELECT dm FROM DesempenyoMenu dm WHERE dm.usuario.id = :client_id AND dm.menu.id = :menu_id")
    public List<DesempenyoMenu> findByClientIDAndMenuID(@Param("client_id") Integer client_id,
                                                        @Param("menu_id") Integer menu_id);

}
