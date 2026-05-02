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

    public Pago(String id, Boleto boleto, Double monto, EstadoPago estado) {
        this.id = id;
        this.boleto = boleto;
        this.monto = monto;
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

    public Double getMonto() {
        return monto;
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
