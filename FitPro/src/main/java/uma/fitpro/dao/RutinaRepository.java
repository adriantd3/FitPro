package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Rutina;

import java.time.LocalDate;
import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
}
