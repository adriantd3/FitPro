package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.UsuarioDTO;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, DTO<UsuarioDTO> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "sexo")
    private Byte sexo;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "altura")
    private Float altura;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "contrasenya", nullable = false, length = 45)
    private String contrasenya;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @OneToMany(mappedBy = "usuario")
    private Set<DesempenyoMenu> desempenyoMenuEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<DesempenyoSesion> desempenyoSesionEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dietista")
    private Set<Dieta> dietasDietista = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dieta_id"))
    private Set<Dieta> dietasCliente = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "dietista_cliente",
            joinColumns = @JoinColumn(name = "dietista_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private Set<Usuario> clientesDietista = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "dietista_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dietista_id"))
    private Set<Usuario> dietistas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "entrenador_cliente",
            joinColumns = @JoinColumn(name = "entrenador_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private Set<Usuario> clientesEntrenador = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "entrenador_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "entrenador_id"))
    private Set<Usuario> entrenadores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "entrenador")
    private Set<Rutina> rutinasEntrenador = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "rutina_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "rutina_id"))
    private Set<Rutina> rutinasCliente = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Byte getSexo() {
        return sexo;
    }

    public void setSexo(Byte sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<DesempenyoMenu> getDesempenyoMenus() {
        return desempenyoMenuEntities;
    }

    public void setDesempenyoMenus(Set<DesempenyoMenu> desempenyoMenuEntities) {
        this.desempenyoMenuEntities = desempenyoMenuEntities;
    }

    public Set<DesempenyoSesion> getDesempenyoSesions() {
        return desempenyoSesionEntities;
    }

    public void setDesempenyoSesions(Set<DesempenyoSesion> desempenyoSesionEntities) {
        this.desempenyoSesionEntities = desempenyoSesionEntities;
    }

    public Set<Dieta> getDietasDietista() {
        return dietasDietista;
    }

    public void setDietasDietista(Set<Dieta> dietasDietista) {
        this.dietasDietista = dietasDietista;
    }

    public Set<Dieta> getDietasCliente() {
        return dietasCliente;
    }

    public void setDietasCliente(Set<Dieta> dietasCliente) {
        this.dietasCliente = dietasCliente;
    }

    public Set<Usuario> getClientesDietista() {
        return clientesDietista;
    }

    public void setClientesDietista(Set<Usuario> clientesDietista) {
        this.clientesDietista = clientesDietista;
    }

    public Set<Usuario> getDietistas() {
        return dietistas;
    }

    public void setDietistas(Set<Usuario> dietistas) {
        this.dietistas = dietistas;
    }

    public Set<Usuario> getClientesEntrenador() {
        return clientesEntrenador;
    }

    public void setClientesEntrenador(Set<Usuario> clientesEntrenador) {
        this.clientesEntrenador = clientesEntrenador;
    }

    public Set<Usuario> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(Set<Usuario> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public Set<Rutina> getRutinasEntrenador() {
        return rutinasEntrenador;
    }

    public void setRutinasEntrenador(Set<Rutina> rutinasEntrenador) {
        this.rutinasEntrenador = rutinasEntrenador;
    }

    public Set<Rutina> getRutinasCliente() {
        return rutinasCliente;
    }

    public void setRutinasCliente(Set<Rutina> rutinasCliente) {
        this.rutinasCliente = rutinasCliente;
    }


    @Override
    public UsuarioDTO toDTO() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setDni(dni);
        usuario.setRol(rol.toDTO());
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setSexo(sexo == 1);
        usuario.setEdad(edad);
        usuario.setAltura(altura);
        usuario.setPeso(peso);
        usuario.setContrasenya(contrasenya);
        usuario.setCorreo(correo);

        return usuario;
    }
}