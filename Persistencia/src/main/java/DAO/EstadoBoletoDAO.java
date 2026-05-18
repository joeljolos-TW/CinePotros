/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import entidadesMongo.BoletoMongoEntidad;
import exception.PersistenciaException;
import itson.dominio.EstadoBoleto;
import org.bson.types.ObjectId;

/**
 *
 * 
 */
public class EstadoBoletoDAO {
    private final MongoCollection<BoletoMongoEntidad> coleccion;

    public EstadoBoletoDAO() {
        this.coleccion = ConexionMongoDB.getInstance()
                .getDatabase()
                .getCollection("boleto", BoletoMongoEntidad.class);
    }

    public boolean actualizarEstado(String id, EstadoBoleto nuevoEstado) throws PersistenciaException {
        try {
            ObjectId objectId = new ObjectId(id);
            BoletoMongoEntidad entidad = coleccion.find(eq("_id", objectId)).first();
            if (entidad == null) throw new PersistenciaException("Boleto no encontrado.");
            entidad.setEstado(nuevoEstado);
            UpdateResult resultado = coleccion.replaceOne(eq("_id", objectId), entidad);
            return resultado.getModifiedCount() > 0;
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el estado: " + e.getMessage());
        }
    }
}
