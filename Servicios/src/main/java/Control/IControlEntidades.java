package Control;

import excepcion.NegocioException;

import java.util.List;

public interface IControlEntidades<T> {
    T agregar(T entidad) throws NegocioException;
    void actualizar(T entidad) throws NegocioException;
    T obtenerPorId(String id) throws NegocioException;
    void eliminar(String id) throws NegocioException;
    List<T> obtenerTodos() throws NegocioException;
}
