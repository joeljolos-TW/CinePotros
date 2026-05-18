/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elements.Panels;

import Control.ControlFactory;
import Control.IControlEntidades;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
import control.ControlCancelacion;
import excepcion.NegocioException;
import itson.dominio.EstadoBoleto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jazmin
 */
public class MisBoletosPanel extends JPanel {

    private final ControlCancelacion control;
    private final PanelMediator mediador;
    private IControlEntidades<FuncionDTO> controlerFuncion;
    private IControlEntidades<PeliculaDTO> controlerPelicula;

    public MisBoletosPanel() {
        this.control = new ControlCancelacion();
        this.mediador = SwitchPanel.getInstance();
        this.controlerPelicula = ControlFactory.getPeliculaControl();
        this.controlerFuncion = ControlFactory.getFuncionControl();

        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

       
        add(construirEncabezado(), BorderLayout.NORTH);

        
        JPanel contenedorBoletos = new JPanel();
        contenedorBoletos.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedorBoletos.setLayout(new BoxLayout(contenedorBoletos, BoxLayout.Y_AXIS));
        contenedorBoletos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
        JScrollPane scroll = new JScrollPane(contenedorBoletos);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }
    
    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JLabel titulo = new JLabel("Mis boletos");
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);
        return encabezado;
    }

    private JPanel tarjetaBoleto(BoletoDTO boleto) throws NegocioException {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(UtilGeneral.BORDE, 1), new EmptyBorder(16, 20, 16, 20)));
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setOpaque(false);

        FuncionDTO funcion = controlerFuncion.obtenerPorIdPorId(boleto.getIdFuncion());
        PeliculaDTO pelicula = controlerPelicula.obtenerPorIdPorId(funcion.getIdPelicula());
        JLabel lblPelicula = new JLabel(pelicula.getTitulo());
        lblPelicula.setFont(UtilGeneral.FUENTE_SUBTITULO);
        lblPelicula.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        JLabel lblInfo = new JLabel(boleto.getNumAsiento() + " . " + boleto.getFecha() + " . " + boleto.getHora());
        lblInfo.setFont(UtilGeneral.FUENTE_CUERPO);
        lblInfo.setForeground(UtilGeneral.TEXTO_SECUNDARIO);

        info.add(lblPelicula);
        info.add(Box.createVerticalStrut(4));
        info.add(lblInfo);

        JLabel lblEstado = new JLabel(boleto.getEstado().name());
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEstado.setOpaque(false);
        lblEstado.setBorder(new EmptyBorder(4, 10, 4, 10));

        switch (boleto.getEstado()) {
            case PENDIENTE -> {
                lblEstado.setForeground(new Color(240, 180, 63));
                lblEstado.setBackground(new Color(42, 32, 16));
            }
            case ESCANEADO -> {
                lblEstado.setForeground(new Color(79, 140, 255));
                lblEstado.setBackground(new Color(16, 27, 45));
            }
            case CANCELADO -> {
                lblEstado.setForeground(new Color(229, 115, 115));
                lblEstado.setBackground(new Color(61, 26, 26));
            }
        }
        tarjeta.add(info, BorderLayout.CENTER);
        tarjeta.add(lblEstado, BorderLayout.EAST);

        if (boleto.getEstado() == EstadoBoleto.PENDIENTE) {
            tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    mediador.changePanel("infoBoleto", boleto);
                }
            });
        }

        return tarjeta;

    }
}
