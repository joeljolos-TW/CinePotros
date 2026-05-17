package DAO;

import entidadesMongo.SalaMongoEntidad;
import org.bson.types.ObjectId;
import java.util.List;

public interface ISalaDAO {
    SalaMongoEntidad insertar(SalaMongoEntidad entidad);
    List<SalaMongoEntidad> obtenerTodas();
    SalaMongoEntidad obtenerPorId(ObjectId id);
    boolean actualizar(SalaMongoEntidad entidad);
    boolean eliminar(ObjectId id);
}
