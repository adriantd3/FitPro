package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
