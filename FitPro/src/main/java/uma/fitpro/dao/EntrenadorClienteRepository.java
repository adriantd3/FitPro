package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.EntrenadorCliente;
import uma.fitpro.entity.EntrenadorClienteId;
import uma.fitpro.entity.Usuario;

import java.util.List;

public interface EntrenadorClienteRepository extends JpaRepository<EntrenadorCliente, EntrenadorClienteId> {
    @Query("SELECT u FROM EntrenadorCliente u WHERE u.entrenador.id = ?1")
    List<EntrenadorCliente> findByEntrenador(Integer id);

}
