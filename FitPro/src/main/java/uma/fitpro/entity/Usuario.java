package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario implements Comparable<Usuario> {
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
    private List<DesempenyoMenu> desempenyoMenuEntities = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private Set<DesempenyoSesion> desempenyoSesionEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dietista")
    private List<Dieta> dietasDietista = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "dieta_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dieta_id"))
    private List<Dieta> dietasCliente = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "dietista_cliente",
            joinColumns = @JoinColumn(name = "dietista_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private List<Usuario> clientesDietista = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "dietista_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "dietista_id"))
    private List<Usuario> dietistas = new ArrayList<>();

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

    public List<DesempenyoMenu> getDesempenyoMenus() {
        return desempenyoMenuEntities;
    }

    public void setDesempenyoMenus(List<DesempenyoMenu> desempenyoMenuEntities) {
        this.desempenyoMenuEntities = desempenyoMenuEntities;
    }

    public Set<DesempenyoSesion> getDesempenyoSesions() {
        return desempenyoSesionEntities;
    }

    public void setDesempenyoSesions(Set<DesempenyoSesion> desempenyoSesionEntities) {
        this.desempenyoSesionEntities = desempenyoSesionEntities;
    }

    public List<Dieta> getDietasDietista() {
        return dietasDietista;
    }

    public void setDietasDietista(List<Dieta> dietasDietista) {
        this.dietasDietista = dietasDietista;
    }

    public List<Dieta> getDietasCliente() {
        return dietasCliente;
    }

    public void setDietasCliente(List<Dieta> dietasCliente) {
        this.dietasCliente = dietasCliente;
    }

    public List<Usuario> getClientesDietista() {
        return clientesDietista;
    }

    public void setClientesDietista(List<Usuario> clientesDietista) {
        this.clientesDietista = clientesDietista;
    }

    public List<Usuario> getDietistas() {
        return dietistas;
    }

    public void setDietistas(List<Usuario> dietistas) {
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
    public int compareTo(Usuario o) {
        return this.getNombre().compareTo(o.getNombre());
    }
}