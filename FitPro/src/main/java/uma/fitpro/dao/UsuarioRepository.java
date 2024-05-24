package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.correo like :mail")
    public List<Usuario> findByMail(@Param("mail") String mail);

    @Query("select u from Usuario u where u.rol.id = :rol")
    public List<Usuario> findAllByRol(@Param("rol") Integer id);

    @Query("select u from Usuario u where u.nombre like concat('%' , :nombre , '%') order by u.nombre")
    public List<Usuario> findByNombre(@Param("nombre") String nombre);

}
