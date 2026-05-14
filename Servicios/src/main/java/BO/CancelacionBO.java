/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.BoletoDAO;
import adaptadores.BoletoPersistenciaAdapter;
import entidadesMongo.BoletoMongoEntidad;
import excepcion.NegocioException;
import exception.PersistenciaException;
import itson.dominio.EstadoBoleto;

/**
 *
 * @author Jazmin
 */
public class CancelacionBO implements ICancelacionBO{
    private final BoletoDAO boletoDAO;
    private final BoletoPersistenciaAdapter adapter;

    public CancelacionBO() {
       this.boletoDAO = new BoletoDAO();
       this.adapter = new BoletoPersistenciaAdapter();
    }
    
    @Override
    public void cancelarBoleto(String id) throws NegocioException {
        try {
            BoletoMongoEntidad entidad = boletoDAO.obtenerPorId(adapter.convertirStringAObjectId(id));
            if(entidad == null){
                throw new NegocioException("No se encontró ningun boleto");
            }
            if(entidad.getEstado() != EstadoBoleto.PENDIENTE){
                throw new NegocioException("Solo se pueden cancelar boletos con estado PENDIENTE.");
            }
            entidad.setEstado(EstadoBoleto.CANCELADO);
            boletoDAO.actualizar(entidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al cancelar" + e.getMessage());
            
        }
    }
    
}
