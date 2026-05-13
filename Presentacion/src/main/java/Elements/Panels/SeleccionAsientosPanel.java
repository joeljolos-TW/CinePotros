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
public class SeleccionAsientosPanel extends JPanel implements Refreshable {

    private PanelMediator panelMediator;
    private ISubsistemaAsientos subsistemaAsientos;
    private ObjectId idFuncionActual;
    private List<Asiento> asientosSeleccionados;
    private JPanel contenedorCentral;

    public SeleccionAsientosPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        this.subsistemaAsientos = new SubsistemaAsientos();
        this.asientosSeleccionados = new ArrayList<>();
        
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        
        contenedorCentral = new JPanel(new BorderLayout());
        contenedorCentral.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        add(contenedorCentral, BorderLayout.CENTER);
        
        add(construirPiePagina(), BorderLayout.SOUTH);
    }

    @Override
    public void onShow(Object object) {
        if (object instanceof ObjectId) {
            this.idFuncionActual = (ObjectId) object;
            this.asientosSeleccionados.clear();
            actualizarMatrizAsientos();
        }
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

    private void actualizarMatrizAsientos() {
        contenedorCentral.removeAll();

        JLabel pantalla = new JLabel("P A N T A L L A", SwingConstants.CENTER);
        pantalla.setOpaque(true);
        pantalla.setBackground(Color.DARK_GRAY);
        pantalla.setForeground(Color.WHITE);
        pantalla.setFont(new Font("Arial", Font.BOLD, 14));
        pantalla.setPreferredSize(new Dimension(800, 40));
        pantalla.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contenedorCentral.add(pantalla, BorderLayout.NORTH);

        List<Asiento> asientos = subsistemaAsientos.obtenerAsientosPorFuncion(idFuncionActual);

        // Si no hay asientos generados, simulamos una matriz de 5x8 = 40 para la demo
        int rows = 5;
        int cols = 8;

        JPanel matriz = new JPanel(new GridLayout(rows, cols, 15, 15));
        matriz.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        matriz.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        Color colorDisponible = new Color(41, 128, 185);
        Color colorOcupado = new Color(149, 165, 166);
        Color colorSeleccionado = new Color(46, 204, 113); // Verde para seleccionado

        for (Asiento asiento : asientos) {
            JButton btnAsiento = new JButton("💺 " + asiento.getFila() + asiento.getNumero());
            btnAsiento.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
            btnAsiento.setForeground(Color.WHITE);
            btnAsiento.setFocusPainted(false);
            btnAsiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            if (asiento.getEstado() == EstadoAsiento.OCUPADO) {
                btnAsiento.setBackground(colorOcupado);
                btnAsiento.setEnabled(false);
            } else {
                btnAsiento.setBackground(colorDisponible);
                btnAsiento.addActionListener(e -> {
                    if (asientosSeleccionados.contains(asiento)) {
                        asientosSeleccionados.remove(asiento);
                        btnAsiento.setBackground(colorDisponible);
                    } else {
                        asientosSeleccionados.add(asiento);
                        btnAsiento.setBackground(colorSeleccionado);
                    }
                });
            }
            matriz.add(btnAsiento);
        }

        if (asientos.isEmpty()) {
            JLabel noAsientos = new JLabel("No hay asientos configurados para esta función.");
            noAsientos.setForeground(Color.WHITE);
            matriz.add(noAsientos);
        }

        contenedorCentral.add(matriz, BorderLayout.CENTER);
        contenedorCentral.revalidate();
        contenedorCentral.repaint();
    }

    private JPanel construirPiePagina() {
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        
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
        confirmBtn.addActionListener(e -> confirmarCompra());

        pie.add(backBtn);
        pie.add(confirmBtn);
        
        return pie;
    }

    private void confirmarCompra() {
        if (idFuncionActual == null) {
            JOptionPane.showMessageDialog(this, "No hay función seleccionada.");
            return;
        }
        if (asientosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un asiento.");
            return;
        }

        boolean exito = subsistemaAsientos.confirmarAsientosSeleccionados(idFuncionActual, asientosSeleccionados);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Asientos confirmados exitosamente!");
            // Regresar a la cartelera o ir al siguiente paso
            panelMediator.changePanel("cartelera"); // Opcional: panelMediator.changePanel("generacionBoleto");
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error al confirmar algunos asientos. Es posible que alguien más los haya tomado.");
            actualizarMatrizAsientos(); // Recargar el estado real
        }
    }
}

