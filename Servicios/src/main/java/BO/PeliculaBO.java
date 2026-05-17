/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.PeliculaDAO;
import DAO.IPeliculaDAO;
import DTOs.SeleccionPeliculaDTO;
import entidadesMongo.PeliculaMongoEntidad;
import excepcion.NegocioException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class PeliculaBO implements IPeliculaBO {

    private final IPeliculaDAO peliculaDAO;

    public PeliculaBO() {
        this.peliculaDAO = new PeliculaDAO();
    }

    @Override
    public List<SeleccionPeliculaDTO> obtenerTodos() throws NegocioException {
        try {
            List<PeliculaMongoEntidad> entidades = peliculaDAO.obtenerTodas();
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
