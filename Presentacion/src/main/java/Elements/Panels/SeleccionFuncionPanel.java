package Elements.Panels;

import DTOs.SeleccionPeliculaDTO;
import Mediator.PanelMediator;

import java.awt.*;
import javax.security.auth.RefreshFailedException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SeleccionFuncionPanel extends JPanel implements Refreshable {
    
    private PanelMediator panelMediator;
    private final Color AZUL_OSCURO = new Color(10, 25, 49);
    private final Color AZUL_CLARO = new Color(50, 130, 240);
    private final Color TEXTO_BLANCO = Color.WHITE;
    private SeleccionPeliculaDTO selectedMovie;

    public SeleccionFuncionPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        setBackground(AZUL_OSCURO);
        setLayout(new BorderLayout());
        // ← solo el panel superior, no toca selectedMovie
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

    private JPanel construirContenidoCentrado() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(AZUL_OSCURO);
        contenedor.setBorder(new EmptyBorder(0, 20, 40, 20));

        JPanel panelPeli = new JPanel();
        panelPeli.setLayout(new BoxLayout(panelPeli, BoxLayout.Y_AXIS));
        panelPeli.setOpaque(false);
        panelPeli.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = selectedMovie.getCover();
        Image image = icon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel(new ImageIcon(image));
        poster.setPreferredSize(new Dimension(200, 300));
        poster.setMaximumSize(new Dimension(200, 300));
        poster.setOpaque(true);
        poster.setBackground(new Color(30, 45, 70));
        poster.setForeground(TEXTO_BLANCO);
        poster.setHorizontalAlignment(SwingConstants.CENTER);
        poster.setAlignmentX(Component.CENTER_ALIGNMENT);
        poster.setBorder(new LineBorder(AZUL_CLARO, 2));

        JLabel titulo = new JLabel(selectedMovie.getName());
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(TEXTO_BLANCO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(15, 0, 5, 0));

        JLabel info = new JLabel("192 min | Ciencia Ficción | B-15");
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setForeground(new Color(180, 200, 230));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPeli.add(poster);
        panelPeli.add(titulo);
        panelPeli.add(info);

        JPanel panelDias = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelDias.setOpaque(false);
        panelDias.setMaximumSize(new Dimension(800, 100));

        String[] dias = {"Hoy", "Mañana", "Jueves 17", "Viernes 18"};
        for (String dia : dias) {
            JButton btnDia = new JButton(dia);
            btnDia.setFont(new Font("Arial", Font.BOLD, 13));
            btnDia.setBackground(AZUL_OSCURO);
            btnDia.setForeground(TEXTO_BLANCO);
            btnDia.setBorder(new LineBorder(AZUL_CLARO, 1));
            btnDia.setFocusPainted(false);
            btnDia.setPreferredSize(new Dimension(110, 35));
            panelDias.add(btnDia);
        }

        JPanel panelFunciones = new JPanel();
        panelFunciones.setLayout(new BoxLayout(panelFunciones, BoxLayout.Y_AXIS));
        panelFunciones.setOpaque(false);
        panelFunciones.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelFunciones.add(crearFilaCentrada("SALA 1 - TRADICIONAL", "14:00", "17:30", "20:00"));
        panelFunciones.add(Box.createVerticalStrut(20));
        panelFunciones.add(crearFilaCentrada("SALA VIP", "16:30", "19:00", "22:15"));

        contenedor.add(panelPeli);
        contenedor.add(Box.createVerticalStrut(20));
        contenedor.add(panelDias);
        contenedor.add(Box.createVerticalStrut(10));
        contenedor.add(panelFunciones);

        return contenedor;
    }

    private JPanel crearFilaCentrada(String sala, String... horarios) {
        JPanel fila = new JPanel();
        fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
        fila.setOpaque(false);
        fila.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSala = new JLabel(sala);
        lblSala.setFont(new Font("Arial", Font.BOLD, 15));
        lblSala.setForeground(AZUL_CLARO);
        lblSala.setAlignmentX(Component.CENTER_ALIGNMENT);
        fila.add(lblSala);

        JPanel panelHoras = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelHoras.setOpaque(false);

        for (String h : horarios) {
            JButton btnH = new JButton(h);
            btnH.setBackground(AZUL_CLARO);
            btnH.setForeground(TEXTO_BLANCO);
            btnH.setFont(new Font("Arial", Font.BOLD, 14));
            btnH.setFocusPainted(false);
            btnH.setPreferredSize(new Dimension(90, 40));
            btnH.addActionListener(e -> panelMediator.changePanel("seleccionAsientos"));
            panelHoras.add(btnH);
        }

        fila.add(panelHoras);
        return fila;
    }

    @Override
    public void onShow(Object object) {
        if (!(object instanceof SeleccionPeliculaDTO seleccionPeliculaDTO)) return;

        selectedMovie = seleccionPeliculaDTO; // ← asigna el DTO

        // Reconstruye el contenido con los datos nuevos
        removeAll();
        add(construirPanelSuperior(), BorderLayout.NORTH);
        add(new JScrollPane(construirContenidoCentrado()), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}