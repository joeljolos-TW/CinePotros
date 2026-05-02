package entidades;

import org.bson.types.ObjectId;
import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class Sala implements Serializable {
    
    private ObjectId id;
    private String nombre;
    private Integer capacidadTotal;

    public Sala() {
    }

    public Sala(String nombre, Integer capacidadTotal) {
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(Integer capacidadTotal) { this.capacidadTotal = capacidadTotal; }
}
