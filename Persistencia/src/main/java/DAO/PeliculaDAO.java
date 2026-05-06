package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.Pelicula;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PeliculaDAO {

    private final MongoCollection<Document> coleccion;

    public PeliculaDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("peliculas");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Pelicula insertar(Pelicula pelicula) {
        Document doc = peliculaADocumento(pelicula);
        coleccion.insertOne(doc);
        pelicula.setId(doc.getObjectId("_id"));
        return pelicula;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Pelicula> obtenerTodas() {
        List<Pelicula> peliculas = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            peliculas.add(documentoAPelicula(doc));
        }
        return peliculas;
    }

    public Pelicula obtenerPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return documentoAPelicula(doc);
    }

    public List<Pelicula> obtenerPorGenero(String genero) {
        List<Pelicula> peliculas = new ArrayList<>();
        for (Document doc : coleccion.find(eq("genero", genero))) {
            peliculas.add(documentoAPelicula(doc));
        }
        return peliculas;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(Pelicula pelicula) {
        Document actualizacion = new Document("$set", peliculaADocumento(pelicula));
        UpdateResult resultado = coleccion.updateOne(eq("_id", pelicula.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document peliculaADocumento(Pelicula p) {
        Document doc = new Document()
                .append("titulo", p.getTitulo())
                .append("clasificacion", p.getClasificacion())
                .append("genero", p.getGenero())
                .append("duracion", p.getDuracion())
                .append("imagen", p.getImagen());
        if (p.getId() != null) {
            doc.append("_id", p.getId());
        }
        return doc;
    }

    private Pelicula documentoAPelicula(Document doc) {
        return new Pelicula(
                doc.getObjectId("_id"),
                doc.getString("titulo"),
                doc.getString("clasificacion"),
                doc.getString("genero"),
                doc.getInteger("duracion", 0),
                doc.getString("imagen")
        );
    }
}