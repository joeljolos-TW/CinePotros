/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import excepcion.NegocioException;

/**
 *
 * @author Jamzin
 */
public interface ICancelacionBO {
    public void cancelarBoleto(String id)throws NegocioException;
    
    
}
