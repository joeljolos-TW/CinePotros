/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import itson.dominio.Sala;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * 
 */
public class FuncionDTO {
    private String id;
    private String fecha;
    private String hora;
    private String idSala;
    private String idPelicula;
    private Double precio;
    private String idioma;

    public FuncionDTO() {
    }
    
    public FuncionDTO(String id,String fecha, String hora, String sala) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idSala = sala;
    }

    public FuncionDTO(String id, Double precio, String idSala, String idPelicula, String hora, String fecha, String idioma) {
        this.id = id;
        this.precio = precio;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
        this.hora = hora;
        this.fecha = fecha;
        this.idioma = idioma;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setSalaFuncion(String sala) {
        this.idSala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getSalaFuncion() {
        return idSala;
    }

    public void setIdPelicula(String idPelicula) {this.idPelicula = idPelicula;}

    public void setPrecio(Double precio) {this.precio = precio;}

    public Double getPrecio() {
        return precio;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }
    
}
