package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.PeliculaMongoEntidad;

public class PeliculaDAO {

    private final MongoCollection<PeliculaMongoEntidad> coleccion;

    public PeliculaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("pelicula", PeliculaMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public PeliculaMongoEntidad insertar(PeliculaMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<PeliculaMongoEntidad> obtenerTodas() {
        return coleccion.find().into(new ArrayList<>());
    }

    public PeliculaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
        
 
    }

//    public List<Pelicula> obtenerPorGenero(String genero) {
//        List<Pelicula> peliculas = new ArrayList<>();
//        for (Document doc : coleccion.find(eq("genero", genero))) {
//            peliculas.add(documentoAPelicula(doc));
//        }
//        return peliculas;
//    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(PeliculaMongoEntidad entidad) {
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
//    private Document peliculaADocumento(Pelicula p) {
//        Document doc = new Document()
//                .append("titulo", p.getTitulo())
//                .append("clasificacion", p.getClasificacion())
//                .append("genero", p.getGenero())
//                .append("duracion", p.getDuracion())
//                .append("imagen", p.getImagen());
//        if (p.getId() != null) {
//            doc.append("_id", p.getId());
//        }
//        return doc;
//    }
//
//    private Pelicula documentoAPelicula(Document doc) {
//        return new Pelicula(
//                doc.getObjectId("_id"),
//                doc.getString("titulo"),
//                doc.getString("clasificacion"),
//                doc.getString("genero"),
//                doc.getInteger("duracion", 0),
//                doc.getString("imagen")
//        );
//    }
}