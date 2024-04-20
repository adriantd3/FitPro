package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "desempenyo_serie")
public class DesempenyoSerie {

    @EmbeddedId
    private DesempenyoSerieId id;

    @MapsId("serieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "serie_id", nullable = true, referencedColumnName = "id")
    private Serie serie;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "repeticiones")
    private Integer repeticiones;

    @Column(name = "distancia")
    private Float distancia;

    @Column(name = "duracion")
    private Integer duracion;

    public DesempenyoSerieId getId() {
        return id;
    }

    public void setId(DesempenyoSerieId id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

}