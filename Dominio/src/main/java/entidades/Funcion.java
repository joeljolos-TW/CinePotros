package entidades;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo
 */
public class Funcion {

    private ObjectId id;
    private ObjectId peliculaId;
    private Date horaInicio;
    private int numeroSala;
    private List<Asiento> asientos;

    public Funcion() {}

    public Funcion(ObjectId id, ObjectId peliculaId, Date horaInicio, int numeroSala, List<Asiento> asientos) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.horaInicio = horaInicio;
        this.numeroSala = numeroSala;
        this.asientos = asientos;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("peliculaId", peliculaId)
                .append("horaInicio", horaInicio)
                .append("numeroSala", numeroSala)
                .append("asientos", asientos.stream()
                        .map(Asiento::toDocument)
                        .collect(Collectors.toList()));
    }

    public static Funcion fromDocument(Document doc) {
        List<Document> asientosDoc = (List<Document>) doc.get("asientos");

        return new Funcion(
                doc.getObjectId("_id"),
                doc.getObjectId("peliculaId"),
                doc.getDate("horaInicio"),
                doc.getInteger("numeroSala"),
                asientosDoc != null ? asientosDoc.stream()
                        .map(Asiento::fromDocument)
                        .collect(Collectors.toList()) : null
        );
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public ObjectId getPeliculaId() { return peliculaId; }
    public void setPeliculaId(ObjectId peliculaId) { this.peliculaId = peliculaId; }
    public Date getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Date horaInicio) { this.horaInicio = horaInicio; }
    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }
    public List<Asiento> getAsientos() { return asientos; }
    public void setAsientos(List<Asiento> asientos) { this.asientos = asientos; }
}