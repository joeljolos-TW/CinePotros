package BO;

import DTOs.FuncionDTO;
import excepcion.NegocioException;
import java.util.List;

public interface IFuncionBO {
    List<FuncionDTO> obtenerPorPelicula(String idPelicula) throws NegocioException;
}
