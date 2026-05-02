package entidades;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Ricardo
 */
public class Boleto {

    private ObjectId id;
    private ObjectId funcionId;
    private String numeroAsiento;
    private double precio;

    public Boleto() {}

    public Boleto(ObjectId id, ObjectId funcionId, String numeroAsiento, double precio) {
        this.id = id;
        this.funcionId = funcionId;
        this.numeroAsiento = numeroAsiento;
        this.precio = precio;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("funcionId", funcionId)
                .append("numeroAsiento", numeroAsiento)
                .append("precio", precio);
    }

    public static Boleto fromDocument(Document doc) {
        return new Boleto(
                doc.getObjectId("_id"),
                doc.getObjectId("funcionId"),
                doc.getString("numeroAsiento"),
                doc.getDouble("precio")
        );
    }
}