/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package excepcion;

import itson.dominio.Asiento;
import org.bson.types.ObjectId;
import java.util.List;

/**
 *
 */
public class NegocioException extends Exception{

    public NegocioException(String message) {
        super(message);
    }
// * Interface for the Seats Subsystem.
// */
//public interface ISubsistemaAsientos {
//    
//    /**
//     * Retrieves all seats for a specific showtime (función).
//     */
//    List<Asiento> obtenerAsientosPorFuncion(ObjectId idFuncion);
//
//    /**
//     * Updates the status of the selected seats to OCUPADO.
//     */
//    boolean confirmarAsientosSeleccionados(ObjectId idFuncion, List<Asiento> asientosSeleccionados);

}
