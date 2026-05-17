package DAO;

import entidadesMongo.BoletoMongoEntidad;
import org.bson.types.ObjectId;
import java.util.List;

public interface IBoletoDAO {
    BoletoMongoEntidad insertar(BoletoMongoEntidad entidad);
    List<BoletoMongoEntidad> obtenerTodos();
    BoletoMongoEntidad obtenerPorId(ObjectId id);
    boolean actualizar(BoletoMongoEntidad entidad);
    boolean eliminar(ObjectId id);
}
