/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package itson.dominio;

import java.util.Date;

/**
 * Entidad que representa una Promocion en el sistema CinePotros.
 */
public class Promocion {

    private String codigo;
    private double descuento;
    private Date fechaVencimiento;
    private TipoPromocion tipo;

    public Promocion() {
    }

    public Promocion(String codigo, double descuento, Date fechaVencimiento, TipoPromocion tipo) {
        this.codigo = codigo;
        this.descuento = descuento;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
    }

    /**
     * Valida si la promocion sigue vigente (fecha de vencimiento no ha pasado).
     * @return true si la promocion es valida, false si esta vencida.
     */
    public boolean esValida() {
        if (fechaVencimiento == null) {
            return false;
        }
        return new Date().before(fechaVencimiento);
    }

    //Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public TipoPromocion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPromocion tipo) {
        this.tipo = tipo;
    }
}

