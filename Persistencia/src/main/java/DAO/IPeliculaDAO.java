package DAO;

import entidadesMongo.PeliculaMongoEntidad;
import org.bson.types.ObjectId;
import java.util.List;

public interface IPeliculaDAO {
    PeliculaMongoEntidad insertar(PeliculaMongoEntidad entidad);
    List<PeliculaMongoEntidad> obtenerTodas();
    PeliculaMongoEntidad obtenerPorId(ObjectId id);
    boolean actualizar(PeliculaMongoEntidad entidad);
    boolean eliminar(ObjectId id);
}
