/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.FuncionDAO;
import DAO.SalaDAO;
import DTOs.FuncionDTO;
import DTOs.SalaDTO;
import adaptadores.BoletoPersistenciaAdapter;
import entidadesMongo.FuncionMongoEntidad;
import entidadesMongo.SalaMongoEntidad;
import excepcion.NegocioException;
import exception.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class FuncionBO implements IFuncionBO {

    private final FuncionDAO funcionDAO;
    private final SalaDAO salaDAO;
    private final BoletoPersistenciaAdapter adapter;

    public FuncionBO() {
        this.funcionDAO = new FuncionDAO();
        this.salaDAO = new SalaDAO();
        this.adapter = new BoletoPersistenciaAdapter();
    }

    @Override
    public List<FuncionDTO> obtenerPorPelicula(String idPelicula) throws NegocioException {
        try {
            List<FuncionMongoEntidad> funciones = funcionDAO.obtenerPorPelicula(
                    adapter.convertirStringAObjectId(idPelicula));

            List<FuncionDTO> dtos = new ArrayList<>();

            for (FuncionMongoEntidad funcion : funciones) {
                SalaMongoEntidad sala = salaDAO.obtenerPorId(funcion.getSala());

                if (sala == null) {
                    continue;
                }

                SalaDTO salaDTO = new SalaDTO(
                        sala.getTipo().name(),
                        sala.getNombre()
                );

                FuncionDTO dto = new FuncionDTO(
                        funcion.getId().toHexString(),
                        LocalDate.parse(funcion.getFecha()), 
                        LocalTime.parse(funcion.getHora()), 
                        salaDTO
                );

                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible obtener las funciones: " + e.getMessage());
        }
    }

}
