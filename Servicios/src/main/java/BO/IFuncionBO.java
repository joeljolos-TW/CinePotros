/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.FuncionDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 *
 * 
 */
public interface IFuncionBO {
    List<FuncionDTO> obtenerPorPelicula(String idPelicula)throws NegocioException;
    
}
