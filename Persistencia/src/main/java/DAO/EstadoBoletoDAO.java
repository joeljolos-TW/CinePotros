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
 *DAO encargado de actualizar el estado de un boleto en la base de datos MongoDB.
 * @author Jazmin
 */
public class EstadoBoletoDAO {
    private final MongoCollection<BoletoMongoEntidad> coleccion;
    /**
     * Constructor que inicializa la conexión con la colección "boleto" de MongoDB.
     */
    public EstadoBoletoDAO() {
        this.coleccion = ConexionMongoDB.getInstance()
                .getDatabase()
                .getCollection("boleto", BoletoMongoEntidad.class);
    }
    /**
     * Actualiza el estado de un boleto identificado por su ID.
     * Busca el boleto en la base de datos, le asigna el nuevo estado
     * y lo reemplaza en la colección.
     *
     * @param id          identificador del boleto como cadena hexadecimal de 24 caracteres.
     * @param nuevoEstado el nuevo estado a asignar al boleto ({@link EstadoBoleto}).
     * @return true si el documento fue modificado exitosamente,
     * @false si no hubo cambios.
     * @throws PersistenciaException si el boleto no existe, el ID es inválido,
     *                               o ocurre un error en la base de datos.
     */
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
