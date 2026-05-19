package Fachada;

import DTO.ValidacionDTO;

/**
 * Interfaz de la fachada del subsistema ValidarBoleto (módulo empleado).
 * Punto de entrada único para que la pantalla del empleado valide boletos
 * sin conocer los controles internos del subsistema.
 *
 * @author CinePotros
 */
public interface IFachadaValidacion {

    /**
     * Escanea un boleto vía QR y verifica que sea válido para la función del día.
     * Si es válido cambia su estado a ESCANEADO.
     *
     * @param dto DTO con el id del boleto escaneado.
     * @return ValidacionDTO con el resultado y la razón de fallo (si aplica).
     */
    ValidacionDTO escanearBoleto(ValidacionDTO dto);
}
