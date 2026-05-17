/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * 
 */
public class FuncionDTO {
    private String id;
    private LocalDate fecha;
    private LocalTime hora;
    private SalaDTO salaFuncion;
    private Double precio;

    public FuncionDTO() {
    }

    public FuncionDTO(String id, LocalDate fecha, LocalTime hora, SalaDTO salaFuncion, Double precio) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.salaFuncion = salaFuncion;
        this.precio = precio;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setSalaFuncion(SalaDTO salaFuncion) {
        this.salaFuncion = salaFuncion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public SalaDTO getSalaFuncion() {
        return salaFuncion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
