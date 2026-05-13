package Pago;

import itson.dominio.Boleto;
import itson.dominio.EstadoPago;
import itson.dominio.Pago;
import itson.dominio.TipoSala;
import PaymentDTO.PaymentSummaryDTO;
import DAO.BoletoDAO;
import DAO.PagoDAO;
import org.bson.types.ObjectId;

public class PaymentBO {
    // Modificar según tu DAO
    private PagoDAO pagoDAO;
    private PaymentSummaryDTO dto;

    private double precioEntradas;
    private double servicio;
    private double total;

    private static final ObjectId EMPLEADO_ID = new ObjectId();
    public PaymentBO(PaymentSummaryDTO dto) {
        this.dto = dto;
        // Modificar según tu DAO - inicializar tus DAOs aquí
        this.pagoDAO = new PagoDAO();
    }

    /**
     * Calcula precioEntradas, servicio y total
     * a partir de la cantidad de asientos y el tipo de sala.
     */
    public void calcularPrecio() {
        double precioPorAsiento = switch (dto.getTipoSala()) {
            case TRADICIONAL -> 80.0;  // Modificar según tus precios
            case KIDS   -> 60.0; // Modificar según tus precios
            case VIP    -> 130.0; // Modificar según tus precios
        };

        this.precioEntradas = precioPorAsiento * dto.getAsientos().size();
        this.servicio = 0.0; // Modificar según tu regla de negocio
        this.total = this.precioEntradas + this.servicio;
    }

    /**
     * Simula la confirmación del cajero.
     * Devuelve true si el pago fue confirmado, false si fue rechazado.
     * El control es quien llama a este método y decide qué mostrar.
     */
    public boolean confirmarPago(double montoRecibido) {
        return montoRecibido >= this.total;
    }

    /**
     * Genera el boleto y persiste el pago en la BD.
     * Solo llamar si confirmarPago() devolvió true.
     */
    public boolean generarPago() {
        // Modificar según tu DAO - crear y persistir el Pago
        Pago pago = new Pago();
        pago.setMonto(this.total);
        pago.setEmpleado(EMPLEADO_ID); // Modificar según tu sesión global
        pago.setEstado(EstadoPago.EXITOSO);

        Pago resultado = pagoDAO.insertar(pago);

        return resultado != null && resultado.getId() != null;
    }

    /**
     * Cancela el proceso de pago.
     * Solo llamar si confirmarPago() devolvió false.
     */
    public void cancelar() {
        // Modificar según tu DAO - lógica de cancelación si es necesaria
        System.out.println("Pago cancelado.");
    }

    // Getters para que el control pueda mostrar los precios en la vista
    public double getPrecioEntradas() { return precioEntradas; }
    public double getServicio() { return servicio; }
    public double getTotal() { return total; }
    public PaymentSummaryDTO getDto() { return dto; }
}
