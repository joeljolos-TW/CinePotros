package DTO;

import org.bson.types.ObjectId;

public class ValidacionDTO {
    private boolean verificado;
    private ObjectId idBoleto;

    public ValidacionDTO(boolean verificado, ObjectId idBoleto) {
        this.verificado = verificado;
        this.idBoleto = idBoleto;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setIdBoleto(ObjectId idBoleto) {
        this.idBoleto = idBoleto;
    }

    public ObjectId getIdBoleto() {
        return idBoleto;
    }

    public boolean isVerificado() {
        return verificado;
    }
}
