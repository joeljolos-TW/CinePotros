/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elements.Panels;

import BO.BoletoBO;
import DTOs.BoletoDTO;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
import control.ControlCancelacion;
import itson.dominio.EstadoBoleto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jazmin
 */
public class MisBoletosPanel extends JPanel implements Refreshable{

    private final ControlCancelacion control;
    private final PanelMediator mediador;
    private final BoletoBO boletoBO;

    public MisBoletosPanel() {
        this.control = new ControlCancelacion();
        this.mediador = SwitchPanel.getInstance();
        this.boletoBO = new BoletoBO();

        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

       
        add(construirEncabezado(), BorderLayout.NORTH);
        cargarBoletos();
        
    }
    
    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        JButton btnAtras = new JButton("⬅");
        btnAtras.setFont(new Font("Arial",Font.BOLD,18));
        btnAtras.setBackground(UtilGeneral.BOTON_AZUL);
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.addActionListener(e-> mediador.changePanel("cartelera"));
        
        JLabel titulo = new JLabel("Mis boletos");
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(btnAtras,BorderLayout.WEST);
        encabezado.add(titulo, BorderLayout.CENTER);
        return encabezado;
    }
    private void cargarBoletos(){
        List<BoletoDTO> boletos = boletoBO.obtenerTodos();
        
        JPanel listaBoletos = new JPanel();
        listaBoletos.setLayout(new BoxLayout(listaBoletos,BoxLayout.Y_AXIS));
        listaBoletos.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        listaBoletos.setBorder(new EmptyBorder(20,20,20,20));
        
        if(boletos.isEmpty()){
            JLabel lblVacio = new JLabel("No tiene boletos registrados");
            lblVacio.setForeground(UtilGeneral.TEXTO_SECUNDARIO);
            lblVacio.setFont(UtilGeneral.FUENTE_CUERPO);
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            listaBoletos.add(lblVacio);
            listaBoletos.add(Box.createVerticalStrut(12));
        }else{
            for (BoletoDTO boleto : boletos) {
                listaBoletos.add(tarjetaBoleto(boleto));
               
            }
            JScrollPane scroll = new JScrollPane(listaBoletos);
            scroll.setBorder(null);
            scroll.getVerticalScrollBar().setUnitIncrement(16);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            if(getComponentCount()>1){
                remove(1);
            }
            add(scroll,BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
    private JPanel tarjetaBoleto(BoletoDTO boleto) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(UtilGeneral.FONDO_SECUNDARIO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(UtilGeneral.BORDE, 1), new EmptyBorder(16, 20, 16, 20)));
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setOpaque(false);

        JLabel lblPelicula = new JLabel(boleto.getPelicula());
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
                    mediador.changePanel("ConfirmacionCancelacionPanel", boleto);
                }
            });
        }

        return tarjeta;

    }

    @Override
    public void onShow(Object object) {
      cargarBoletos();
    }
}
