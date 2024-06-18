package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uma.fitpro.dto.DTO;
import uma.fitpro.dto.EjercicioDTO;

import java.io.Serializable;

@Entity
@Table(name = "ejercicio")
public class Ejercicio implements Serializable, DTO<EjercicioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "video")
    private String video;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoEjercicio tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "grupo_muscular", nullable = false)
    private GrupoMuscular grupoMuscular;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public TipoEjercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEjercicio tipo) {
        this.tipo = tipo;
    }

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    @Override
    public EjercicioDTO toDTO() {
        EjercicioDTO dto = new EjercicioDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setImagen(imagen);
        dto.setVideo(video);
        dto.setTipo(tipo.toDTO());
        dto.setGrupoMuscular(grupoMuscular.toDTO());
        return dto;
    }
}