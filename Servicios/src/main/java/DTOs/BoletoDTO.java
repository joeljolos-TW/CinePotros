/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * 
 */
public class BoletoDTO {
    private boolean usado;
    private ImageIcon qr;
    private FuncionDTO funcionDelBoleto;
    private List<String> numAsientos;

    public BoletoDTO(boolean usado, ImageIcon qr, FuncionDTO funcionDelBoleto, List<String> numAsientos) {
        this.usado = usado;
        this.qr = qr;
        this.funcionDelBoleto = funcionDelBoleto;
        this.numAsientos = numAsientos;
    }

    public List<String> getNumAsientos() {
        return numAsientos;
    }

    public void setNumAsientos(List<String> numAsientos) {
        this.numAsientos = numAsientos;
    }

    public FuncionDTO getFuncionDelBoleto() {
        return funcionDelBoleto;
    }

    public void setFuncionDelBoleto(FuncionDTO funcionDelBoleto) {
        this.funcionDelBoleto = funcionDelBoleto;
    }

    public ImageIcon getQr() {
        return qr;
    }

    public void setQr(ImageIcon qr) {
        this.qr = qr;
    }
    
    public boolean isUsado(){
        return usado;
    }
    
    public void setUsado(boolean usado){
        this.usado = usado;
    }
}
