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
 *
 * @author 
 */
public class SalaDAO implements IDAOGenerico<SalaMongoEntidad, ObjectId>{
        private final MongoCollection<SalaMongoEntidad> coleccion;
 
    public SalaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("sala", SalaMongoEntidad.class);
    }

    @Override
    public SalaMongoEntidad insertar(SalaMongoEntidad sala){
        coleccion.insertOne(sala);
        return sala;
    }

    @Override
    public boolean actualizar(SalaMongoEntidad sala) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", sala.getId()), sala);
        return resultado.getModifiedCount() > 0;
    }

    @Override
    public List<SalaMongoEntidad> obtenerTodos() {
        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public SalaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }

    @Override
    public boolean eliminar(ObjectId id){
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}
