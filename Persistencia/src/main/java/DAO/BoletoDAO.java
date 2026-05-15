package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.BoletoMongoEntidad;

public class BoletoDAO {

    private final MongoCollection<BoletoMongoEntidad> coleccion;

    public BoletoDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase().getCollection("boleto",BoletoMongoEntidad.class);
       
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public BoletoMongoEntidad insertar(BoletoMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<BoletoMongoEntidad> obtenerTodos() {
       return coleccion.find().into(new ArrayList<>());
    }

    public BoletoMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id",id)).first();
    }

//    public List<BoletoMongoEntidad> obtenerPorFuncion(ObjectId idFuncion) {
//        List<Boleto> boletos = new ArrayList<>();
//        for (Document doc : coleccion.find(eq("funcion", idFuncion))) {
//            boletos.add(documentoABoleto(doc));
//        }
//        return boletos;
//    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(BoletoMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────
//
//    private Document boletoADocumento(Boleto b) {
//        Document doc = new Document()
//                .append("funcion", b.getFuncion())
//                .append("numAsiento", b.getNumAsiento())
//                .append("total", b.getTotal())
//                .append("usado", b.isUsado());
//        if (b.getId() != null) {
//            doc.append("_id", b.getId());
//        }
//        return doc;
//    }
//
//    @SuppressWarnings("unchecked")
//    private Boleto documentoABoleto(Document doc) {
//        return new Boleto(
//                doc.getObjectId("_id"),
//                doc.getObjectId("funcion"),
//                (List<String>) doc.get("numAsiento"),
//                doc.getDouble("total"),
//                false
//        );
//    }
}