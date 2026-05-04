/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jazmin
 */
public class Boleto {
    private ObjectId id;
    private ObjectId funcion;
    private List<String> numAsiento;
    private Double total;

    public Boleto() {
    }
    
    
    public Boleto(ObjectId id, ObjectId funcion, List<String> numAsiento, Double total) {
        this.id = id;
        this.funcion = funcion;
        this.numAsiento = numAsiento;
        this.total = total;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getFuncion() {
        return funcion;
    }

    public void setFuncion(ObjectId funcion) {
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
    
    
    
    
    
}