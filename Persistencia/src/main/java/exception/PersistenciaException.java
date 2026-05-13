/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import DAO.AsientoDAO;
import itson.dominio.Asiento;
import itson.dominio.EstadoAsiento;
import org.bson.types.ObjectId;
import java.util.List;

/**
 * Implementation of the Seats Subsystem.
 */
public class SubsistemaAsientos implements ISubsistemaAsientos {
    
    private final AsientoDAO asientoDAO;

    public SubsistemaAsientos() {
        this.asientoDAO = new AsientoDAO();
    }

    @Override
    public List<Asiento> obtenerAsientosPorFuncion(ObjectId idFuncion) {
        return asientoDAO.obtenerPorFuncion(idFuncion);
    }

    @Override
    public boolean confirmarAsientosSeleccionados(ObjectId idFuncion, List<Asiento> asientosSeleccionados) {
        boolean allSuccess = true;
        for (Asiento asiento : asientosSeleccionados) {
            boolean success = asientoDAO.actualizarEstado(idFuncion, asiento.getFila(), asiento.getNumero(), EstadoAsiento.OCUPADO);
            if (!success) {
                allSuccess = false;
            }
        }
        return allSuccess;
    }
}
