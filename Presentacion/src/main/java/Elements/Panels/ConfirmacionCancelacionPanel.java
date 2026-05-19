/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elements.Panels;

import DTOs.BoletoDTO;
import Elements.Buttons.GenericButton;
import Elements.Utileria.UtilGeneral;
import control.ControlCancelacion;
import control.CoordinadorPantalla;
import excepcion.NegocioException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Panel que solicita confirmación al usuario antes de cancelar un boleto.
 * Muestra los detalles del boleto seleccionado y ofrece dos opciones:
 * confirmar la cancelación o conservar el boleto.
 */
public class ConfirmacionCancelacionPanel extends JPanel implements Refreshable {

    private BoletoDTO boletoSeleccionado;
    private final ControlCancelacion controlCancelacion;
    private final CoordinadorPantalla coordinador;

    public ConfirmacionCancelacionPanel() {
        this.controlCancelacion = new ControlCancelacion();
        this.coordinador = new CoordinadorPantalla(controlCancelacion);
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());
        add(construirEncabezado(), BorderLayout.NORTH);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(new EmptyBorder(12, 20, 12, 20));
        JLabel titulo = new JLabel("Confirmar Cancelación");
        titulo.setFont(UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);
        return encabezado;
    }

    private JPanel construirContenido() {
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(UtilGeneral.FONDO_PRINCIPAL);

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(UtilGeneral.BORDE, 1),
            new EmptyBorder(30, 40, 30, 40)
        ));

        JLabel lblPregunta = new JLabel("¿Deseas cancelar este boleto?");
        lblPregunta.setFont(UtilGeneral.FUENTE_SUBTITULO);
        lblPregunta.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        lblPregunta.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAviso = new JLabel("Una vez cancelado, el acceso a la función será inválido.");
        lblAviso.setFont(UtilGeneral.FUENTE_CUERPO);
        lblAviso.setForeground(new Color(192, 57, 43));
        lblAviso.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAsientos = new JLabel("Asientos: " + 
            (boletoSeleccionado.getNumAsiento() != null 
                ? String.join(", ", boletoSeleccionado.getNumAsiento()) : "—"));
        lblAsientos.setFont(UtilGeneral.FUENTE_CUERPO);
        lblAsientos.setForeground(UtilGeneral.TEXTO_SECUNDARIO);
        lblAsientos.setAlignmentX(Component.LEFT_ALIGNMENT);

        tarjeta.add(lblPregunta);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(lblAviso);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(lblAsientos);

        contenedor.add(tarjeta);
        return contenedor;
    }

    private JPanel construirPiePagina() {
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setBackground(UtilGeneral.FONDO_ENCABEZADO);

        GenericButton btnSi = new GenericButton(
            "Sí, cancelar", false, 20, 160, 40,
            Color.WHITE, new Color(192, 57, 43), new Color(160, 40, 30)
        );
        GenericButton btnNo = new GenericButton(
            "No, conservar", false, 20, 160, 40,
            new Color(79, 140, 255), new Color(0, 0, 0, 0), new Color(79, 140, 255, 30)
        );
        btnNo.setBorder(BorderFactory.createLineBorder(new Color(79, 140, 255), 2));

        btnSi.addActionListener(e -> {
            try {
                controlCancelacion.cancelarBoleto(boletoSeleccionado.getId());
                JOptionPane.showMessageDialog(this,
                    "Boleto cancelado exitosamente.",
                    "Cancelación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
                coordinador.regresarAMisBoletos();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        btnNo.addActionListener(e -> coordinador.cerrarConfirmacion(boletoSeleccionado));

        pie.add(btnSi);
        pie.add(btnNo);
        return pie;
    }

    @Override
    public void onShow(Object object) {
        if (!(object instanceof BoletoDTO boleto)) return;
        this.boletoSeleccionado = boleto;

        removeAll();
        setLayout(new BorderLayout());
        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirContenido(), BorderLayout.CENTER);
        add(construirPiePagina(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}