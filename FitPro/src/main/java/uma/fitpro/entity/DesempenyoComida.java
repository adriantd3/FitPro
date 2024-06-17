package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.DesempenyoComidaDTO;

import java.io.Serializable;

@Entity
@Table(name = "desempenyo_comida")
public class DesempenyoComida implements Serializable, DTO<DesempenyoComidaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "desempenyo_menu_id", nullable = false)
    private DesempenyoMenu desempenyoMenu;

    @Column(name = "comido", nullable = false)
    private Byte comido;

    @Column(name = "gustado", nullable = false)
    private Byte gustado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public DesempenyoMenu getDesempenyoMenu() {
        return desempenyoMenu;
    }

    public void setDesempenyoMenu(DesempenyoMenu desempenyoMenu) {
        this.desempenyoMenu = desempenyoMenu;
    }

    public Byte getComido() {
        return comido;
    }

    public void setComido(Byte comido) {
        this.comido = comido;
    }

    public Byte getGustado() {
        return gustado;
    }

    public void setGustado(Byte gustado) {
        this.gustado = gustado;
    }

    @Override
    public DesempenyoComidaDTO toDTO() {
        DesempenyoComidaDTO dto = new DesempenyoComidaDTO();
        dto.setId(id);
        dto.setComida(comida.toDTO());
        dto.setDesempenyoMenu(desempenyoMenu.getId());
        dto.setComido(comido == 1);
        dto.setGustado(gustado == 1);
        return dto;
    }
}