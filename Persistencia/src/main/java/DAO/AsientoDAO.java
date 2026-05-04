package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import itson.dominio.Asiento;
import itson.dominio.EstadoAsiento;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Los asientos se almacenan como subdocumentos dentro de cada Funcion.
 * Esta DAO opera sobre la colección "funciones" filtrando por idFuncion.
 */
public class AsientoDAO {

    private final MongoCollection<Document> coleccion;

    public AsientoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("funciones");
    }

    // ── CREATE ────────────────────────────────────────────────────────────────

    /**
     * Agrega un asiento al arreglo de asientos de una función.
     */
    public void agregarAsiento(ObjectId idFuncion, Asiento asiento) {
        Document docAsiento = asientoADocumento(asiento);
        coleccion.updateOne(
                eq("_id", idFuncion),
                Updates.push("asientos", docAsiento)
        );
    }

    /**
     * Inicializa todos los asientos de una función de una vez.
     */
    public void inicializarAsientos(ObjectId idFuncion, List<Asiento> asientos) {
        List<Document> docs = new ArrayList<>();
        for (Asiento a : asientos) {
            docs.add(asientoADocumento(a));
        }
        coleccion.updateOne(
                eq("_id", idFuncion),
                Updates.set("asientos", docs)
        );
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    /**
     * Devuelve todos los asientos de una función.
     */
    @SuppressWarnings("unchecked")
    public List<Asiento> obtenerPorFuncion(ObjectId idFuncion) {
        List<Asiento> asientos = new ArrayList<>();
        Document funcion = coleccion.find(eq("_id", idFuncion)).first();
        if (funcion == null) return asientos;

        List<Document> docs = (List<Document>) funcion.get("asientos");
        if (docs == null) return asientos;

        for (Document doc : docs) {
            asientos.add(documentoAAsiento(doc));
        }
        return asientos;
    }

    /**
     * Devuelve solo los asientos disponibles de una función.
     */
    public List<Asiento> obtenerDisponibles(ObjectId idFuncion) {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento a : obtenerPorFuncion(idFuncion)) {
            if (a.getEstado() == EstadoAsiento.DISPONIBLE) {
                disponibles.add(a);
            }
        }
        return disponibles;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    /**
     * Cambia el estado de un asiento específico (identificado por fila + número).
     */
    public boolean actualizarEstado(ObjectId idFuncion, String fila, int numero, EstadoAsiento nuevoEstado) {
        var resultado = coleccion.updateOne(
                and(eq("_id", idFuncion),
                        eq("asientos.fila", fila),
                        eq("asientos.numero", numero)),
                Updates.set("asientos.$.estado", nuevoEstado.name())
        );
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    /**
     * Elimina un asiento del arreglo de una función.
     */
    public void eliminarAsiento(ObjectId idFuncion, String fila, int numero) {
        Document filtroAsiento = new Document("fila", fila).append("numero", numero);
        coleccion.updateOne(
                eq("_id", idFuncion),
                Updates.pull("asientos", filtroAsiento)
        );
    }

    // ── MAPEO ─────────────────────────────────────────────────────────────────

    private Document asientoADocumento(Asiento a) {
        return new Document()
                .append("fila", a.getFila())
                .append("numero", a.getNumero())
                .append("estado", a.getEstado().name());
    }

    private Asiento documentoAAsiento(Document doc) {
        return new Asiento(
                doc.getString("fila"),
                doc.getInteger("numero", 0),
                EstadoAsiento.valueOf(doc.getString("estado"))
        );
    }
}