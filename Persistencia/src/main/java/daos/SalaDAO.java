package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import entidades.Sala;
import conexion.ConexionMongo;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class SalaDAO {

    private MongoCollection<Sala> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("salas", Sala.class);
    }

    public boolean agregarSala(Sala sala) {
        try {
            obtenerColeccion().insertOne(sala);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar sala: " + e.getMessage());
            return false;
        }
    }

    public List<Sala> obtenerTodasLasSalas() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener salas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Sala buscarSalaPorId(ObjectId id) {
        try {
            return obtenerColeccion().find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar sala: " + e.getMessage());
            return null;
        }
    }
}