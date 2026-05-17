package daos;

import entidades.Pelicula;
import org.bson.types.ObjectId;
import java.util.List;

public interface IPeliculaDAO {
    boolean agregarPelicula(Pelicula pelicula);
    List<Pelicula> obtenerTodasLasPeliculas();
    Pelicula buscarPeliculaPorId(ObjectId id);
    boolean actualizarPelicula(Pelicula pelicula);
    boolean eliminarPelicula(ObjectId id);
}
