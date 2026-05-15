/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import itson.dominio.EstadoBoleto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * DTO para mostrar la información de un boleto en pantalla.
 * @author Jazmin
 */
public class BoletoDTO {
    private String id;
    private String pelicula;
    private String idSala;
    private List<String> numAsiento;
    private LocalDate fecha;
    private LocalTime hora;
    private Double total;
    private EstadoBoleto estado;

    public BoletoDTO() {
    }

    public BoletoDTO(String id, String pelicula, String sala, List<String> numAsiento, LocalDate fecha, LocalTime hora, Double total, EstadoBoleto estado) {
        this.id = id;
        this.pelicula = pelicula;
        this.idSala = sala;
        this.numAsiento = numAsiento;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getSala() {
        return idSala;
    }

    public void setSala(String sala) {
        this.idSala = sala;
    }

    public List<String> getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(List<String> numAsiento) {
        this.numAsiento = numAsiento;
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
    
     @Override
    public String toString() {
        return "BoletoDTO{"
                + "id='" + id + '\''
                + ", pelicula='" + pelicula + '\''
                + ", sala='" + idSala + '\''
                + ", numAsiento=" + numAsiento
                + ", fecha=" + fecha
                + ", hora=" + hora
                + ", total=" + total
                + ", estado=" + estado
                + '}';
    }
}
