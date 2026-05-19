/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * Objeto de transferencia de datos que representa una sala de cine.
 * Contiene la información necesaria para identificar y describir
 * una sala dentro del sistema.
 *
 * @author axelm
 */
public class SalaDTO {
    private String id;
    private String tipoSala;
    private String nombre;
    private int capacidad;

    public SalaDTO() {
    }

    public SalaDTO(String id, String tipoSala, String nombre, int capacidad) {
        this.id = id;
        this.tipoSala = tipoSala;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
