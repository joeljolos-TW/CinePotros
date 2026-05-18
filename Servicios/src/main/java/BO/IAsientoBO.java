/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.AsientoDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IAsientoBO {
    public List<AsientoDTO> obtenerPorFuncion(String idFuncion) throws NegocioException;
    public void ocuparAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;
    public void liberarAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;

}
