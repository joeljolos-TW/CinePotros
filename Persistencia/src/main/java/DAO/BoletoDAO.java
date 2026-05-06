package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.Boleto;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class BoletoDAO {

    private final MongoCollection<Document> coleccion;

    public BoletoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("boletos");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Boleto insertar(Boleto boleto) {
        Document doc = boletoADocumento(boleto);
        coleccion.insertOne(doc);
        boleto.setId(doc.getObjectId("_id"));
        return boleto;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Boleto> obtenerTodos() {
        List<Boleto> boletos = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            boletos.add(documentoABoleto(doc));
        }
        return boletos;
    }

    public Boleto obtenerPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return documentoABoleto(doc);
    }

    public List<Boleto> obtenerPorFuncion(ObjectId idFuncion) {
        List<Boleto> boletos = new ArrayList<>();
        for (Document doc : coleccion.find(eq("funcion", idFuncion))) {
            boletos.add(documentoABoleto(doc));
        }
        return boletos;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(Boleto boleto) {
        Document actualizacion = new Document("$set", boletoADocumento(boleto));
        UpdateResult resultado = coleccion.updateOne(eq("_id", boleto.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document boletoADocumento(Boleto b) {
        Document doc = new Document()
                .append("funcion", b.getFuncion())
                .append("numAsiento", b.getNumAsiento())
                .append("total", b.getTotal());
        if (b.getId() != null) {
            doc.append("_id", b.getId());
        }
        return doc;
    }

    @SuppressWarnings("unchecked")
    private Boleto documentoABoleto(Document doc) {
        return new Boleto(
                doc.getObjectId("_id"),
                doc.getObjectId("funcion"),
                (List<String>) doc.get("numAsiento"),
                doc.getDouble("total"),
                false
        );
    }
}