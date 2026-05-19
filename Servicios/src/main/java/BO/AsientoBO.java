/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.AsientoDAO;
import DTOs.AsientoDTO;
import adaptadores.BoletoPersistenciaAdapter;
import excepcion.NegocioException;
import exception.PersistenciaException;
import itson.dominio.Asiento;
import itson.dominio.EstadoAsiento;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *BO encargado de gestionar los asientos de una función.
 *
 */
public class AsientoBO implements IAsientoBO {

    private final AsientoDAO asientoDAO;
    private final BoletoPersistenciaAdapter adapter;

    public AsientoBO() {
        this.asientoDAO = new AsientoDAO();
        this.adapter = new BoletoPersistenciaAdapter();
    }

    /**
     * Obtiene todos los asientos de una función y los convierte a DTOs.
     *
     * @param idFuncion id de la función como String.
     * @return lista de AsientoDTO con fila, numero y estado.
     * @throws NegocioException si ocurre un error.
     */
    @Override
    public List<AsientoDTO> obtenerPorFuncion(String idFuncion) throws NegocioException {
        try {
             System.out.println("Buscando asientos para funcion: " + idFuncion);
            ObjectId objectId = adapter.convertirStringAObjectId(idFuncion);
            List<Asiento> asientos = asientoDAO.obtenerPorFuncion(objectId);
             System.out.println("Asientos encontrados: " + asientos.size());
            List<AsientoDTO> dtos = new ArrayList<>();
            for (Asiento asiento : asientos) {
                dtos.add(new AsientoDTO(
                        asiento.getFila(),
                        asiento.getNumero(),
                        asiento.getEstado()
                ));
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible obtener los asientos: " + e.getMessage());
        }
    }

    /**
     * Marca los asientos seleccionados como OCUPADO en la función.
     *
     * @param idFuncion id de la función como String.
     * @param asientos lista de AsientoDTO seleccionados.
     * @throws NegocioException si ocurre un error.
     */
    @Override
    public void ocuparAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException {
        try {
            ObjectId objectId = adapter.convertirStringAObjectId(idFuncion);
            for (AsientoDTO asiento : asientos) {
                asientoDAO.actualizarEstado(objectId, asiento.getFila(), asiento.getNumero(), EstadoAsiento.OCUPADO);
            }
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible ocupar los asientos: " + e.getMessage());
        }
    }

    /**
     * Libera los asientos de un boleto cancelado.
     *
     * @param idFuncion id de la función como String.
     * @param asientos lista de AsientoDTO a liberar.
     * @throws NegocioException si ocurre un error.
     */
    @Override
    public void liberarAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException {
        try {
            ObjectId objectId = adapter.convertirStringAObjectId(idFuncion);
            for (AsientoDTO asiento : asientos) {
                asientoDAO.actualizarEstado(objectId, asiento.getFila(), asiento.getNumero(), EstadoAsiento.DISPONIBLE);
            }
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible liberar los asientos: " + e.getMessage());
        }
    }
}
