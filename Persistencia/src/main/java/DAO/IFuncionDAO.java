package DAO;

import entidadesMongo.FuncionMongoEntidad;
import org.bson.types.ObjectId;
import java.util.List;

public interface IFuncionDAO {
    FuncionMongoEntidad insertar(FuncionMongoEntidad entidad);
    List<FuncionMongoEntidad> obtenerTodas();
    FuncionMongoEntidad obtenerPorId(ObjectId id);
    List<FuncionMongoEntidad> obtenerPorPelicula(ObjectId idPelicula);
    boolean actualizar(FuncionMongoEntidad entidad);
    boolean eliminar(ObjectId id);
}
