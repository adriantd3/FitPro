package uma.fitpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uma.fitpro.entity.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.correo = :mail and u.contrasenya = :password")
    public Usuario autenticar(@Param("mail") String mail, @Param("password") String password);

    @Query("select u from Usuario u where u.rol.id = :rol")
    public List<Usuario> findAllByRol(@Param("rol") Integer id);

    @Query("select u from Usuario u where u.rol.nombre like concat('%', :rol, '%')  and u.nombre like concat('%', :nombre, '%') and u.apellidos like concat('%', :apellido, '%')")
    public List<Usuario> filterUsers(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("rol") String rol);
    @Query("select u from Usuario u where u.nombre like concat('%' , :nombre , '%') " +
            "and :entrenador member of u.entrenadores " +
            "and u.edad >= :edad " +
            "and u.peso >= :peso " +
            "and u.altura >= :altura " +
            "order by u.nombre")
    public List<Usuario> filtrarCliente(@Param("nombre") String nombre,
                                        @Param("entrenador") Usuario entrenador,
                                        @Param("edad") Integer edad,
                                        @Param("peso") Float peso,
                                        @Param("altura") Float altura);

    @Query("select u from Usuario u where u.nombre like concat('%' , :nombre , '%') " +
            "and u.apellidos like concat('%' , :apellidos , '%') " +
            "and :dietista member of u.dietistas ")
    public List<Usuario> buscarClientesDietistaConFiltro(@Param("dietista") Usuario dietista,
                                                         @Param("nombre") String nombre,
                                                         @Param("apellidos") String apellidos);
}
