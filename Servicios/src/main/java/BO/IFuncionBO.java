/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.FuncionDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio disponibles para la gestión de funciones.
 * Las clases que la implementen deben proporcionar la lógica para consultar
 * las funciones asociadas a una película específica.
 */
public interface IFuncionBO {
    List<FuncionDTO> obtenerPorPelicula(String idPelicula)throws NegocioException;
    
}
