package Control;

import excepcion.NegocioException;

import java.util.List;

public interface IControlEntidades<T, ID> {
    void agregar(T entidad) throws NegocioException;
    void actualizar(T entidad) throws NegocioException;
    T buscarPorId(ID id) throws NegocioException;
    void eliminar(ID id) throws NegocioException;
    List<T> obtenerTodos() throws NegocioException;
}
