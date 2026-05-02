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
                asientosDoc.stream()
                        .map(Asiento::fromDocument)
                        .collect(Collectors.toList())
        );
    }
}