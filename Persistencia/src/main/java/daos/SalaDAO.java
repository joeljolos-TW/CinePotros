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
public class SalaDAO implements ISalaDAO {

    private MongoCollection<Sala> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("salas", Sala.class);
    }

    @Override
    public boolean agregarSala(Sala sala) {
        try {
            obtenerColeccion().insertOne(sala);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar sala: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Sala> obtenerTodasLasSalas() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener salas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Sala buscarSalaPorId(ObjectId id) {
        try {
            return obtenerColeccion().find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar sala: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean actualizarSala(Sala sala) {
        try {
            obtenerColeccion().replaceOne(Filters.eq("_id", sala.getId()), sala);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar sala: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarSala(ObjectId id) {
        try {
            obtenerColeccion().deleteOne(Filters.eq("_id", id));
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar sala: " + e.getMessage());
            return false;
        }
    }
}