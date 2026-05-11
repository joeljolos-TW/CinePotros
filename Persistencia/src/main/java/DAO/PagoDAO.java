package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.EstadoPago;
import itson.dominio.Pago;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PagoDAO {
    private final MongoCollection<Document> coleccion;

    public PagoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("pagos");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Pago insertar(Pago pago) {
        Document doc = pagoADocumento(pago);
        coleccion.insertOne(doc);
        pago.setId(doc.getObjectId("_id"));
        return pago;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Pago> obtenerTodos() {
        List<Pago> pagos = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            pagos.add(documentoApago(doc));
        }
        return pagos;
    }

    public Pago obtenerPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return documentoApago(doc);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(Pago pago) {
        Document actualizacion = new Document("$set", pagoADocumento(pago));
        UpdateResult resultado = coleccion.updateOne(eq("_id", pago.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document pagoADocumento(Pago p) {
        Document doc = new Document()
                .append("Boleto", p.getBoleto())
                .append("Monto", p.getMonto())
                .append("Estado", p.getEstado().toString());
        if (p.getId() != null) {
            doc.append("_id", p.getId());
        }
        return doc;
    }

    private Pago documentoApago(Document doc) {
        return new Pago(
                doc.getObjectId("_id"),
                doc.getObjectId("Boleto"),
                doc.getDouble("Monto"),
                doc.getObjectId("Empleado"),
                EstadoPago.valueOf(doc.getString("Estado"))
        );
    }    
}
