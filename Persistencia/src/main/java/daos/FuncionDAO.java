package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import entidades.Funcion;
import conexion.ConexionMongo;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class FuncionDAO {

    private MongoCollection<Funcion> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("funciones", Funcion.class);
    }

    public boolean agregarFuncion(Funcion funcion) {
        try {
            obtenerColeccion().insertOne(funcion);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar función: " + e.getMessage());
            return false;
        }
    }

    public List<Funcion> obtenerTodasLasFunciones() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener funciones: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Funcion buscarFuncionPorId(ObjectId id) {
        try {
            return obtenerColeccion().find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar función: " + e.getMessage());
            return null;
        }
    }
}