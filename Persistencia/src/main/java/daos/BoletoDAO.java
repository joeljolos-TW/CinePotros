package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entidades.Boleto;
import conexion.ConexionMongo;
import java.util.ArrayList;
import java.util.List;

public class BoletoDAO {

    private MongoCollection<Boleto> obtenerColeccion() {
        return ConexionMongo.getInstancia()
                .getBaseDatos()
                .getCollection("boletos", Boleto.class);
    }

    public boolean guardarBoleto(Boleto boleto) {
        try {
            obtenerColeccion().insertOne(boleto);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar boleto en MongoDB: " + e.getMessage());
            return false;
        }
    }

    // Usado por el Lector QR (Retorna la Entidad para que "Negocio" la convierta a DTO)
    public Boleto buscarPorFolioQR(String folioQR) {
        try {
            return obtenerColeccion().find(Filters.eq("folioQR", folioQR)).first();
        } catch (Exception e) {
            System.err.println("Error al buscar el boleto por QR: " + e.getMessage());
            return null;
        }
    }

    // Usado cuando el boleto es válido y el cliente ingresa a la sala
    public boolean marcarComoUsado(String folioQR) {
        try {
            // updateOne actualiza solo el campo 'usado' sin modificar lo demás
            obtenerColeccion().updateOne(
                Filters.eq("folioQR", folioQR),
                Updates.set("usado", true)
            );
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar el estado del boleto: " + e.getMessage());
            return false;
        }
    }

    public List<Boleto> obtenerTodosLosBoletos() {
        try {
            return obtenerColeccion().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error al obtener el historial de boletos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}