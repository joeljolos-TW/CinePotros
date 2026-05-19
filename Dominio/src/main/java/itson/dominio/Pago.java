/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;


/**
 * Entidad que representa un pago realizado por la compra de un boleto de cine.
 * Asocia un boleto con su monto, el empleado que procesó la transacción
 * y el estado resultante del pago.
 *
 * @author Jazmin
 */
public class Pago {
    private String id;
    private Boleto boleto;
    private Double monto;
    private Empleado empleado; // para marcar el estado del pago como exitoso o fallido
    private EstadoPago estado;

    public Pago() {
    }

    public Pago(String id, Boleto boleto, double monto,Empleado empleado, EstadoPago estado) {
        this.id = id;
        this.boleto = boleto;
        this.monto = monto;
        this.empleado = empleado;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
    
    
}
