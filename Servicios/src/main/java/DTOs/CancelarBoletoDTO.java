/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO para cancelar un boleto.
 * Solo contiene el id del boleto que se desea cancelar
 * @author Jazmin
 */
public class CancelarBoletoDTO {
    private String id;

    public CancelarBoletoDTO() {
    }

    public CancelarBoletoDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
