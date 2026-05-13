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
    private LocalDate fecha;
    private LocalTime hora;
    private SalaDTO salaFuncion;

    public FuncionDTO(LocalDate fecha, LocalTime hora, SalaDTO salaFuncion) {
        this.fecha = fecha;
        this.hora = hora;
        this.salaFuncion = salaFuncion;
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
}
