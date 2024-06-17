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
@Table(name = "desempenyo_sesion")
public class DesempenyoSesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sesion_id", nullable = false)
    private Sesion sesion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "terminado", nullable = false)
    private Byte terminado;

    @OneToMany(mappedBy = "desempenyoSesion", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("ejercicio.id ASC, id ASC")
    private List<DesempenyoSerie> desempenyoSeries = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Byte getTerminado() {
        return terminado;
    }

    public void setTerminado(Byte terminado) {
        this.terminado = terminado;
    }

    public List<DesempenyoSerie> getDesempenyoSeries() {
        return desempenyoSeries;
    }

    public void setDesempenyoSeries(List<DesempenyoSerie> desempenyoSeries) {
        this.desempenyoSeries = desempenyoSeries;
    }

}