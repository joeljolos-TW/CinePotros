package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import entidadesMongo.PagoMongoEntidad;
import itson.dominio.EstadoPago;
import itson.dominio.Pago;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PagoDAO implements IDAOGenerico<PagoMongoEntidad, ObjectId> {
    private final MongoCollection<PagoMongoEntidad> coleccion;

    public PagoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("pagos", PagoMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public PagoMongoEntidad insertar(PagoMongoEntidad pago) {
        coleccion.insertOne(pago);
        return pago;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<PagoMongoEntidad> obtenerTodos() {
        List<PagoMongoEntidad> pagos = new ArrayList<>();
        for (PagoMongoEntidad pago : coleccion.find()) {
            pagos.add(pago);
        }
        return pagos;
    }

    public PagoMongoEntidad obtenerPorId(ObjectId id) {
        PagoMongoEntidad pago = coleccion.find(eq("_id", id)).first();
        if (pago == null) return null;
        return pago;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(PagoMongoEntidad pago) {
        Document actualizacion = new Document("$set", pago);
        UpdateResult resultado = coleccion.updateOne(eq("_id", pago.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
//
//    // ── MAPEO ─────────────────────────────────────────────────────────────────
//
//    private Document pagoADocumento(Pago p) {
//        Document doc = new Document()
//                .append("Boleto", p.getBoleto())
//                .append("Monto", p.getMonto())
//                .append("Estado", p.getEstado().toString());
//        if (p.getId() != null) {
//            doc.append("_id", p.getId());
//        }
//        return doc;
//    }
//
//    private Pago documentoApago(Document doc) {
//        return new Pago(
//                doc.getObjectId("_id"),
//                doc.getObjectId("Boleto"),
//                doc.getDouble("Monto"),
//                doc.getObjectId("Empleado"),
//                EstadoPago.valueOf(doc.getString("Estado"))
//        );
//    }    
}
