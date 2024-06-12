package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.OrdenSesionRutina;
import uma.fitpro.entity.OrdenSesionRutinaId;

public interface OrdenSesionRutinaRepository extends JpaRepository<OrdenSesionRutina, OrdenSesionRutinaId> {
}
