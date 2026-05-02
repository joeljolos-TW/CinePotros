package entidades;

import org.bson.Document;

/**
 *
 * @author Ricardo
 */
public class Asiento {

    private String numero;
    private boolean ocupado;

    public Asiento() {}

    public Asiento(String numero, boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public Document toDocument() {
        return new Document("numero", numero)
                .append("ocupado", ocupado);
    }

    public static Asiento fromDocument(Document doc) {
        return new Asiento(
                doc.getString("numero"),
                doc.getBoolean("ocupado")
        );
    }
}