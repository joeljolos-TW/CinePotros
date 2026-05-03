package entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "salas")
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer capacidadTotal;

    public Sala(Long id, String nombre, Integer capacidadTotal) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }
}
