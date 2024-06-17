package uma.fitpro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @ColumnDefault("0")
    @Column(name = "calorias", nullable = false)
    private Float calorias;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @ManyToMany
    @JoinTable(name = "comida_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "comida_id"))
    private List<Comida> comidaEntities = new ArrayList<>();

    public Menu() {
        setNombre("NuevoMenú");
        setFechaCreacion(LocalDate.now());
        setCalorias(0f);
    }

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

    public Float getCalorias() {
        return calorias;
    }

    public void setCalorias(Float calorias) {
        this.calorias = calorias;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Comida> getComidas() {
        return comidaEntities;
    }

    public void updateKcal(){
        float kcal = 0;
        for(Comida comida : comidaEntities){
            kcal += comida.getCalorias();
        }
        setCalorias(kcal);
    }

    public void setComidas(List<Comida> comidaEntities) {
        this.comidaEntities = comidaEntities;
    }

}