/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import DTOs.BoletoDTO;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import entidadesMongo.PeliculaMongoEntidad;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter de negocio encargado de convertir entidades de MongoDB a DTOs para la
 * capa de presentación.
 *
 * @author Jazmin
 */
public class BoletoNegocioAdapter {

    /**
     * Convierte una entidad de boleto junto con su función y película a un
     * BoletoDTO para mostrar en pantalla.
     *
     * @param boleto entidad del boleto en MongoDB.
     * @param funcion entidad de la función.
     * @param pelicula entidad de la película.
     * @return BoletoDTO con toda la información para la pantalla.
     */
    public BoletoDTO convertirADTO(BoletoMongoEntidad boleto, FuncionMongoEntidad funcion, PeliculaMongoEntidad pelicula) {
        if (boleto == null || funcion == null) {
            return null;
        }

        BoletoDTO dto = new BoletoDTO();
        dto.setId(boleto.getId().toHexString());
        dto.setNumAsiento(boleto.getNumAsiento());
        dto.setFecha(funcion.getFecha());
        dto.setHora(funcion.getHora());
        dto.setTotal(boleto.getTotal());
        dto.setEstado(boleto.getEstado());

        if (pelicula != null) {
            dto.setTituloPelicula(pelicula.getTitulo());
        }

        return dto;
    }

    public List<BoletoDTO> convertirListaADTO(List<BoletoMongoEntidad> boletos,
            List<FuncionMongoEntidad> funciones, List<PeliculaMongoEntidad> peliculas) {

        List<BoletoDTO> dtos = new ArrayList<>();
        if (boletos == null) {
            return dtos;
        }
        for (int i = 0; i < boletos.size(); i++) {
            FuncionMongoEntidad funcion;
            if (funciones != null && i < funciones.size()) {
                funcion = funciones.get(i);
            } else {
                funcion = null;
            }
            PeliculaMongoEntidad pelicula;
            if (peliculas != null && i < peliculas.size()) {
                pelicula = peliculas.get(i);
            } else {
                pelicula = null;
            }
            BoletoDTO dto = convertirADTO(boletos.get(i), funcion, pelicula);
            if (dto != null) {
                dtos.add(dto);
            }

        }
        return dtos;
    }

}
