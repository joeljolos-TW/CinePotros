package Control;

import DAO.IDAOGenerico;
import excepcion.NegocioException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ControlEntidades<DTO, Entidad, ID> implements IControlEntidades<DTO> {

    private final IDAOGenerico<Entidad, ID> dao;
    private final Function<DTO, Entidad> toEntidad;
    private final Function<Entidad, DTO> toDTO;
    private final Function<String, ID> convertidorId;

    public ControlEntidades(IDAOGenerico<Entidad, ID> dao, Function<DTO, Entidad> toEntidad, Function<Entidad, DTO> toDTO, Function<String, ID> convertidorId) {
        this.dao = dao;
        this.toEntidad = toEntidad;
        this.toDTO = toDTO;
        this.convertidorId = convertidorId;
    }

    @Override
    public DTO obtenerPorIdPorId(String id) throws NegocioException {
        try {
            ID idConvertido = convertidorId.apply(id);
            Entidad entidad = dao.obtenerPorId(idConvertido);
            if (entidad == null) {
                throw new NegocioException("No se encontró ningún registro con el id: " + id);
            }
            return toDTO.apply(entidad);
        } catch (Exception e) {
            throw new NegocioException("Error, el id no es válido o no coincide: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) throws NegocioException {
        try {
            ID idConvertido = convertidorId.apply(id);
            boolean eliminado = dao.eliminar(idConvertido);
            if (!eliminado) {
                throw new NegocioException("No se encontró un registro con el id: " + id);
            }
        } catch (Exception e) {
            throw new NegocioException("Error al intentar eliminar: " + e.getMessage());
        }
    }

    @Override
    public void agregar(DTO dto) throws NegocioException {
        try{
            Entidad entidad = toEntidad.apply(dto);
            Entidad insertado = dao.insertar(entidad);
            if(insertado == null){
                throw new NegocioException("No se pudo guardar la entidad");
            }
        }catch (Exception e){
            throw new NegocioException("Error al insertar la entidad: " + e.getMessage());
        }
    }
    @Override
    public void actualizar(DTO dto) throws NegocioException {
        try{
            Entidad entidad = toEntidad.apply(dto);
            boolean actualizado = dao.actualizar(entidad);
            if(!actualizado){
                throw new NegocioException("No se encontro la entidad para actualizar");
            }
        }catch (Exception e){
            throw new NegocioException("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public List<DTO> obtenerTodos() throws NegocioException {
        try{
            List<Entidad> lista = dao.obtenerTodos();
            List<DTO> listaDTO = new ArrayList<>();
            for(Entidad e : lista){
                listaDTO.add(toDTO.apply(e));
            }
            return listaDTO;
        }catch (Exception e){
            throw new NegocioException("Error al recuperar la lista: " + e.getMessage());
        }
    }
}