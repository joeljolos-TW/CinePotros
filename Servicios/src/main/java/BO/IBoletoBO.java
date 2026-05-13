/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.BoletoDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Jazmin
 */
public interface IBoletoBO {
    
    public List<BoletoDTO> obtenerTodos()throws NegocioException;
    public BoletoDTO obtenerPorId(String id)throws NegocioException;
    
}
