package Fachada;

import BO.ValidacionBO;
import DTO.ValidacionDTO;

/**
 * Implementación de la fachada del subsistema ValidarBoleto.
 *
 * @author CinePotros
 */
public class FachadaValidacion implements IFachadaValidacion {

    @Override
    public ValidacionDTO escanearBoleto(ValidacionDTO dto) {
        ValidacionBO bo = new ValidacionBO(dto);
        return bo.escanearBoleto();
    }
}
