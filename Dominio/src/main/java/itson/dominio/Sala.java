/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import org.bson.types.ObjectId;

/**
 *
 * @author Jazmin
 */
public class Sala {
    private ObjectId id;
    private String nombre;
    private TipoSala tipo;
    private int capacidad;

    public Sala() {
    }

    public Sala(ObjectId id, String nombre, TipoSala tipo, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
    
}
