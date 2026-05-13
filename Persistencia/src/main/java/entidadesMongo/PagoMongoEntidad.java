/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import itson.dominio.EstadoPago;
import org.bson.types.ObjectId;

/**
 *
 * @author Jazmin
 */
public class PagoMongoEntidad {
    private ObjectId id;
    private ObjectId boleto;
    private Double monto;
    private ObjectId empleado; // para marcar el estado del pago como exitoso o fallido
    private EstadoPago estado;

    public PagoMongoEntidad() {
    }

    public PagoMongoEntidad(ObjectId id, ObjectId boleto, Double monto, ObjectId empleado, EstadoPago estado) {
        this.id = id;
        this.boleto = boleto;
        this.monto = monto;
        this.empleado = empleado;
        this.estado = estado;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getBoleto() {
        return boleto;
    }

    public void setBoleto(ObjectId boleto) {
        this.boleto = boleto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public ObjectId getEmpleado() {
        return empleado;
    }

    public void setEmpleado(ObjectId empleado) {
        this.empleado = empleado;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
    
    
}
