/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;


/**
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

    public Pago(ObjectId id, ObjectId boleto, double monto,ObjectId empleado, EstadoPago estado) {
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

    public ObjectId getBoleto() {
        return boleto;
    }

    public void setBoleto(ObjectId boleto) {
        this.boleto = boleto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public ObjectId getEmpleado() {
        return empleado;
    }

    public void setEmpleado(ObjectId empleado) {
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
