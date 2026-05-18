/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import java.util.List;

/**
 *
 * @author Jazmin
 */
public class Boleto {
    private String id;
    private String funcion;
    private List<String> numAsiento;
    private Double total;
    private EstadoBoleto estado;

    private byte[] qr;

    public Boleto() {
    }
    
    
    public Boleto(String id, String funcion, List<String> numAsiento, Double total, boolean usado, byte[] qr) {
        this.id = id;
        this.funcion = funcion;
        this.numAsiento = numAsiento;
        this.total = total;
        this.qr = qr;
    }

    public void setQr(byte[] qr) {
        this.qr = qr;
    }

    public byte[] getQr() {
        return qr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public List<String> getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(List<String> numAsiento) {
        this.numAsiento = numAsiento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EstadoBoleto getEstado() {
        return estado;
    }

    public void setEstado(EstadoBoleto estado) {
        this.estado = estado;
    }
    
    
    
    
    
}