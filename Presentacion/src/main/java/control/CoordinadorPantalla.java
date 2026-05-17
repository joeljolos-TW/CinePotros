/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.BoletoDTO;
import Elements.Panels.SwitchPanel;
import Mediator.PanelMediator;


/**
 *
 * 
 */
public class CoordinadorPantalla {
  private final PanelMediator panelMediator;
    private final ControlCancelacion controlCancelacion;

    public CoordinadorPantalla(ControlCancelacion controlCancelacion) {
        this.panelMediator = SwitchPanel.getInstance();
        this.controlCancelacion = controlCancelacion;
    }

    public void abrirInformacionBoleto(BoletoDTO boleto) {
        panelMediator.changePanel("infoBoleto", boleto);
    }

    public void abrirConfirmacion(BoletoDTO boleto) {
        panelMediator.changePanel("confirmacionCancelacion", boleto);
    }

    public void cerrarConfirmacion(BoletoDTO boleto) {
        panelMediator.changePanel("infoBoleto", boleto);
    }

    public void regresarAMisBoletos() {
        panelMediator.changePanel("misBoletos", null);
    }

}
