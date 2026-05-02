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
    private Funcion funcion;
    private List<String> numAsiento;
    private String codigoQR;
    private Double total;

    public Boleto() {
    }
    
    
    public Boleto(String id, Funcion funcion, List<String> numAsiento, String codigoQR, Double total) {
        this.id = id;
        this.funcion = funcion;
        this.numAsiento = numAsiento;
        this.codigoQR = codigoQR;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public List<String> getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(List<String> numAsiento) {
        this.numAsiento = numAsiento;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
    
    
}