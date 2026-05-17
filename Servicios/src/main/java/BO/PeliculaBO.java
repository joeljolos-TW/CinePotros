/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import daos.PeliculaDAO;
import daos.IPeliculaDAO;
import DTOs.SeleccionPeliculaDTO;
import entidades.Pelicula;
import excepcion.NegocioException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaBO implements IPeliculaBO {

    private final IPeliculaDAO peliculaDAO;

    public PeliculaBO() {
        this.peliculaDAO = new PeliculaDAO();
    }

    @Override
    public List<SeleccionPeliculaDTO> obtenerTodos() throws NegocioException {
        try {
            List<Pelicula> entidades = peliculaDAO.obtenerTodasLasPeliculas();
            List<SeleccionPeliculaDTO> dtos = new ArrayList<>();

            for (Pelicula entidad : entidades) {
                SeleccionPeliculaDTO dto = new SeleccionPeliculaDTO(
                        entidad.getId().toHexString(),
                        "", // imagen (entidades.Pelicula no lo tiene)
                        entidad.getTitulo(), 
                        entidad.getGenero()); // mapped to categoria
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
