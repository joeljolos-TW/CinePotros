/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author axelm
 */
public class SalaDTO {
    private String tipoSala;
    private String nombre;

    public SalaDTO(String tipoSala, String nombre) {
        this.tipoSala = tipoSala;
        this.nombre = nombre;
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
