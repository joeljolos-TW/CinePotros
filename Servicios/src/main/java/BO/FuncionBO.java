/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import daos.FuncionDAO;
import daos.SalaDAO;
import daos.IFuncionDAO;
import daos.ISalaDAO;
import DTOs.FuncionDTO;
import DTOs.SalaDTO;
import entidades.Funcion;
import entidades.Sala;
import excepcion.NegocioException;
import exception.PersistenciaException;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FuncionBO implements IFuncionBO {

    private final IFuncionDAO funcionDAO;
    private final ISalaDAO salaDAO;

    public FuncionBO() {
        this.funcionDAO = new FuncionDAO();
        this.salaDAO = new SalaDAO();
    }

    @Override
    public List<FuncionDTO> obtenerPorPelicula(String idPelicula) throws NegocioException {
        try {
            List<Funcion> funciones = funcionDAO.buscarPorPelicula(new ObjectId(idPelicula));
            List<FuncionDTO> dtos = new ArrayList<>();

            for (Funcion funcion : funciones) {
                SalaDTO salaDTO = new SalaDTO(
                        "Regular",
                        "Sala " + funcion.getNumeroSala()
                );

                java.util.Date horaInicio = funcion.getHoraInicio();
                LocalDate fecha = horaInicio != null ? new java.sql.Date(horaInicio.getTime()).toLocalDate() : LocalDate.now();
                LocalTime hora = horaInicio != null ? new java.sql.Time(horaInicio.getTime()).toLocalTime() : LocalTime.now();

                FuncionDTO dto = new FuncionDTO(
                        funcion.getId().toHexString(),
                        fecha, 
                        hora, 
                        salaDTO
                );

                dtos.add(dto);
            }

            return dtos;

        } catch (Exception e) {
            throw new NegocioException("No fue posible obtener las funciones: " + e.getMessage());
        }
    }
}
