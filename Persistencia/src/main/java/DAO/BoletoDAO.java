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

public class BoletoDAO implements IDAOGenerico<BoletoMongoEntidad, ObjectId> {

    private final MongoCollection<BoletoMongoEntidad> coleccion;

    public BoletoDAO() {
        this.coleccion = ConexionMongoDB.getInstance().getDatabase().getCollection("boleto",BoletoMongoEntidad.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public BoletoMongoEntidad insertar(BoletoMongoEntidad entidad) {
        coleccion.insertOne(entidad);
        return entidad;
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    @Override
    public List<BoletoMongoEntidad> obtenerTodos() {
       return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public BoletoMongoEntidad obtenerPorId(ObjectId id) {
        return coleccion.find(eq("_id",id)).first();
    }

    public List<BoletoMongoEntidad> obtenerPorFuncion(ObjectId idFuncion) {
        return coleccion.find(eq("funcion",idFuncion)).into(new ArrayList<>());
    }

    public boolean actualizar(BoletoMongoEntidad entidad) {
        UpdateResult resultado = coleccion.replaceOne(eq("_id", entidad.getId()), entidad);
        return resultado.getModifiedCount() > 0;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    @Override
    public boolean eliminar(ObjectId id) {
        DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
        return resultado.getDeletedCount() > 0;
    }
}