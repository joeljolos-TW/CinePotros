/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.PeliculaDAO;
import DTOs.SeleccionPeliculaDTO;
import entidadesMongo.PeliculaMongoEntidad;
import excepcion.NegocioException;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de negocio que implementa la lógica de consulta de películas.
 * Obtiene las entidades de película desde la capa de persistencia y las
 * convierte a DTOs para su uso en la capa de presentación.
 * @author
 */
public class PeliculaBO implements IPeliculaBO {

    private final PeliculaDAO peliculaDAO;

    public PeliculaBO() {
        this.peliculaDAO = new PeliculaDAO();
    }

    @Override
    public List<SeleccionPeliculaDTO> obtenerTodos() throws NegocioException {
        try {
            List<PeliculaMongoEntidad> entidades = peliculaDAO.obtenerTodos();
            List<SeleccionPeliculaDTO> dtos = new ArrayList<>();

            for (PeliculaMongoEntidad entidad : entidades) {
                SeleccionPeliculaDTO dto = new SeleccionPeliculaDTO(
                        entidad.getId().toHexString(),
                        entidad.getImagen(),
                        entidad.getTitulo(), 
                        entidad.getCategoria());
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }

}
