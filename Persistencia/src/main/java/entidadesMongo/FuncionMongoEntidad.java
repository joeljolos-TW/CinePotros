/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import java.time.LocalDate;
import java.time.LocalTime;
import org.bson.types.ObjectId;

/**
 *
 * @author Jazmin
 */
public class FuncionMongoEntidad {

    private ObjectId id;
    private ObjectId pelicula;
    private String fecha;
    private String hora;
    private ObjectId sala;
    private String idioma;
    private Double precio;

    public FuncionMongoEntidad() {
    }

    public FuncionMongoEntidad(ObjectId id, ObjectId pelicula, String fecha, String hora, ObjectId sala, String idioma, Double precio) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
