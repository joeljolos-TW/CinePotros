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

/**
 * DAO encargado de las operaciones CRUD sobre la colección "funcion" en MongoDB.
 * Utiliza el codec POJO de {@link FuncionMongoEntidad} para mapear documentos
 * automáticamente.
 * @author Jazmin
 */
public class FuncionDAO implements IDAOGenerico<FuncionMongoEntidad, ObjectId> {
 
    private final MongoCollection<FuncionMongoEntidad> coleccion;
 
    /**
     * Constructor que inicializa la conexión con la colección "funcion" de MongoDB.
     */
    public FuncionDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("funcion", FuncionMongoEntidad.class);
    }

    /**
     * Inserta una nueva función en la colección.
     *
     * @param entidad la función a insertar. MongoDB asigna el {@code _id}
     *                automáticamente si no se especifica.
     * @return la misma entidad con el {@code id} asignado por MongoDB.
     */
    @Override
    public FuncionMongoEntidad insertar(FuncionMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }
 
  
    /**
     * Obtiene todas las funciones almacenadas en la colección.
     *
     * @return lista de {@link FuncionMongoEntidad} con todas las funciones.
     *         Retorna lista vacía si no hay funciones.
     */
    @Override
    public List<FuncionMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    /**
     * Busca una función por su identificador único.
     *
     * @param id el {@link ObjectId} de la función a buscar.
     * @return la función encontrada, o {@code null} si no existe.
     */
    @Override
    public FuncionMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
 
    /**
     * Obtiene todas las funciones asociadas a una película específica.
     *
     * @param idPelicula el {@link ObjectId} de la película.
     * @return lista de funciones de esa película. Retorna lista vacía si no hay funciones.
     */
    public List<FuncionMongoEntidad> obtenerPorPelicula(ObjectId idPelicula) {
        return coleccion.find(eq("pelicula", idPelicula)).into(new ArrayList<>());
    }
 
    /**
     * Reemplaza una función existente con los datos actualizados de la entidad.
     *
     * @param entidad la función con los datos actualizados. Debe tener un {@code id} válido.
     * @return {@code true} si el documento fue modificado, {@code false} si no hubo cambios.
     */
    @Override
    public boolean actualizar(FuncionMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    /**
     * Elimina una función de la colección por su identificador.
     *
     * @param id el {@link ObjectId} de la función a eliminar.
     * @return {@code true} si el documento fue eliminado, {@code false} si no existía.
     */
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}