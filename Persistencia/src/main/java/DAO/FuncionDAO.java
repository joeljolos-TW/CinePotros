package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.FuncionMongoEntidad;

public class FuncionDAO {

    private final MongoCollection<FuncionMongoEntidad> coleccion;

    public FuncionDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("funcion",FuncionMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    public FuncionMongoEntidad insertar(FuncionMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<FuncionMongoEntidad> obtenerTodas() {
        return coleccion.find().into(new ArrayList<>());
    }

    public FuncionMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }

//    public List<Funcion> obtenerPorPelicula(ObjectId idPelicula) {
//        List<Funcion> funciones = new ArrayList<>();
//        for (Document doc : coleccion.find(eq("pelicula", idPelicula))) {
//            funciones.add(documentoAFuncion(doc));
//        }
//        return funciones;
//    }
//
//    public List<Funcion> obtenerPorPeliculaYFecha(ObjectId idPelicula, LocalDate fecha) {
//        List<Funcion> funciones = new ArrayList<>();
//        for (Document doc : coleccion.find(
//                and(eq("pelicula", idPelicula), eq("fecha", fecha.toString())))) {
//            funciones.add(documentoAFuncion(doc));
//        }
//        return funciones;
//    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean actualizar(FuncionMongoEntidad entidad) {
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
//    private Document funcionADocumento(Funcion f) {
//        Document doc = new Document()
//                .append("pelicula", f.getPelicula())
//                .append("fecha", f.getFecha().toString())
//                .append("hora", f.getHora().toString())
//                .append("sala", f.getSala())
//                .append("idioma", f.getIdioma())
//                .append("precio", f.getPrecio());
//        if (f.getId() != null) {
//            doc.append("_id", f.getId());
//        }
//        return doc;
//    }
//
//    private Funcion documentoAFuncion(Document doc) {
//        return new Funcion(
//                doc.getObjectId("_id"),
//                doc.getObjectId("pelicula"),
//                LocalDate.parse(doc.getString("fecha")),
//                LocalTime.parse(doc.getString("hora")),
//                doc.getObjectId("sala"),
//                doc.getString("idioma"),
//                doc.getDouble("precio")
//        );
//    }
}