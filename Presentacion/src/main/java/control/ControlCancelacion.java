/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import BO.BoletoBO;
import BO.CancelacionBO;
import BO.IBoletoBO;
import BO.ICancelacionBO;
import DTOs.BoletoDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Jazmin
 */
public class ControlCancelacion {
      private final IBoletoBO boletoBO;
    private final ICancelacionBO cancelacionBO;

    public ControlCancelacion() {
        this.boletoBO = new BoletoBO();
        this.cancelacionBO = new CancelacionBO();
    }

    public List<BoletoDTO> obtenerTodos() throws NegocioException {
        return boletoBO.obtenerTodos();
    }

    public void cancelarBoleto(String id) throws NegocioException {
        cancelacionBO.cancelarBoleto(id);
    }

}
