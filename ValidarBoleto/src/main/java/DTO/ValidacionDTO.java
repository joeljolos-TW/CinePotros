package DTO;

import org.bson.types.ObjectId;

public class ValidacionDTO {
    private boolean verificado;
    private ObjectId idBoleto;
    private String razon; //Es la razon por la que no se ha validado un boleto, solo es para mostrar en pantalla

    public ValidacionDTO(boolean verificado, ObjectId idBoleto) {
        this.verificado = verificado;
        this.idBoleto = idBoleto;
    }

    public ValidacionDTO(String razon, ObjectId idBoleto, boolean verificado) {
        this.razon = razon;
        this.idBoleto = idBoleto;
        this.verificado = verificado;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setIdBoleto(ObjectId idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getRazon() {
        return razon;
    }

    public ObjectId getIdBoleto() {
        return idBoleto;
    }

    public boolean isVerificado() {
        return verificado;
    }
}
