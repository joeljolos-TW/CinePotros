package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.Empleado;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class EmpleadoDAO {
    private final MongoCollection<Document> coleccion;

    public EmpleadoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("empleado");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

//    public Empleado insertar(Empleado Empleado) {
//        Document doc = EmpleadoADocumento(Empleado);
//        coleccion.insertOne(doc);
//        Empleado.setId(doc.getObjectId("_id"));
//        return Empleado;
//    }
//
//    // ── READ ──────────────────────────────────────────────────────────────────
//
//    public List<Empleado> obtenerTodos() {
//        List<Empleado> Empleados = new ArrayList<>();
//        for (Document doc : coleccion.find()) {
//            Empleados.add(documentoAEmpleado(doc));
//        }
//        return Empleados;
//    }
//
//    public Empleado obtenerPorId(ObjectId id) {
//        Document doc = coleccion.find(eq("_id", id)).first();
//        if (doc == null) return null;
//        return documentoAEmpleado(doc);
//    }
//
//    // ── UPDATE ────────────────────────────────────────────────────────────────
//
//    public boolean actualizar(Empleado Empleado) {
//        Document actualizacion = new Document("$set", EmpleadoADocumento(Empleado));
//        UpdateResult resultado = coleccion.updateOne(eq("_id", Empleado.getId()), actualizacion);
//        return resultado.getModifiedCount() > 0;
//    }
//
//    // ── DELETE ────────────────────────────────────────────────────────────────
//
//    public boolean eliminar(ObjectId id) {
//        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
//        return resultado.getDeletedCount() > 0;
//    }
//
//    // ── MAPEO ─────────────────────────────────────────────────────────────────
//
//    private Document EmpleadoADocumento(Empleado e) {
//        Document doc = new Document()
//                .append("Boleto", e.getNombreUsuario())
//                .append("Monto", e.getContrasena());
//        if (e.getId() != null) {
//            doc.append("_id", e.getId());
//        }
//        return doc;
//    }
//
//    private Empleado documentoAEmpleado(Document doc) {
//        return new Empleado(
//                doc.getObjectId("_id"),
//                doc.getString("Usuario"),
//                doc.getString("Contraseña")
//        );
//    }
}
