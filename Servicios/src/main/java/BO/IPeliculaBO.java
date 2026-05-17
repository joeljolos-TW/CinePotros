package BO;

import DTOs.SeleccionPeliculaDTO;
import excepcion.NegocioException;
import java.util.List;

public interface IPeliculaBO {
    List<SeleccionPeliculaDTO> obtenerTodos() throws NegocioException;
}
