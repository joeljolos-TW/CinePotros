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
    private String folioQR;
    private boolean usado;

    public Boleto() {}

    public Boleto(ObjectId id, ObjectId funcionId, String numeroAsiento, double precio) {
        this.id = id;
        this.funcionId = funcionId;
        this.numeroAsiento = numeroAsiento;
        this.precio = precio;
        this.folioQR = id != null ? id.toHexString() : "";
        this.usado = false;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("funcionId", funcionId)
                .append("numeroAsiento", numeroAsiento)
                .append("precio", precio)
                .append("folioQR", folioQR)
                .append("usado", usado);
    }

    public static Boleto fromDocument(Document doc) {
        Boleto b = new Boleto(
                doc.getObjectId("_id"),
                doc.getObjectId("funcionId"),
                doc.getString("numeroAsiento"),
                doc.getDouble("precio")
        );
        b.setFolioQR(doc.getString("folioQR"));
        Boolean u = doc.getBoolean("usado");
        b.setUsado(u != null ? u : false);
        return b;
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public ObjectId getFuncionId() { return funcionId; }
    public void setFuncionId(ObjectId funcionId) { this.funcionId = funcionId; }
    public String getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(String numeroAsiento) { this.numeroAsiento = numeroAsiento; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getFolioQR() { return folioQR; }
    public void setFolioQR(String folioQR) { this.folioQR = folioQR; }
    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}