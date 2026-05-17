package daos;

import entidades.Funcion;
import org.bson.types.ObjectId;
import java.util.List;

public interface IFuncionDAO {
    boolean agregarFuncion(Funcion funcion);
    List<Funcion> obtenerTodasLasFunciones();
    Funcion buscarFuncionPorId(ObjectId id);
    List<Funcion> buscarPorPelicula(ObjectId idPelicula);
    boolean actualizarFuncion(Funcion funcion);
    boolean eliminarFuncion(ObjectId id);
}
