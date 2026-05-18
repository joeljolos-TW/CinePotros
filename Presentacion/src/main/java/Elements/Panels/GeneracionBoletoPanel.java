/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elements.Panels;

import Control.ControlFactory;
import Control.IControlEntidades;
import DTO.ValidacionDTO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import Elements.Buttons.GenericButton;
import Elements.Utileria.UtilGeneral;
import Generador.ConvertidorBoletoQR;
import excepcion.NegocioException;
import itson.dominio.EstadoBoleto;

import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author
 */
public class GeneracionBoletoPanel extends JPanel implements Refreshable{

    private SwitchPanel panelNavegacion;
    private BoletoDTO boletoDTO;
    private IControlEntidades<PeliculaDTO> controlerPelicula;
    private IControlEntidades<FuncionDTO> controlerFuncion;
    private IControlEntidades<SalaDTO> controlerSala;
    private IControlEntidades<BoletoDTO> controlerBoleto;
    private JPanel contenedorCentral;
    private ConvertidorBoletoQR generadorQR;

    public GeneracionBoletoPanel() {
        this.panelNavegacion = SwitchPanel.getInstance();
        this.controlerPelicula = ControlFactory.getPeliculaControl();
        this.controlerFuncion = ControlFactory.getFuncionControl();
        this.controlerSala = ControlFactory.getSalaControl();
        this.controlerBoleto = ControlFactory.getBoletoControl();
        this.generadorQR = new ConvertidorBoletoQR();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirPiePagina(), BorderLayout.SOUTH);

    }

    public GeneracionBoletoPanel(BoletoDTO boletoDTO){
        this.boletoDTO = boletoDTO;
        this.controlerPelicula = ControlFactory.getPeliculaControl();
        this.controlerFuncion = ControlFactory.getFuncionControl();
        this.controlerSala = ControlFactory.getSalaControl();
        this.controlerBoleto = ControlFactory.getBoletoControl();
        this.panelNavegacion = SwitchPanel.getInstance();
        this.generadorQR = new ConvertidorBoletoQR();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirPiePagina(), BorderLayout.SOUTH);

        contenedorCentral = new JPanel(new GridBagLayout());
        contenedorCentral.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        add(contenedorCentral, BorderLayout.CENTER);
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

    private JPanel construirContenido() throws NegocioException{
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedor.setBorder(new EmptyBorder(40, 80, 40, 80));
        JPanel tarjeta = new JPanel(new GridBagLayout());
        tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(UtilGeneral.BORDE, 1),
                new EmptyBorder(30, 30, 30, 30)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 0, 0, 40);
        tarjeta.add(construirDatosBoleto(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        tarjeta.add(construirPanelQR(), gbc);
        contenedor.add(tarjeta);
        return contenedor;
    }

    private JPanel construirDatosBoleto() throws NegocioException {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel subtitulo = new JLabel("Resumen de tu compra");
        subtitulo.setFont(UtilGeneral.FUENTE_SUBTITULO);
        subtitulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        subtitulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(subtitulo);

        FuncionDTO funcion = controlerFuncion.obtenerPorId(boletoDTO.getIdFuncion());
        PeliculaDTO pelicula = controlerPelicula.obtenerPorId(funcion.getIdPelicula());
        SalaDTO sala = controlerSala.obtenerPorId(funcion.getSalaFuncion());

        panel.add(crearFilaDato("Pelicula", pelicula.getTitulo()));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Cine", "Cinépolis Bella Vista"));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Función", funcion.getFecha() + " " + funcion.getHora()));
        panel.add(Box.createVerticalStrut(14));
        panel.add(crearFilaDato("Sala", sala.getNombre()));
        panel.add(Box.createVerticalStrut(14));
        List<String> asientos = boletoDTO.getNumAsiento();
        String asientosComprados = "";
        int contador = 0;
        for(String a : asientos){
            asientosComprados += a;
            contador++;
            if(contador != asientos.size()){
                asientosComprados += ", ";
            }
        }
        panel.add(crearFilaDato("Asientos", asientosComprados));
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

        String totalTxt = boletoDTO.getTotal() != null ? String.format("$%.2f", boletoDTO.getTotal()) : "$0.00";
        JLabel lblMonto = new JLabel(totalTxt);
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
    private void actualizarContenidoDinamico() {
        contenedorCentral.removeAll();
        contenedorCentral.setBorder(new EmptyBorder(40, 80, 40, 80));

        if (boletoDTO == null) {
            contenedorCentral.add(new JLabel("No hay información de boleto cargada."));
            return;
        }

        try {
            JPanel tarjeta = new JPanel(new BorderLayout(40, 0));
            tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
            tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(UtilGeneral.BORDE, 1),
                    new EmptyBorder(30, 30, 30, 30)
            ));

            tarjeta.add(construirDatosBoleto(), BorderLayout.CENTER);
            tarjeta.add(construirPanelQR(), BorderLayout.EAST);
            contenedorCentral.add(tarjeta);

        } catch (NegocioException e) {
            System.err.println("Error al obtener datos de los controladores: " + e.getMessage());
            contenedorCentral.add(new JLabel("Error al cargar los datos del boleto."));
        }
    }

    private JPanel construirPanelQR() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(180, 180));
        panel.setMaximumSize(new Dimension(180, 180));
        panel.setMinimumSize(new Dimension(180, 180));
        panel.setOpaque(false);

        JLabel qr = new JLabel("", SwingConstants.CENTER);
        qr.setOpaque(true);
        qr.setBackground(new Color(16, 27, 45));
        qr.setPreferredSize(new Dimension(176, 176));
        qr.setMaximumSize(new Dimension(176, 176));
        qr.setMinimumSize(new Dimension(176, 176));
        qr.setBorder(new LineBorder(UtilGeneral.BORDE, 2));

        String rutaQR = generadorQR.generarQRAsRutaTemporal(boletoDTO);

        if (rutaQR != null && new File(rutaQR).exists()) {
            ImageIcon iconQR = new ImageIcon(rutaQR);
            Image imgEscalada = iconQR.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            qr.setIcon(new ImageIcon(imgEscalada));
        } else {
            qr.setText("Error QR");
            qr.setFont(new Font("SansSerif", Font.BOLD, 14));
            qr.setForeground(Color.RED);
        }

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
                boletoDTO.setEstado(EstadoBoleto.CANCELADO);
                try {
                    controlerBoleto.actualizar(boletoDTO);
                }catch (NegocioException ex){
                    ex.printStackTrace();
                }
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

    @Override
    public void onShow(Object object) {
        if (object instanceof BoletoDTO bDTO) {
            this.boletoDTO = bDTO; //
        }

        actualizarContenidoDinamico();

        revalidate();
        repaint();
    }
}
