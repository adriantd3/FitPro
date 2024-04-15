package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.EntrenadorCliente;
import uma.fitpro.entity.EntrenadorClienteId;
import uma.fitpro.entity.Usuario;

public interface EntrenadorClienteRepository extends JpaRepository<EntrenadorCliente, EntrenadorClienteId> {
}
