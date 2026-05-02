/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author Jazmin
 */
public class Funcion {
    private String id;
    private Pelicula pelicula;
    private LocalDate fecha;
    private LocalTime hora;
    private Sala sala;
    private String idioma;
    private Double precio;

    public Funcion() {
    }

    public Funcion(String id, Pelicula pelicula, LocalDate fecha, LocalTime hora, Sala sala, String idioma, Double precio) {
        this.id = id;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.sala = sala;
        this.idioma = idioma;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
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
