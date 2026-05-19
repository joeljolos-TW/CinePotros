/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.BoletoDAO;
import DAO.EstadoBoletoDAO;
import adaptadores.BoletoPersistenciaAdapter;
import entidadesMongo.BoletoMongoEntidad;
import excepcion.NegocioException;
import exception.PersistenciaException;
import itson.dominio.EstadoBoleto;

/**
 *Objeto de negocio que implementa la lógica de cancelación de boletos.
 * @author Jazmin
 */
public class CancelacionBO implements ICancelacionBO {

    private final BoletoDAO boletoDAO;
    private final EstadoBoletoDAO estadoBoletoDAO;
    private final BoletoPersistenciaAdapter adapter;

    public CancelacionBO() {
        this.boletoDAO = new BoletoDAO();
        this.estadoBoletoDAO = new EstadoBoletoDAO();
        this.adapter = new BoletoPersistenciaAdapter();
    }

    @Override
    public void cancelarBoleto(String id) throws NegocioException {
        try {
            BoletoMongoEntidad entidad = boletoDAO.obtenerPorId(
                    adapter.convertirStringAObjectId(id)
            );
            if (entidad == null) {
                throw new NegocioException("No se encontró ningún boleto.");
            }
            if (entidad.getEstado() != EstadoBoleto.PENDIENTE) {
                throw new NegocioException("Solo se pueden cancelar boletos con estado PENDIENTE.");
            }
            estadoBoletoDAO.actualizarEstado(id, EstadoBoleto.CANCELADO);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al cancelar: " + e.getMessage());
        }
    }
}
