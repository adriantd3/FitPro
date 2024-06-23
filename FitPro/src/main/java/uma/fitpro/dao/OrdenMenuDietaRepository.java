package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uma.fitpro.entity.OrdenMenuDieta;
import uma.fitpro.entity.OrdenMenuDietaId;

/**
 * @author José Ángel Bueno Ruiz (100%)
 */

public interface OrdenMenuDietaRepository extends JpaRepository<OrdenMenuDieta, OrdenMenuDietaId> {

    @Transactional
    @Modifying
    @Query("delete from OrdenMenuDieta omd where omd.dieta.id = :dietaId and omd.id.orden = :ordenMenu")
    void deleteOrdenMenuByOrden(Integer dietaId, Integer ordenMenu);
}
