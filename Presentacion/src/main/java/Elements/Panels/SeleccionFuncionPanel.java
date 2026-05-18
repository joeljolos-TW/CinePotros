package Elements.Panels;

import BO.FuncionBO;
import Control.ControlFactory;
import Control.IControlEntidades;
import DTOs.FuncionDTO;
import DTOs.SalaDTO;
import DTOs.SeleccionPeliculaDTO;
import Mediator.PanelMediator;
import excepcion.NegocioException;
import java.awt.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class SeleccionFuncionPanel extends JPanel implements Refreshable {
    
    private PanelMediator panelMediator;
    private final Color AZUL_OSCURO = new Color(10, 25, 49);
    private final Color AZUL_CLARO = new Color(50, 130, 240);
    private final Color TEXTO_BLANCO = Color.WHITE;
    private SeleccionPeliculaDTO selectedMovie;
    private List<FuncionDTO> funciones;
    private String fechaSeleccionada;
    private final FuncionBO funcionBO;
    private IControlEntidades<SalaDTO> controler;
 
    public SeleccionFuncionPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        this.funcionBO = new FuncionBO();
        this.funciones = new ArrayList<>();
        this.controler = ControlFactory.getSalaControl();
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
 
    private JPanel construirContenidoCentrado() throws NegocioException{
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(AZUL_OSCURO);
        contenedor.setBorder(new EmptyBorder(0, 20, 40, 20));
 
      
        JPanel panelPeli = new JPanel();
        panelPeli.setLayout(new BoxLayout(panelPeli, BoxLayout.Y_AXIS));
        panelPeli.setOpaque(false);
        panelPeli.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        URL url = getClass().getResource(selectedMovie.getImagen());
        ImageIcon icon = new ImageIcon(url);
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
 
        panelPeli.add(poster);
        panelPeli.add(titulo);
 
        // ── FECHAS ÚNICAS ─────────────────────────────────────────────────────
        JPanel panelDias = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelDias.setOpaque(false);
        panelDias.setMaximumSize(new Dimension(800, 100));
 
        Set<String> fechasUnicas = new LinkedHashSet<>();
        for (FuncionDTO f : funciones) {
            fechasUnicas.add(f.getFecha());
        }
 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
        for (String fecha : fechasUnicas) {
            JButton btnDia = new JButton(fecha);
            btnDia.setFont(new Font("Arial", Font.BOLD, 13));
            btnDia.setFocusPainted(false);
            btnDia.setPreferredSize(new Dimension(110, 35));
            btnDia.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
            if (fecha.equals(fechaSeleccionada)) {
                btnDia.setBackground(AZUL_CLARO);
                btnDia.setForeground(TEXTO_BLANCO);
                btnDia.setBorder(null);
            } else {
                btnDia.setBackground(AZUL_OSCURO);
                btnDia.setForeground(TEXTO_BLANCO);
                btnDia.setBorder(new LineBorder(AZUL_CLARO, 1));
            }
 
            btnDia.addActionListener(e -> {
                fechaSeleccionada = fecha;
                recargarContenido();
            });
 
            panelDias.add(btnDia);
        }
 
      
        JPanel panelFunciones = new JPanel();
        panelFunciones.setLayout(new BoxLayout(panelFunciones, BoxLayout.Y_AXIS));
        panelFunciones.setOpaque(false);
        panelFunciones.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        // Agrupar funciones por sala para la fecha seleccionada
        Set<String> salasVistas = new LinkedHashSet<>();
        for (FuncionDTO f : funciones) {
            if (f.getFecha().equals(fechaSeleccionada)) {
                SalaDTO sala = controler.obtenerPorIdPorId(f.getSalaFuncion());
                salasVistas.add(sala.getNombre());
            }
        }
 
        for (String nombreSala : salasVistas) {
            List<FuncionDTO> funcionesDeSala = new ArrayList<>();
            for (FuncionDTO f : funciones) {
                SalaDTO sala = controler.obtenerPorIdPorId(f.getSalaFuncion());
                if (f.getFecha().equals(fechaSeleccionada)
                        && sala.getNombre().equals(nombreSala)) {
                    funcionesDeSala.add(f);
                }
            }
            panelFunciones.add(crearFilaSala(nombreSala, funcionesDeSala));
            panelFunciones.add(Box.createVerticalStrut(20));
        }
 
        contenedor.add(panelPeli);
        contenedor.add(Box.createVerticalStrut(20));
        contenedor.add(panelDias);
        contenedor.add(Box.createVerticalStrut(10));
        contenedor.add(panelFunciones);
 
        return contenedor;
    }
 
    private JPanel crearFilaSala(String nombreSala, List<FuncionDTO> funcionesSala) {
        JPanel fila = new JPanel();
        fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
        fila.setOpaque(false);
        fila.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        JLabel lblSala = new JLabel(nombreSala);
        lblSala.setFont(new Font("Arial", Font.BOLD, 15));
        lblSala.setForeground(AZUL_CLARO);
        lblSala.setAlignmentX(Component.CENTER_ALIGNMENT);
        fila.add(lblSala);
 
        JPanel panelHoras = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelHoras.setOpaque(false);
 
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        for (FuncionDTO funcion : funcionesSala) {
            JButton btnH = new JButton(funcion.getHora());
            btnH.setBackground(AZUL_CLARO);
            btnH.setForeground(TEXTO_BLANCO);
            btnH.setFont(new Font("Arial", Font.BOLD, 14));
            btnH.setFocusPainted(false);
            btnH.setPreferredSize(new Dimension(90, 40));
            btnH.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnH.addActionListener(e -> panelMediator.changePanel("seleccionAsientos", funcion));
            panelHoras.add(btnH);
        }
 
        fila.add(panelHoras);
        return fila;
    }
 
    private void recargarContenido() {
        try {
            removeAll();
            add(construirPanelSuperior(), BorderLayout.NORTH);
            add(new JScrollPane(construirContenidoCentrado()), BorderLayout.CENTER);
            revalidate();
            repaint();
        }catch (NegocioException e){
            //Logica de excepcion
        }
    }
 
    @Override
    public void onShow(Object object) {
        if (!(object instanceof SeleccionPeliculaDTO seleccionPeliculaDTO)) return;
 
        selectedMovie = seleccionPeliculaDTO;
 
        try {
            funciones = funcionBO.obtenerPorPelicula(selectedMovie.getId());
        } catch (NegocioException e) {
            funciones = new ArrayList<>();
        }
 
        // Seleccionar la primera fecha disponible por defecto
        if (!funciones.isEmpty()) {
            fechaSeleccionada = funciones.get(0).getFecha();
        }
 
        recargarContenido();
    }
}