package Fachada;

import Pago.PaymentBO;
import PaymentDTO.PaymentSummaryDTO;

/**
 * Implementación de la fachada del subsistema VerificacionPago.
 *
 * @author CinePotros
 */
public class FachadaPago implements IFachadaPago {

    @Override
    public PaymentSummaryDTO calcularPrecio(PaymentSummaryDTO dto) {
        PaymentBO bo = new PaymentBO(dto);
        bo.calcularPrecio();
        return dto;
    }

    @Override
    public boolean confirmarPago(PaymentSummaryDTO dto, double montoRecibido) {
        PaymentBO bo = new PaymentBO(dto);
        bo.calcularPrecio();
        return bo.confirmarPago(montoRecibido);
    }

    @Override
    public void cancelarPago(PaymentSummaryDTO dto) {
        PaymentBO bo = new PaymentBO(dto);
        bo.cancelar();
    }
}
