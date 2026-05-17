package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import entidades.Pelicula;
import conexion.ConexionMongo;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class PeliculaDAO implements IPeliculaDAO {

    private MongoCollection<Pelicula> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("peliculas", Pelicula.class);
    }

    @Override
    public boolean agregarPelicula(Pelicula pelicula) {
        try {
            obtenerColeccion().insertOne(pelicula);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Pelicula> obtenerTodasLasPeliculas() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener películas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Pelicula buscarPeliculaPorId(ObjectId id) {
        try {
            return obtenerColeccion().find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar película: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean actualizarPelicula(Pelicula pelicula) {
        try {
            obtenerColeccion().replaceOne(Filters.eq("_id", pelicula.getId()), pelicula);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarPelicula(ObjectId id) {
        try {
            obtenerColeccion().deleteOne(Filters.eq("_id", id));
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar película: " + e.getMessage());
            return false;
        }
    }
}