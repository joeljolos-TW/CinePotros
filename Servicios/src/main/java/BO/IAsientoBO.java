/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.AsientoDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio disponibles para la gestión de asientos.
 * Las clases que la implementen deben proporcionar la lógica para consultar,
 * ocupar y liberar asientos asociados a una función específica.
 */
public interface IAsientoBO {
    public List<AsientoDTO> obtenerPorFuncion(String idFuncion) throws NegocioException;
    public void ocuparAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;
    public void liberarAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;

}
