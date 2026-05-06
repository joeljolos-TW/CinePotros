/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import org.bson.types.ObjectId;


/**
 *
 * @author Jazmin
 */
public class Funcion {
    private ObjectId id;
    private ObjectId pelicula;
    private LocalDate fecha;
    private LocalTime hora;
    private ObjectId sala;
    private String idioma;
    private Double precio;

    public Funcion() {
    }

    public Funcion(ObjectId id, ObjectId pelicula, LocalDate fecha, LocalTime hora, ObjectId sala, String idioma, Double precio) {
        this.id = id;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.sala = sala;
        this.idioma = idioma;
        this.precio = precio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getPelicula() {
        return pelicula;
    }

    public void setPelicula(ObjectId pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public ObjectId getSala() {
        return sala;
    }

    public void setSala(ObjectId sala) {
        this.sala = sala;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
    
}
