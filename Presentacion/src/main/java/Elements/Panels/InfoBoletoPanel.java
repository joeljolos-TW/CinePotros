package Elements.Panels;

import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.SalaDTO;
import Mediator.PanelMediator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author axelm
 */
public class InfoBoletoPanel extends JPanel implements Refreshable{
    private BoletoDTO boletoSeleccionado;
    private PanelMediator panelMediator;
    private final Color AZUL_OSCURO = new Color(10, 25, 49);
    private final Color AZUL_CLARO = new Color(50, 130, 240);
    private final Color TEXTO_BLANCO = Color.WHITE;
    private final Color TEXTO_USADO = Color.RED;
    private final Color TEXTO_DISPONIBLE = Color.GREEN;

    public InfoBoletoPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        setBackground(AZUL_OSCURO);
        setLayout(new BorderLayout());
        add(construirPanelSuperior(), BorderLayout.NORTH);
    }
    
    private JPanel construirPanelSuperior() {
        JPanel panelAtras = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAtras.setOpaque(false);
        panelAtras.setBorder(new EmptyBorder(10, 10, 0, 0));

        JButton btnAtras = new JButton("←");
        btnAtras.setFont(new Font("Arial", Font.BOLD, 20));
        btnAtras.setBackground(AZUL_CLARO);
        btnAtras.setForeground(TEXTO_BLANCO);
        btnAtras.setFocusPainted(false);
        btnAtras.setPreferredSize(new Dimension(50, 40));
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addActionListener(e -> panelMediator.changePanel("cartelera"));

        panelAtras.add(btnAtras);
        return panelAtras;
    }
    
    private JPanel construirContenidoCentrado(){
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));
        contenedor.setBackground(AZUL_OSCURO);
        contenedor.setBorder(new EmptyBorder(0, 20, 40, 20));
        
        JPanel panelQR = new JPanel();
        panelQR.setLayout(new BoxLayout(panelQR, BoxLayout.Y_AXIS));
        panelQR.setOpaque(false);
        panelQR.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ImageIcon qr = boletoSeleccionado.getQr();
        Image image = qr.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel QR = new JLabel(new ImageIcon(image));
        QR.setPreferredSize(new Dimension(300, 300));
        QR.setMaximumSize(new Dimension(300, 300));
        QR.setOpaque(true);
        QR.setBackground(new Color(30, 45, 70));
        QR.setForeground(TEXTO_BLANCO);
        QR.setHorizontalAlignment(SwingConstants.LEFT);
        QR.setAlignmentX(Component.CENTER_ALIGNMENT);
        QR.setBorder(new LineBorder(AZUL_CLARO, 2));
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel info = new JLabel();
        info.setFont(new Font("Arial", Font.BOLD, 26));
        info.setForeground(TEXTO_BLANCO);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setBorder(new EmptyBorder(15, 0, 5, 0));
        FuncionDTO funcion = boletoSeleccionado.getFuncionDelBoleto();
        SalaDTO sala = funcion.getSalaFuncion();
        StringBuilder sb = new StringBuilder();
        sb.append("Funcion:\n");
        sb.append(sala.getNombre() + "\n");
        sb.append(sala.getTipoSala() + "\n");
        List<String> numAsientos = boletoSeleccionado.getNumAsientos();
        numAsientos.forEach(na -> sb.append("Asiento " + na + " "));
        sb.append(funcion.getFecha().toString() + "\n");
        sb.append(funcion.getHora().toString() + "\n");
        info.setText(sb.toString() + "hola");
        JLabel usado = new JLabel();
        usado.setFont(new Font("Arial", Font.BOLD, 26));
        usado.setAlignmentX(Component.CENTER_ALIGNMENT);
        if(boletoSeleccionado.isUsado()){
            usado.setForeground(TEXTO_USADO);
            usado.setText("Boleto usado");
        }else{
            usado.setForeground(TEXTO_DISPONIBLE);
            usado.setText("Boleto disponible");
        }
        
        panelInfo.add(info);
        panelInfo.add(usado);
        
        panelQR.add(QR);
        
        contenedor.add(panelQR);
        contenedor.add(panelInfo);
        
        return contenedor;
    }
    
    @Override
    public void onShow(Object object) {
        if (!(object instanceof BoletoDTO boletoDTO)) return;

        boletoSeleccionado = boletoDTO;

        removeAll();
        add(construirPanelSuperior(), BorderLayout.NORTH);
        add(new JScrollPane(construirContenidoCentrado()), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
