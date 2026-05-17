package BO;

import DTOs.BoletoDTO;
import excepcion.NegocioException;
import java.util.List;

public interface IBoletoBO {
    List<BoletoDTO> obtenerTodos();
    BoletoDTO obtenerPorId(String id) throws NegocioException;
}
