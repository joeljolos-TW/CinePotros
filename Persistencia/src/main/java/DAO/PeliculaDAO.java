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

/**
 * DAO encargado de las operaciones CRUD sobre la colección "pelicula" en MongoDB.
 * Utiliza el codec POJO de PeliculaMongoEntidad para mapear documentos
 * automáticamente.
 *
 * @author Jazmin
 */
public class PeliculaDAO implements IDAOGenerico<PeliculaMongoEntidad, ObjectId> {
 
    private final MongoCollection<PeliculaMongoEntidad> coleccion;
 
    /**
     * Constructor que inicializa la conexión con la colección "pelicula" de MongoDB.
     */
    public PeliculaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("pelicula", PeliculaMongoEntidad.class);
    }
 
 
    /**
     * Inserta una nueva película en la colección.
     *
     * @param entidad la película a insertar. MongoDB asigna el {@code _id}
     *                automáticamente si no se especifica.
     * @return la misma entidad con el {@code id} asignado por MongoDB.
     */
    @Override
    public PeliculaMongoEntidad insertar(PeliculaMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    /**
     * Obtiene todas las películas almacenadas en la colección.
     *
     * @return lista de {@link PeliculaMongoEntidad} con todas las películas.
     *         Retorna lista vacía si no hay películas.
     */
    @Override
    public List<PeliculaMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    /**
     * Busca una película por su identificador único.
     *
     * @param id el {@link ObjectId} de la película a buscar.
     * @return la película encontrada, o {@code null} si no existe.
     */
    @Override
    public PeliculaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
 
    /**
     * Reemplaza una película existente con los datos actualizados de la entidad.
     *
     * @param entidad la película con los datos actualizados. Debe tener un {@code id} válido.
     * @return {@code true} si el documento fue modificado, {@code false} si no hubo cambios.
     */
    @Override
    public boolean actualizar(PeliculaMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    /**
     * Elimina una película de la colección por su identificador.
     *
     * @param id el {@link ObjectId} de la película a eliminar.
     * @return {@code true} si el documento fue eliminado, {@code false} si no existía.
     */
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}