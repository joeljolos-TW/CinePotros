package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import entidadesMongo.PagoMongoEntidad;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * DAO encargado de las operaciones CRUD sobre la colección "pagos" en MongoDB.
 * Utiliza el codec POJO de {@link PagoMongoEntidad} para mapear documentos
 * automáticamente.
 */
public class PagoDAO implements IDAOGenerico<PagoMongoEntidad, ObjectId> {
 
    private final MongoCollection<PagoMongoEntidad> coleccion;
 
    /**
     * Constructor que inicializa la conexión con la colección "pagos" de MongoDB.
     */
    public PagoDAO() {
        MongoDatabase base = ConexionMongoDB.getInstance().getDatabase();
        this.coleccion = base.getCollection("pagos", PagoMongoEntidad.class);
    }
 
 
    /**
     * Inserta un nuevo pago en la colección.
     *
     * @param pago el pago a insertar. MongoDB asigna el {@code _id}
     *             automáticamente si no se especifica.
     * @return el mismo pago con el {@code id} asignado por MongoDB.
     */
    @Override
    public PagoMongoEntidad insertar(PagoMongoEntidad pago) {
        coleccion.insertOne(pago);
        return pago;
    }

    /**
     * Obtiene todos los pagos almacenados en la colección.
     *
     * @return lista de {@link PagoMongoEntidad} con todos los pagos.
     *         Retorna lista vacía si no hay pagos.
     */
    public List<PagoMongoEntidad> obtenerTodos() {
        List<PagoMongoEntidad> pagos = new ArrayList<>();
        for (PagoMongoEntidad pago : coleccion.find()) {
            pagos.add(pago);
        }
        return pagos;
    }
 
    /**
     * Busca un pago por su identificador único.
     *
     * @param id el {@link ObjectId} del pago a buscar.
     * @return el pago encontrado, o {@code null} si no existe.
     */
    public PagoMongoEntidad obtenerPorId(ObjectId id) {
        PagoMongoEntidad pago = coleccion.find(eq("_id", id)).first();
        if (pago == null) return null;
        return pago;
    }
 

    /**
     * Actualiza los campos de un pago existente usando {@code $set}.
     *
     * @param pago el pago con los datos actualizados. Debe tener un {@code id} válido.
     * @return {@code true} si el documento fue modificado, {@code false} si no hubo cambios.
     */
    public boolean actualizar(PagoMongoEntidad pago) {
        Document actualizacion = new Document("$set", pago);
        UpdateResult resultado = coleccion.updateOne(eq("_id", pago.getId()), actualizacion);
        return resultado.getModifiedCount() > 0;
    }

    /**
     * Elimina un pago de la colección por su identificador.
     *
     * @param id el {@link ObjectId} del pago a eliminar.
     * @return {@code true} si el documento fue eliminado, {@code false} si no existía.
     */
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}
 