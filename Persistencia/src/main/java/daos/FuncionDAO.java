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
public class FuncionDAO implements IFuncionDAO {

    private MongoCollection<Funcion> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("funciones", Funcion.class);
    }

    @Override
    public boolean agregarFuncion(Funcion funcion) {
        try {
            obtenerColeccion().insertOne(funcion);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar función: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Funcion> obtenerTodasLasFunciones() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener funciones: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Funcion buscarFuncionPorId(ObjectId id) {
        try {
            return obtenerColeccion().find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar función: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Funcion> buscarPorPelicula(ObjectId idPelicula) {
        try {
            return obtenerColeccion().find(Filters.eq("pelicula", idPelicula)).into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al buscar funciones por película: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean actualizarFuncion(Funcion funcion) {
        try {
            obtenerColeccion().replaceOne(Filters.eq("_id", funcion.getId()), funcion);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar función: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarFuncion(ObjectId id) {
        try {
            obtenerColeccion().deleteOne(Filters.eq("_id", id));
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar función: " + e.getMessage());
            return false;
        }
    }
}