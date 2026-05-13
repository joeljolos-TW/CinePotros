package PaymentDTO;

import itson.dominio.TipoSala;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentSummaryDTO {

    private LocalDateTime fecha;
    private ObjectId idFuncion;
    private String tituloPelicula;
    private String detalles;
    private List<String> asientos;
    private TipoSala tipoSala;

    public PaymentSummaryDTO() {
    }

    public PaymentSummaryDTO(LocalDateTime fecha, ObjectId idFuncion, String tituloPelicula, String detalles, List<String> asientos, TipoSala tipoSala) {
        this.fecha = fecha;
        this.idFuncion = idFuncion;
        this.tituloPelicula = tituloPelicula;
        this.detalles = detalles;
        this.asientos = asientos;
        this.tipoSala = tipoSala;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ObjectId getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(ObjectId idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<String> asientos) {
        this.asientos = asientos;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
}