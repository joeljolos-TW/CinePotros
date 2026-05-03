package Elements.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.*;
import Elements.Buttons.GenericButton;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
/**
 *
 * @author Ricardo
 */
public class SeleccionAsientosPanel extends JPanel {

    private PanelMediator panelMediator;

    public SeleccionAsientosPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirMatrizAsientos(), BorderLayout.CENTER);
        add(construirPiePagina(), BorderLayout.SOUTH);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JLabel titulo = new JLabel("Selección de Asientos");
        titulo.setFont(UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);

        return encabezado;
    }

    private JPanel construirMatrizAsientos() {
        JPanel contenedorCentral = new JPanel(new BorderLayout());
        contenedorCentral.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Representación visual de la pantalla del cine
        JLabel pantalla = new JLabel("P A N T A L L A", SwingConstants.CENTER);
        pantalla.setOpaque(true);
        pantalla.setBackground(Color.DARK_GRAY);
        pantalla.setForeground(Color.WHITE);
        pantalla.setFont(new Font("Arial", Font.BOLD, 14));
        pantalla.setPreferredSize(new Dimension(800, 40));
        pantalla.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contenedorCentral.add(pantalla, BorderLayout.NORTH);

        // Cuadrícula de asientos
        JPanel matriz = new JPanel(new GridLayout(5, 8, 15, 15));
        matriz.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        matriz.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        Color colorAzulFigma = new Color(41, 128, 185); 
        Color colorOcupado = new Color(149, 165, 166); // Gris para asientos no disponibles

        for (int i = 0; i < 40; i++) {
            JButton asiento = new JButton();
            
            // Aquí ponemos la imagen del asiento :o 
            /*
            URL iconURL = getClass().getResource("/images/tu_icono_asiento.png");
            if (iconURL != null) {
                asiento.setIcon(new ImageIcon(iconURL));
                asiento.setText(""); // Quitar texto si solo quieres el icono
            }
            */

            asiento.setText("💺 " + (i + 1));
            asiento.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
            asiento.setForeground(Color.WHITE);
            asiento.setFocusPainted(false);
            asiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // Simulamos asientos ocupados para ver el contraste
            if (i == 12 || i == 13 || i == 25) {
                asiento.setBackground(colorOcupado);
                asiento.setEnabled(false);
            } else {
                asiento.setBackground(colorAzulFigma);
            }
            
            matriz.add(asiento);
        }

        contenedorCentral.add(matriz, BorderLayout.CENTER);
        return contenedorCentral;
    }

    private JPanel construirPiePagina() {
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        
        //JButton btnVolver = new JButton("Volver a Horarios");
        //btnVolver.addActionListener(e -> panelNavegacion.changePanel("seleccionFuncion"));
        GenericButton backBtn = new GenericButton(
                "Volver a Horarios",
                true,
                10,
                160,
                110,
                UtilGeneral.TEXTO_PRINCIPAL,
                UtilGeneral.BOTON_AZUL,
                UtilGeneral.FONDO_SECUNDARIO
        );
        backBtn.addActionListener(e -> panelMediator.changePanel("seleccionFuncion"));

        //JButton btnConfirmar = new JButton("Confirmar Compra");
        //btnConfirmar.setBackground(new Color(46, 204, 113)); // Verde para el botón principal
        //btnConfirmar.setForeground(Color.WHITE);
        //btnConfirmar.setFocusPainted(false);
        //btnConfirmar.addActionListener(e -> JOptionPane.showMessageDialog(this, "¡Asientos confirmados!"));
        GenericButton confirmBtn = new GenericButton(
                "Confirmar Compra",
                true,
                10,
                160,
                110,
                UtilGeneral.TEXTO_PRINCIPAL,
                UtilGeneral.BOTON_AZUL,
                UtilGeneral.FONDO_SECUNDARIO
        );
        confirmBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Asientos confirmados!");

        });

        pie.add(backBtn);
        pie.add(confirmBtn);
        
        return pie;
    }
}