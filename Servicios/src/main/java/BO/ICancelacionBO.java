/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import excepcion.NegocioException;

/**
 * Interfaz que define las operaciones de negocio disponibles para la cancelación de boletos.
 * Las clases que la implementen deben proporcionar la lógica para cancelar
 * un boleto registrado en el sistema.
 */
public interface ICancelacionBO {
    public void cancelarBoleto(String id)throws NegocioException;
    
    
}
