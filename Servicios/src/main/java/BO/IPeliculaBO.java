/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.SeleccionPeliculaDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio disponibles para la gestión de películas.
 * Las clases que la implementen deben proporcionar la lógica para consultar
 * todas las películas disponibles en el sistema.
 */
public interface IPeliculaBO {
    public List<SeleccionPeliculaDTO>obtenerTodos()throws NegocioException;
    
}
