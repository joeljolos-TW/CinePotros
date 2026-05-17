package DAO;

import java.util.List;

public interface IDAOGenerico<T> {
    void guardar(T entidad);
    void actualizar(T entidad);
    T buscarPorId(String id);
    void eliminar(String id);
    List<T> obtenerTodos();
}
