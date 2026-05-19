/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.BoletoDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio disponibles para la gestión de boletos.
 * Las clases que la implementen deben proporcionar la lógica para consultar
 * boletos individuales o en conjunto.
 */
public interface IBoletoBO {
    
    public List<BoletoDTO> obtenerTodos()throws NegocioException;
    public BoletoDTO obtenerPorId(String id)throws NegocioException;
    
}
