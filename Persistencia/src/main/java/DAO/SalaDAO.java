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
public class SalaDAO {
        private final MongoCollection<SalaMongoEntidad> coleccion;
 
    public SalaDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase()
                .getCollection("sala", SalaMongoEntidad.class);
    }
 
 
    public List<SalaMongoEntidad> obtenerTodas() {
        return coleccion.find().into(new ArrayList<>());
    }
 
    public SalaMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id", id)).first();
    }
    
}
