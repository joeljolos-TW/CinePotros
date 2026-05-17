/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elements.Panels;

import DTO.ValidacionDTO;
import DTOs.BoletoDTO;
import Elements.Buttons.GenericButton;
import Elements.Utileria.UtilGeneral;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author
 */
public class GeneracionBoletoPanel extends JPanel {

    private SwitchPanel panelNavegacion;
    private BoletoDTO boletoDTO;

    public GeneracionBoletoPanel() {
        this.panelNavegacion = SwitchPanel.getInstance();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirContenido(), BorderLayout.CENTER);
        add(construirPiePagina(), BorderLayout.SOUTH);

    }

    public GeneracionBoletoPanel(BoletoDTO boletoDTO){
        this.boletoDTO = boletoDTO;
        this.panelNavegacion = SwitchPanel.getInstance();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirContenido(), BorderLayout.CENTER);
        add(construirPiePagina(), BorderLayout.SOUTH);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JLabel titulo = new JLabel("Información del boleto");
        titulo.setFont(UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);

        return encabezado;
    }

    private JPanel construirContenido() {
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedor.setBorder(new EmptyBorder(40, 80, 40, 80));

        JPanel tarjeta = new JPanel(new BorderLayout(40, 0));
        tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(UtilGeneral.BORDE, 1),
                new EmptyBorder(30, 30, 30, 30)
        ));
        tarjeta.add(construirDatosBoleto(), BorderLayout.CENTER);
        tarjeta.add(construirPanelQR(), BorderLayout.EAST);
        contenedor.add(tarjeta);
        return contenedor;

    }

    private JPanel construirDatosBoleto() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel subtitulo = new JLabel("Resumen de tu compra");
        subtitulo.setFont(UtilGeneral.FUENTE_SUBTITULO);
        subtitulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        subtitulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(subtitulo);

        panel.add(crearFilaDato("Película", "Avatar 3"));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Cine", "Cinépolis Bella Vista"));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Función", "03 de febrero · 6:30 PM"));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Sala", "Sala 4"));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Asientos", "F5, F6"));
        panel.add(Box.createVerticalStrut(24));

        JSeparator sep = new JSeparator();
        sep.setForeground(UtilGeneral.BORDE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(20));

        JPanel filTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filTotal.setOpaque(false);

        JLabel lblMontoTotal = new JLabel("Monto total:  ");
        lblMontoTotal.setFont(UtilGeneral.FUENTE_SUBTITULO);
        lblMontoTotal.setForeground(UtilGeneral.TEXTO_PRINCIPAL);

        JLabel lblMonto = new JLabel("$144.00");
        lblMonto.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblMonto.setForeground(UtilGeneral.BOTON_AZUL);

        filTotal.add(lblMontoTotal);
        filTotal.add(lblMonto);
        panel.add(filTotal);

        return panel;

    }

    private JPanel crearFilaDato(String clave, String valor) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        fila.setOpaque(false);

        JLabel lblClave = new JLabel(clave + ":  ");
        lblClave.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblClave.setForeground(UtilGeneral.TEXTO_PRINCIPAL);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(UtilGeneral.FUENTE_CUERPO);
        lblValor.setForeground(UtilGeneral.TEXTO_SECUNDARIO);

        fila.add(lblClave);
        fila.add(lblValor);
        return fila;
    }

    private JPanel construirPanelQR() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel qr = new JLabel("QR", SwingConstants.CENTER);
        qr.setFont(new Font("SansSerif", Font.BOLD, 28));
        qr.setForeground(new Color(162, 166, 175));
        qr.setOpaque(true);
        qr.setBackground(new Color(16, 27, 45));
        qr.setPreferredSize(new Dimension(180, 180));
        qr.setMaximumSize(new Dimension(180, 180));
        qr.setMinimumSize(new Dimension(180, 180));
        qr.setAlignmentX(Component.CENTER_ALIGNMENT);
        qr.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(UtilGeneral.BORDE, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.add(qr);
        return panel;
    }

    private JPanel construirPiePagina() {
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        GenericButton btnSubir = new GenericButton("Subir boleto", false, 20, 160, 40, Color.white, new Color(79, 140, 255), new Color(58, 122, 238));
        btnSubir.addActionListener(e -> {
            ValidacionDTO validacionDTO = new ValidacionDTO(true, boletoDTO.getId());
            panelNavegacion.changePanel("validacionBoleto", validacionDTO);
        });
        GenericButton btnCancelar = new GenericButton("Cancelar boleto", false, 20, 160, 40, Color.white, new Color(192, 57, 43), new Color(160, 40, 30));
        btnCancelar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas cancelar este boleto?\nUna vez cancelado, el acceso a la función será inválido.",
                    "Cancelar Boleto",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                        this,
                        "Boleto cancelado exitosamente.",
                        "Cancelar Boleto",
                        JOptionPane.INFORMATION_MESSAGE
                );
                panelNavegacion.changePanel("cartelera");
            }
        });
        GenericButton btnMisBoletos = new GenericButton("Ver mis boletos", false, 20, 160, 40, new Color(79, 140, 255), new Color(0, 0, 0, 0), new Color(79, 140, 255));
        GenericButton btnInicio = new GenericButton("Volver al inicio", false, 20, 160, 40, Color.white, new Color(44, 44, 62), new Color(60, 60, 80));
        btnInicio.setFocusPainted(false);
        btnInicio.addActionListener(e -> panelNavegacion.changePanel("cartelera"));
        pie.add(btnSubir);
        pie.add(btnCancelar);
        pie.add(btnMisBoletos);
        pie.add(btnInicio);
        return pie;
    }

}
