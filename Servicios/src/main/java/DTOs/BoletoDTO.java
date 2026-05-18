/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import itson.dominio.EstadoBoleto;
import java.util.List;

/**
 * DTO para mostrar la información de un boleto en pantalla.
 * @author Jazmin
 */
public class BoletoDTO {
    private String id;
    private String idFuncion;
    private List<String> numAsiento;
    private String fecha;
    private String hora;
    private Double total;
    private EstadoBoleto estado;

    public BoletoDTO() {
    }

    public BoletoDTO(String id, String funcion, List<String> numAsiento, String fecha, String hora, Double total, String estado) {
        this.id = id;
        this.idFuncion = funcion;
        this.numAsiento = numAsiento;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        switch (estado){
            case "PENDIENTE" :
                this.estado = EstadoBoleto.PENDIENTE;
                break;
            case "ESCANEADO" :
                this.estado = EstadoBoleto.ESCANEADO;
                break;
            case "CANCELADO" :
                this.estado = EstadoBoleto.CANCELADO;
                break;
            default:
                this.estado = EstadoBoleto.PENDIENTE;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(List<String> numAsiento) {
        this.numAsiento = numAsiento;
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

    public void setIdFuncion(String idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getIdFuncion() {
        return idFuncion;
    }

    @Override
    public String toString() {
        return "BoletoDTO{"
                + "id='" + id + '\''
                + ", numAsiento=" + numAsiento
                + ", fecha=" + fecha
                + ", hora=" + hora
                + ", total=" + total
                + ", estado=" + estado
                + '}';
    }
}
