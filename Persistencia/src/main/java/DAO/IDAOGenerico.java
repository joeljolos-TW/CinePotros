package DAO;

import org.bson.types.ObjectId;

import java.util.List;

public interface IDAOGenerico<T, ID> {
    T insertar(T entidad);
    boolean actualizar(T entidad);
    T obtenerPorId(ID id);
    boolean eliminar(ID id);
    List<T> obtenerTodos();
}
