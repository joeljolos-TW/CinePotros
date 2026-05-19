package Fachada;

import PaymentDTO.PaymentSummaryDTO;

/**
 * Interfaz de la fachada del subsistema VerificacionPago.
 * Punto de entrada único para que la pantalla de caja calcule
 * y confirme pagos sin conocer el PaymentBO interno.
 *
 * @author CinePotros
 */
public interface IFachadaPago {

    /**
     * Calcula el precio total de la compra según los asientos y tipo de sala.
     */
    PaymentSummaryDTO calcularPrecio(PaymentSummaryDTO dto);

    /**
     * Confirma el pago verificando que el monto recibido sea suficiente.
     */
    boolean confirmarPago(PaymentSummaryDTO dto, double montoRecibido);

    /**
     * Cancela el proceso de pago.
     */
    void cancelarPago(PaymentSummaryDTO dto);
}
