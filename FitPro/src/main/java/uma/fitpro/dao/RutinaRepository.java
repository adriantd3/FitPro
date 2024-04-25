package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.util.List;
import java.util.Set;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {

    @Query("select R from Rutina R where R.entrenador = :entrenador and R in :rutinasCliente")
    public List<Rutina> findByClienteByEntrenador(Set<Rutina> rutinasCliente, Usuario entrenador);
}
