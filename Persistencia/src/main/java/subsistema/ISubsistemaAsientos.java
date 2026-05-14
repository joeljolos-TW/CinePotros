/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsistema;

import itson.dominio.Asiento;
import java.util.List;
import org.bson.types.ObjectId;

public interface ISubsistemaAsientos {
     public List<Asiento> obtenerAsientosPorFuncion(ObjectId idFuncion);
     public boolean confirmarAsientosSeleccionados(ObjectId idFuncion, List<Asiento> asientosSeleccionados);
}
