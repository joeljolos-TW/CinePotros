package DTO;

import org.bson.types.ObjectId;

public class ValidacionDTO {
    private boolean verificado;
    private ObjectId idBoleto;
    private String razon; //Es la razon por la que no se ha validado un boleto, solo es para mostrar en pantalla

    public ValidacionDTO() {
    }

    /**
     * Constructor para crear el dto con el id siendo un String
     * @param verificado
     * @param idBoleto
     */
    public ValidacionDTO(boolean verificado, String idBoleto) {
        this.verificado = verificado;
        this.idBoleto = new ObjectId(idBoleto);
    }

    /**
     * Constructor para crear el dto con el id siendo String y con un razon de porque no se validó el boleto
     * @param razon
     * @param idBoleto
     * @param verificado
     */
    public ValidacionDTO(String razon, String idBoleto, boolean verificado) {
        this.razon = razon;
        this.idBoleto = new ObjectId(idBoleto);
        this.verificado = verificado;
    }

    /**
     * Constructor para crear el dto con el ObjectId directo
     * @param verificado
     * @param idBoleto
     */
    public ValidacionDTO(boolean verificado, ObjectId idBoleto) {
        this.verificado = verificado;
        this.idBoleto = idBoleto;
    }

    /**
     * Constructor para crear el dto ya con el ObjectId y con un razon de porque no se validó el boleto
     * @param razon
     * @param idBoleto
     * @param verificado
     */
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
