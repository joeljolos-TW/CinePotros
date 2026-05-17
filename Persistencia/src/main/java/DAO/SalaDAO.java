/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import entidadesMongo.SalaMongoEntidad;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author 
 */
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class SalaDAO implements ISalaDAO {
    private final MongoCollection<SalaMongoEntidad> coleccion;
 
    public SalaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("sala", SalaMongoEntidad.class);
    }
 
    public SalaMongoEntidad insertar(SalaMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    public List<SalaMongoEntidad> obtenerTodas() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    public SalaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
    
    public boolean actualizar(SalaMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}
