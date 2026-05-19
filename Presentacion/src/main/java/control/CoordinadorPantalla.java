/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.BoletoDTO;
import Elements.Panels.SwitchPanel;
import Mediator.PanelMediator;


/**
 * Coordinador de navegación entre pantallas para el flujo de cancelación de boletos.
 * Actúa como intermediario entre los paneles de presentación y el PanelMediator,
 * centralizando la lógica de navegación para mantener los paneles desacoplados.
 * @author Jazmin
 */

public class CoordinadorPantalla {
  private final PanelMediator panelMediator;
    private final ControlCancelacion controlCancelacion;

     /**
     * Constructor que inicializa el coordinador con el mediador de paneles
     * y el control de cancelación.
     *
     * @param controlCancelacion el control encargado de ejecutar la lógica de cancelación.
     */
    public CoordinadorPantalla(ControlCancelacion controlCancelacion) {
        this.panelMediator = SwitchPanel.getInstance();
        this.controlCancelacion = controlCancelacion;
    }

    /**
     * Navega al panel de información de un boleto específico.
     *
     * @param boleto el  BoletoDTO cuya información se desea mostrar.
     */
    public void abrirInformacionBoleto(BoletoDTO boleto) {
        panelMediator.changePanel("infoBoleto", boleto);
    }

    /**
     * Navega al panel de confirmación de cancelación con el boleto seleccionado.
     *
     * @param boleto el BoletoDTO que se desea cancelar.
     */
    public void abrirConfirmacion(BoletoDTO boleto) {
        panelMediator.changePanel("confirmacionCancelacion", boleto);
    }

    /**
     * Cierra el panel de confirmación y regresa al panel de mis boletos
     * cuando el usuario decide no cancelar.
     *
     * @param boleto el BoletoDTO que se conserva sin cambios.
     */
    public void cerrarConfirmacion(BoletoDTO boleto) {
        panelMediator.changePanel("misBoletos", boleto);
    }

    /**
     * Navega al panel de mis boletos después de completar una cancelación exitosa.
     */
    public void regresarAMisBoletos() {
        panelMediator.changePanel("misBoletos", null);
    }
}