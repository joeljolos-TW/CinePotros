/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import org.bson.types.ObjectId;

/**
 * Entidad que representa un empleado almacenado en la colección correspondiente
 * de MongoDB. Es utilizada por el codec POJO de MongoDB para mapear automáticamente
 * los documentos BSON a objetos Java y viceversa.
 *
 * @author Jazmin
 */
public class EmpleadoMongoEntidad {
    private ObjectId id;
    private String nombreUsuario;
    private String contrasenia;

    public EmpleadoMongoEntidad() {
    }

    public EmpleadoMongoEntidad(ObjectId id, String nombreUsuario, String contrasenia) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
}
