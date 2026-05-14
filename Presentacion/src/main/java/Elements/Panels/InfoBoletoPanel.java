package Elements.Panels;

import DTOs.BoletoDTO;
import Mediator.PanelMediator;
import itson.dominio.EstadoBoleto;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
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
        JPanel panelAtras = new JPanel(new BorderLayout());
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

        JLabel lblTitulo = new JLabel("INFORMACIÓN DEL BOLETO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(TEXTO_BLANCO);

        panelAtras.add(btnAtras, BorderLayout.WEST);
        panelAtras.add(lblTitulo, BorderLayout.CENTER);
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

        URL url = getClass().getResource("/images/marioPoster.jpg");
        ImageIcon qr = new ImageIcon(url);
        //ImageIcon qr = boletoSeleccionado.getQr();   CUANDO FUNCIONE ESTO VA AQUI
        Image image = qr.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel QR = new JLabel(new ImageIcon(image));
        QR.setPreferredSize(new Dimension(300, 300));
        QR.setMinimumSize(new Dimension(300, 300));
        QR.setMaximumSize(new Dimension(300, 300));
        QR.setOpaque(true);
        QR.setBackground(new Color(30, 45, 70));
        QR.setForeground(TEXTO_BLANCO);
        QR.setHorizontalAlignment(SwingConstants.LEFT);
        QR.setAlignmentX(Component.CENTER_ALIGNMENT);
        QR.setBorder(new LineBorder(AZUL_CLARO, 2));

//        FuncionDTO funcion = boletoSeleccionado.getFuncionDelBoleto();
//        SalaDTO sala = funcion.getSalaFuncion();

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInfo.add(crearLabelInfo("Función:"));
        panelInfo.add(crearLabelInfo("Sala: A1"));
        panelInfo.add(crearLabelInfo("Asiento: J10, J11"));
        //panelInfo.add(crearLabelInfo("Sala: " + sala.getNombre()));
        //panelInfo.add(crearLabelInfo("Asiento: " + String.join(", ", boletoSeleccionado.getNumAsientos())));
        panelInfo.add(Box.createVerticalStrut(80));
        //JLabel lblEstado = crearLabelInfo(boletoSeleccionado.isUsado() ? "Boleto usado" : "Boleto disponible");
        JLabel lblEstado = crearLabelInfo("Boleto disponible");
        lblEstado.setForeground(boletoSeleccionado.getEstado() == EstadoBoleto.CANCELADO ? Color.RED : Color.GREEN);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInfo.add(lblEstado);

        panelQR.add(QR);

        panelQR.setAlignmentY(Component.TOP_ALIGNMENT);
        panelInfo.setAlignmentY(Component.TOP_ALIGNMENT);

        contenedor.add(panelQR);
        contenedor.add(Box.createRigidArea(new Dimension(40, 0)));
        contenedor.add(panelInfo);

        return contenedor;
    }

    private JLabel crearLabelInfo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    @Override
    public void onShow(Object object) {
        if (!(object instanceof BoletoDTO boletoDTO)) return;
        boletoSeleccionado = boletoDTO;

        removeAll();
        setLayout(new BorderLayout());
        add(construirPanelSuperior(), BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(AZUL_OSCURO);

        JPanel contenido = construirContenidoCentrado();
        wrapper.add(contenido);

        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scroll, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
