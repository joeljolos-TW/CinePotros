package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.BoletoMongoEntidad;

/**
 * DAO encargado de las operaciones CRUD sobre la colección "boleto" en MongoDB.
 * Utiliza el codec POJO de {@link BoletoMongoEntidad} para mapear documentos
 * automáticamente.
 *
 * @author Jazmin
 */
public class BoletoDAO implements IDAOGenerico<BoletoMongoEntidad, ObjectId> {
 
    private final MongoCollection<BoletoMongoEntidad> coleccion;
 
    /**
     * Constructor que inicializa la conexión con la colección "boleto" de MongoDB.
     */
    public BoletoDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("boleto", BoletoMongoEntidad.class);
    }
 
 
    /**
     * Inserta un nuevo boleto en la colección.
     *
     * @param entidad el boleto a insertar. MongoDB asigna el {@code _id} automáticamente
     *                si no se especifica.
     * @return la misma entidad con el {@code id} asignado por MongoDB.
     */
    @Override
    public BoletoMongoEntidad insertar(BoletoMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }
 
 
    /**
     * Obtiene todos los boletos almacenados en la colección.
     *
     * @return lista de {@link BoletoMongoEntidad} con todos los boletos.
     *         Retorna lista vacía si no hay boletos.
     */
    @Override
    public List<BoletoMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    /**
     * Busca un boleto por su identificador único.
     *
     * @param id el {@link ObjectId} del boleto a buscar.
     * @return el boleto encontrado, o {@code null} si no existe.
     */
    @Override
    public BoletoMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
 
    /**
     * Obtiene todos los boletos asociados a una función específica.
     *
     * @param idFuncion el {@link ObjectId} de la función.
     * @return lista de boletos de esa función. Retorna lista vacía si no hay boletos.
     */
    public List<BoletoMongoEntidad> obtenerPorFuncion(ObjectId idFuncion) {
        return coleccion.find(eq("funcion", idFuncion)).into(new ArrayList<>());
    }
 
  
 
    /**
     * Reemplaza un boleto existente con los datos actualizados de la entidad.
     *
     * @param entidad el boleto con los datos actualizados. Debe tener un {@code id} válido.
     * @return {@code true} si el documento fue modificado, {@code false} si no hubo cambios.
     */
    public boolean actualizar(BoletoMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }
 
    /**
     * Elimina un boleto de la colección por su identificador.
     *
     * @param id el {@link ObjectId} del boleto a eliminar.
     * @return {@code true} si el documento fue eliminado, {@code false} si no existía.
     */
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}