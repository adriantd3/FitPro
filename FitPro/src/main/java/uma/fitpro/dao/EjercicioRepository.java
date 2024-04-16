package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uma.fitpro.entity.Ejercicio;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
}
