/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.SeleccionPeliculaDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author
 */
public interface IPeliculaBO {
    public List<SeleccionPeliculaDTO>obtenerTodos()throws NegocioException;
    
}
