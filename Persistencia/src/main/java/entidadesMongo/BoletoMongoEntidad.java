/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import itson.dominio.EstadoBoleto;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Entidad que representa un boleto almacenado en la colección "boleto" de MongoDB.
 * Es utilizada por el codec POJO de MongoDB para mapear automáticamente
 * los documentos BSON a objetos Java y viceversa.
 * @author Jazmin
 */
public class BoletoMongoEntidad {
     private ObjectId id;
    private ObjectId funcion;
    private List<String> numAsiento;
    private Double total;
    private EstadoBoleto estado;

    public BoletoMongoEntidad() {
    }

    public BoletoMongoEntidad(ObjectId id, ObjectId funcion, List<String> numAsiento, Double total, EstadoBoleto estado) {
        this.id = id;
        this.funcion = funcion;
        this.numAsiento = numAsiento;
        this.total = total;
        this.estado = estado;
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

    public EstadoBoleto getEstado() {
        return estado;
    }

    public void setEstado(EstadoBoleto estado) {
        this.estado = estado;
    }
    
    
}
