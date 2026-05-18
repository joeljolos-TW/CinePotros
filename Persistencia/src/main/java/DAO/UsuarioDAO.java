package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.UsuarioMongoEntidad;

public class UsuarioDAO implements IDAOGenerico<UsuarioMongoEntidad, ObjectId>{

    private final MongoCollection<UsuarioMongoEntidad> coleccion;

    public UsuarioDAO() {
        ConexionMongoDB.getInstance().getDatabase();
        this.coleccion =  ConexionMongoDB.getInstance()
                .getDatabase().getCollection("usuarios",UsuarioMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public UsuarioMongoEntidad insertar(UsuarioMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    @Override
    public List<UsuarioMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public UsuarioMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }

//    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
//        Document doc = coleccion.find(eq("nombreUsuario", nombreUsuario)).first();
//        if (doc == null) return null;
//        return documentoAUsuario(doc);
//    }

    /**
     * Útil para autenticación: busca por usuario y contraseña al mismo tiempo.
     */
//    public UsuarioMongoEntidad autenticar(String nombreUsuario, String contrasena) {
//        Document doc = coleccion.find(
//                and(eq("nombreUsuario", nombreUsuario), eq("contrasena", contrasena))
//        ).first();
//        if (doc == null) return null;
//        return documentoAUsuario(doc);
//    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Override
    public boolean actualizar(UsuarioMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

//    private Document usuarioADocumento(Usuario u) {
//        Document doc = new Document()
//                .append("nombreUsuario", u.getNombreUsuario())
//                .append("contrasena", u.getContrasena());
//        if (u.getId() != null) {
//            doc.append("_id", u.getId());
//        }
//        return doc;
//    }
//
//    private Usuario documentoAUsuario(Document doc) {
//        return new Usuario(
//                doc.getObjectId("_id"),
//                doc.getString("nombreUsuario"),
//                doc.getString("contrasena")
//        );
//    }
}