package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import entidadesMongo.EmpleadoMongoEntidad;
import itson.dominio.Empleado;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.eq;

public class EmpleadoDAO implements IDAOGenerico<EmpleadoMongoEntidad, ObjectId> {
    private final MongoCollection<EmpleadoMongoEntidad> coleccion;

    public EmpleadoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("empleado", EmpleadoMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public EmpleadoMongoEntidad insertar(EmpleadoMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    @Override
    public List<EmpleadoMongoEntidad> obtenerTodos() {
        List<EmpleadoMongoEntidad> Empleados = new ArrayList<>();
        for (EmpleadoMongoEntidad empleado : coleccion.find()) {
            Empleados.add(empleado);
        }
        return Empleados;
    }

    @Override
    public EmpleadoMongoEntidad obtenerPorId(ObjectId id) {
        EmpleadoMongoEntidad empleado = coleccion.find(eq("_id", id)).first();
        if (empleado == null) return null;
        return empleado;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Override
    public boolean actualizar(EmpleadoMongoEntidad Empleado) {
        Document actualizacion = new Document("$set", Empleado);
        UpdateResult resultado = coleccion.updateOne(eq("_id", Empleado.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

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
