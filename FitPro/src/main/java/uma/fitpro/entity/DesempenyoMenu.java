package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.DesempenyoMenuDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "desempenyo_menu")
public class DesempenyoMenu implements Serializable, DTO<DesempenyoMenuDTO> {
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
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "terminado", nullable = false)
    private Byte terminado;

    @OneToMany(mappedBy = "desempenyoMenu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("comida.id ASC, id ASC")
    private List<DesempenyoComida> desempenyoComidas = new ArrayList<>();

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Byte getTerminado() {
        return terminado;
    }

    public void setTerminado(Byte terminado) {
        this.terminado = terminado;
    }

    public List<DesempenyoComida> getDesempenyoComidas() {
        return desempenyoComidas;
    }

    public void setDesempenyoComidas(List<DesempenyoComida> desempenyoComidas) {
        this.desempenyoComidas = desempenyoComidas;
    }

    @Override
    public DesempenyoMenuDTO toDTO() {
        DesempenyoMenuDTO desempenyoMenuDTO = new DesempenyoMenuDTO();
        desempenyoMenuDTO.setId(this.id);
        desempenyoMenuDTO.setMenuId(this.menu.getId());
        desempenyoMenuDTO.setNombreMenu(this.menu.getNombre());
        desempenyoMenuDTO.setCaloriasMenu(this.menu.getCalorias());
        desempenyoMenuDTO.setFechaCreacion(this.fechaCreacion);
        desempenyoMenuDTO.setTerminado(this.terminado == 1);

        List<Integer> desempenyoComidas = new ArrayList<>();
        for (DesempenyoComida desempenyoComida : this.desempenyoComidas) {
            desempenyoComidas.add(desempenyoComida.getId());
        }
        desempenyoMenuDTO.setDesempenyoComidas(new ArrayList<>(desempenyoComidas));
        return desempenyoMenuDTO;
    }
}