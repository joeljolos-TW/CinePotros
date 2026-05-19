/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import entidadesMongo.SalaMongoEntidad;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * DAO encargado de las operaciones CRUD sobre la colección "sala" en MongoDB.
 * Utiliza el codec POJO de {@link SalaMongoEntidad} para mapear documentos
 * automáticamente.
 */
public class SalaDAO implements IDAOGenerico<SalaMongoEntidad, ObjectId> {
 
    private final MongoCollection<SalaMongoEntidad> coleccion;
 
    /**
     * Constructor que inicializa la conexión con la colección "sala" de MongoDB.
     */
    public SalaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("sala", SalaMongoEntidad.class);
    }
 
    /**
     * Inserta una nueva sala en la colección.
     *
     * @param sala la sala a insertar. MongoDB asigna el {@code _id}
     *             automáticamente si no se especifica.
     * @return la misma sala con el {@code id} asignado por MongoDB.
     */
    @Override
    public SalaMongoEntidad insertar(SalaMongoEntidad sala) {
        coleccion.insertOne(sala);
        return sala;
    }
 

    /**
     * Obtiene todas las salas almacenadas en la colección.
     *
     * @return lista de {@link SalaMongoEntidad} con todas las salas.
     *         Retorna lista vacía si no hay salas.
     */
    @Override
    public List<SalaMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    /**
     * Busca una sala por su identificador único.
     *
     * @param id el {@link ObjectId} de la sala a buscar.
     * @return la sala encontrada, o {@code null} si no existe.
     */
    @Override
    public SalaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
 
    /**
     * Reemplaza una sala existente con los datos actualizados de la entidad.
     *
     * @param sala la sala con los datos actualizados. Debe tener un {@code id} válido.
     * @return {@code true} si el documento fue modificado, {@code false} si no hubo cambios.
     */
    @Override
    public boolean actualizar(SalaMongoEntidad sala) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", sala.getId()), sala);
        return resultado.getModifiedCount() > 0;
    }
 

    /**
     * Elimina una sala de la colección por su identificador.
     *
     * @param id el {@link ObjectId} de la sala a eliminar.
     * @return {@code true} si el documento fue eliminado, {@code false} si no existía.
     */
    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}