package paquete;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema de empleados.
 * Contiene el panel de validación de boletos.
 */
public class VentanaPrincipal extends JFrame {

    private ValidarBoletoPanel panelValidacion;

    public VentanaPrincipal() {
        setTitle("CinePotros - Sistema de Empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        panelValidacion = new ValidarBoletoPanel();
        setContentPane(panelValidacion);

        setVisible(true);
    }
}
