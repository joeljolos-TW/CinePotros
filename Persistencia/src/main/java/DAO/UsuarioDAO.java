package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.Usuario;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UsuarioDAO {

    private final MongoCollection<Document> coleccion;

    public UsuarioDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("usuarios");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Usuario insertar(Usuario usuario) {
        Document doc = usuarioADocumento(usuario);
        coleccion.insertOne(doc);
        usuario.setId(doc.getObjectId("_id"));
        return usuario;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            usuarios.add(documentoAUsuario(doc));
        }
        return usuarios;
    }

    public Usuario obtenerPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return documentoAUsuario(doc);
    }

    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        Document doc = coleccion.find(eq("nombreUsuario", nombreUsuario)).first();
        if (doc == null) return null;
        return documentoAUsuario(doc);
    }

    /**
     * Útil para autenticación: busca por usuario y contraseña al mismo tiempo.
     */
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        Document doc = coleccion.find(
                and(eq("nombreUsuario", nombreUsuario), eq("contrasena", contrasena))
        ).first();
        if (doc == null) return null;
        return documentoAUsuario(doc);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(Usuario usuario) {
        Document actualizacion = new Document("$set", usuarioADocumento(usuario));
        UpdateResult resultado = coleccion.updateOne(eq("_id", usuario.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document usuarioADocumento(Usuario u) {
        Document doc = new Document()
                .append("nombreUsuario", u.getNombreUsuario())
                .append("contrasena", u.getContrasena());
        if (u.getId() != null) {
            doc.append("_id", u.getId());
        }
        return doc;
    }

    private Usuario documentoAUsuario(Document doc) {
        return new Usuario(
                doc.getObjectId("_id"),
                doc.getString("nombreUsuario"),
                doc.getString("contrasena")
        );
    }
}