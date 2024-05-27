package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dieta")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dietista_id", nullable = false)
    private Usuario dietista;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "dieta")
    private List<OrdenMenuDieta> ordenMenuDietas = new ArrayList<>();

    public Dieta() {
        setNombre("NuevaDieta");
        setFechaCreacion(LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getDietista() {
        return dietista;
    }

    public void setDietista(Usuario dietista) {
        this.dietista = dietista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<OrdenMenuDieta> getOrdenMenuDietas() {
        return ordenMenuDietas;
    }

    public void setOrdenMenuDietas(List<OrdenMenuDieta> ordenMenuDietas) {
        this.ordenMenuDietas = ordenMenuDietas;
    }

}