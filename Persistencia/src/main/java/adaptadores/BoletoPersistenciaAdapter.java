/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import entidadesMongo.BoletoMongoEntidad;
import exception.PersistenciaException;
import itson.dominio.Boleto;
import itson.dominio.Funcion;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Adapter que convierte entre la entidad de dominio Boleto
 * y la entidad de persistencia BoletoMongoEntidad.
 * @author Jazmin
 */
public class BoletoPersistenciaAdapter {
    /**
     * Convierte un Boleto del dominio a una entidad de MongoDB.
     * @param boleto boleto de dominio
     * @return lista para persisitir en MongoDB
     * @throws PersistenciaException si algun id no tiene formato valido
     */
    public BoletoMongoEntidad convertirAMongo(Boleto boleto) throws PersistenciaException{
        if(boleto == null){
            return null;
        }
        BoletoMongoEntidad entidad = new BoletoMongoEntidad();
        entidad.setId(convertirStringAObjectId(boleto.getId()));
        entidad.setFuncion
        (convertirStringAObjectIdObligatorio
        (boleto.getFuncion().getId(), "El id de la función es obligatorio para guardar el boleto"));
        entidad.setNumAsiento(boleto.getNumAsiento());
        entidad.setTotal(boleto.getTotal());
        entidad.setEstado(boleto.getEstado());
        return entidad;
    }
    /**
     * Convierte una entidad de MongoDB a un Boleto del dominio.
     * @param entidad entidad recuperada de MongoDB
     * @param funcion objeto funcion completo
     * @return boleto de dominio.
     */
    public Boleto convertirADominio(BoletoMongoEntidad entidad,Funcion funcion){
        if(entidad == null){
            return null;
        }
        Boleto boleto = new Boleto();
        boleto.setId(convertirObjectIdAString(entidad.getId()));
        boleto.setFuncion(funcion);
        boleto.setNumAsiento(entidad.getNumAsiento());
        boleto.setTotal(entidad.getTotal());
        boleto.setEstado(entidad.getEstado());
        return boleto;
        
    }
    /**
     * Convierte una lista de entidades MongoDB a una lista de Boletos del dominio.
     * @param entidades lista de entidades mongo
     * @param funciones lista de funciones en el mismo orden 
     * @return lista de boletos de dominio
     */
    public List<Boleto> convertirListaADominio(List<BoletoMongoEntidad> entidades,List<Funcion> funciones){
        List<Boleto> boletos = new ArrayList<>();
        if(entidades == null){
            return boletos;
        }
        for (int i = 0; i < entidades.size(); i++) {
            Funcion funcion;
            if(funciones != null & i<funciones.size()){
                funcion = funciones.get(i);
            }else{
                funcion = null;
            }
            boletos.add(convertirADominio(entidades.get(i), funcion));
        }
        return boletos;
    }
    /**
     * Convierte un String a ObjectId.
     * @param id id como string
     * @return ObjectId o null
     * @throws PersistenciaException si el id no tiene formato valido.
     */
    public ObjectId convertirStringAObjectId(String id)
            throws PersistenciaException {

        if (id == null || id.isBlank()) {
            return null;
        }

        if (!ObjectId.isValid(id)) {
            throw new PersistenciaException(
                    "El id recibido no tiene formato válido de ObjectId."
            );
        }

        return new ObjectId(id);
    }
    /**
     * Convierte un String a ObjectId pero exige que el id exista.
     * @param id id como string
     * @param mensajeObligatorio mensaje de error si el id esta vacio
     * @return ObjectId 
     * @throws PersistenciaException si el id esta vacio o el formato no es valido
     */
    public ObjectId convertirStringAObjectIdObligatorio(
            String id, String mensajeObligatorio) throws PersistenciaException {

        if (id == null || id.isBlank()) {
            throw new PersistenciaException(mensajeObligatorio);
        }

        if (!ObjectId.isValid(id)) {
            throw new PersistenciaException(
                    "El id recibido no tiene formato válido de ObjectId."
            );
        }

        return new ObjectId(id);
    }
    /**
     *  Convierte un ObjectId a String.
     * @param id id ObjectId
     * @return id como string o null si el id es null
     */
     public String convertirObjectIdAString(ObjectId id) {

        if (id == null) {
            return null;
        }

        return id.toHexString();
    }

}
