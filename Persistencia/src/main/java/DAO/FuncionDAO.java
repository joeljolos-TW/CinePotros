package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import itson.dominio.Funcion;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class FuncionDAO {

    private final MongoCollection<Document> coleccion;

    public FuncionDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("funciones");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Funcion insertar(Funcion funcion) {
        Document doc = funcionADocumento(funcion);
        coleccion.insertOne(doc);
        funcion.setId(doc.getObjectId("_id"));
        return funcion;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Funcion> obtenerTodas() {
        List<Funcion> funciones = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            funciones.add(documentoAFuncion(doc));
        }
        return funciones;
    }

    public Funcion obtenerPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return documentoAFuncion(doc);
    }

    public List<Funcion> obtenerPorPelicula(ObjectId idPelicula) {
        List<Funcion> funciones = new ArrayList<>();
        for (Document doc : coleccion.find(eq("pelicula", idPelicula))) {
            funciones.add(documentoAFuncion(doc));
        }
        return funciones;
    }

    public List<Funcion> obtenerPorPeliculaYFecha(ObjectId idPelicula, LocalDate fecha) {
        List<Funcion> funciones = new ArrayList<>();
        for (Document doc : coleccion.find(
                and(eq("pelicula", idPelicula), eq("fecha", fecha.toString())))) {
            funciones.add(documentoAFuncion(doc));
        }
        return funciones;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(Funcion funcion) {
        Document actualizacion = new Document("$set", funcionADocumento(funcion));
        UpdateResult resultado = coleccion.updateOne(eq("_id", funcion.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document funcionADocumento(Funcion f) {
        Document doc = new Document()
                .append("pelicula", f.getPelicula())
                .append("fecha", f.getFecha().toString())
                .append("hora", f.getHora().toString())
                .append("sala", f.getSala())
                .append("idioma", f.getIdioma())
                .append("precio", f.getPrecio());
        if (f.getId() != null) {
            doc.append("_id", f.getId());
        }
        return doc;
    }

    private Funcion documentoAFuncion(Document doc) {
        return new Funcion(
                doc.getObjectId("_id"),
                doc.getObjectId("pelicula"),
                LocalDate.parse(doc.getString("fecha")),
                LocalTime.parse(doc.getString("hora")),
                doc.getObjectId("sala"),
                doc.getString("idioma"),
                doc.getDouble("precio")
        );
    }
}