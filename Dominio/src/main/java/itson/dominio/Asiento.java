/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

/**
 *
 * @author Jazmin
 */
public class Asiento {
    private String fila;
    private int numero;
    private EstadoAsiento estado;

    public Asiento() {
    }

    public Asiento(String fila, int numero, EstadoAsiento estado) {
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
    
    
}
