package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "funciones")
public class Funcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Double precioPorAsiento;

    public Funcion() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getPrecioPorAsiento() {
        return precioPorAsiento;
    }

    public void setPrecioPorAsiento(Double precioPorAsiento) {
        this.precioPorAsiento = precioPorAsiento;
    }
}
