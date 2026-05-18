package Pantallas;

import Control.ControlFactory;
import Control.IControlEntidades;
import DTOs.BoletoDTO;
import excepcion.NegocioException;
import itson.dominio.EstadoBoleto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel principal del empleado.
 *
 * Muestra una tabla con todos los boletos (fecha, hora, asientos, total,
 * estado). El empleado identifica el boleto del cliente visualmente,
 * lo selecciona y presiona "Marcar como PAGADO".
 */
public class ValidarBoletoPanel extends JPanel {

    // ── Colores ──────────────────────────────────────────────────────────────
    private static final Color FONDO_PRINCIPAL  = new Color(16, 27, 45);
    private static final Color FONDO_SECUNDARIO = new Color(25, 48, 90);
    private static final Color FONDO_ENCABEZADO = new Color(15, 26, 44);
    private static final Color TEXTO_PRINCIPAL  = new Color(245, 245, 245);
    private static final Color TEXTO_SECUNDARIO = new Color(162, 166, 175);
    private static final Color BORDE            = new Color(27, 50, 94);

    // ── Fuentes ───────────────────────────────────────────────────────────────
    private static final Font FUENTE_TITULO    = new Font("SansSerif", Font.BOLD, 18);
    private static final Font FUENTE_SUBTITULO = new Font("SansSerif", Font.BOLD, 13);
    private static final Font FUENTE_CUERPO    = new Font("SansSerif", Font.PLAIN, 13);

    // ── Columnas de la tabla ──────────────────────────────────────────────────
    private static final String[] COLUMNAS = {"Fecha", "Hora", "Asientos", "Total", "Estado"};

    // ── Componentes ───────────────────────────────────────────────────────────
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnMarcarPagado;
    private JButton btnRefrescar;
    private JLabel lblMensaje;

    // ── Datos cargados ────────────────────────────────────────────────────────
    private List<BoletoDTO> boletos;

    // ── Negocio ───────────────────────────────────────────────────────────────
    private final IControlEntidades<BoletoDTO> controlBoleto;

    public ValidarBoletoPanel() {
        this.controlBoleto = ControlFactory.getBoletoControl();

        setBackground(FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirTabla(), BorderLayout.CENTER);
        add(construirPie(), BorderLayout.SOUTH);

        cargarBoletos();
    }

    // ── Encabezado ────────────────────────────────────────────────────────────

    private JPanel construirEncabezado() {
        JPanel enc = new JPanel(new BorderLayout());
        enc.setBackground(FONDO_ENCABEZADO);
        enc.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel titulo = new JLabel("Lista de Boletos");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(TEXTO_PRINCIPAL);
        enc.add(titulo, BorderLayout.WEST);

        btnRefrescar = new JButton("↺ Refrescar");
        btnRefrescar.setFont(FUENTE_CUERPO);
        btnRefrescar.setBackground(FONDO_SECUNDARIO);
        btnRefrescar.setForeground(TEXTO_PRINCIPAL);
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.setBorderPainted(false);
        btnRefrescar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRefrescar.addActionListener(e -> cargarBoletos());
        enc.add(btnRefrescar, BorderLayout.EAST);

        return enc;
    }

    // ── Tabla ─────────────────────────────────────────────────────────────────

    private JScrollPane construirTabla() {
        modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setBackground(FONDO_SECUNDARIO);
        tabla.setForeground(TEXTO_PRINCIPAL);
        tabla.setFont(FUENTE_CUERPO);
        tabla.setRowHeight(32);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(new Color(45, 80, 140));
        tabla.setSelectionForeground(TEXTO_PRINCIPAL);
        tabla.setFillsViewportHeight(true);

        tabla.getTableHeader().setBackground(FONDO_ENCABEZADO);
        tabla.getTableHeader().setForeground(TEXTO_SECUNDARIO);
        tabla.getTableHeader().setFont(FUENTE_SUBTITULO);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Centrar todas las celdas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < COLUMNAS.length; i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        // Columna Estado con color según valor
        tabla.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        t, value, isSelected, hasFocus, row, col);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setBackground(isSelected ? new Color(45, 80, 140) : FONDO_SECUNDARIO);
                lbl.setForeground(colorParaEstado(value != null ? value.toString() : ""));
                return lbl;
            }
        });

        // Habilitar/deshabilitar botón al seleccionar fila
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) actualizarBoton();
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(FONDO_SECUNDARIO);
        scroll.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        return scroll;
    }

    // ── Pie de página ─────────────────────────────────────────────────────────

    private JPanel construirPie() {
        JPanel pie = new JPanel(new BorderLayout(10, 0));
        pie.setBackground(FONDO_ENCABEZADO);
        pie.setBorder(new EmptyBorder(12, 20, 12, 20));

        lblMensaje = new JLabel(" ");
        lblMensaje.setFont(FUENTE_CUERPO);
        lblMensaje.setForeground(TEXTO_SECUNDARIO);
        pie.add(lblMensaje, BorderLayout.WEST);

        btnMarcarPagado = new JButton("Marcar como PAGADO");
        btnMarcarPagado.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnMarcarPagado.setBackground(new Color(34, 160, 80));
        btnMarcarPagado.setForeground(Color.WHITE);
        btnMarcarPagado.setFocusPainted(false);
        btnMarcarPagado.setBorderPainted(false);
        btnMarcarPagado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMarcarPagado.setPreferredSize(new Dimension(200, 38));
        btnMarcarPagado.setEnabled(false);
        btnMarcarPagado.addActionListener(e -> confirmarPago());
        pie.add(btnMarcarPagado, BorderLayout.EAST);

        return pie;
    }

    // ── Cargar boletos desde MongoDB ──────────────────────────────────────────

    private void cargarBoletos() {
        modeloTabla.setRowCount(0);
        lblMensaje.setText("Cargando...");
        btnMarcarPagado.setEnabled(false);

        try {
            boletos = controlBoleto.obtenerTodos();

            if (boletos == null || boletos.isEmpty()) {
                lblMensaje.setText("No hay boletos registrados.");
                return;
            }

            for (BoletoDTO b : boletos) {
                String asientos = b.getNumAsiento() != null
                        ? String.join(", ", b.getNumAsiento()) : "—";
                String total  = b.getTotal()  != null ? "$" + String.format("%.2f", b.getTotal()) : "—";
                String fecha  = b.getFecha()  != null ? b.getFecha()  : "—";
                String hora   = b.getHora()   != null ? b.getHora()   : "—";
                String estado = b.getEstado() != null ? b.getEstado().name() : "—";

                modeloTabla.addRow(new Object[]{fecha, hora, asientos, total, estado});
            }

            lblMensaje.setText("Selecciona un boleto PENDIENTE para marcarlo como pagado.");

        } catch (NegocioException ex) {
            lblMensaje.setText("Error al cargar boletos: " + ex.getMessage());
        }
    }

    // ── Habilitar botón solo si el boleto seleccionado está PENDIENTE ─────────

    private void actualizarBoton() {
        int fila = tabla.getSelectedRow();
        if (fila < 0 || boletos == null || fila >= boletos.size()) {
            btnMarcarPagado.setEnabled(false);
            lblMensaje.setText(" ");
            return;
        }

        BoletoDTO seleccionado = boletos.get(fila);

        if (seleccionado.getEstado() == EstadoBoleto.PENDIENTE) {
            btnMarcarPagado.setEnabled(true);
            lblMensaje.setText("Boleto PENDIENTE seleccionado.");
        } else {
            btnMarcarPagado.setEnabled(false);
            lblMensaje.setText("Estado: " + seleccionado.getEstado().name() + " — no se puede marcar como pagado.");
        }
    }

    // ── Confirmar y ejecutar el pago ──────────────────────────────────────────

    private void confirmarPago() {
        int fila = tabla.getSelectedRow();
        if (fila < 0 || boletos == null || fila >= boletos.size()) return;

        BoletoDTO seleccionado = boletos.get(fila);
        String asientos = seleccionado.getNumAsiento() != null
                ? String.join(", ", seleccionado.getNumAsiento()) : "—";

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Confirmas el pago del boleto?\n\n"
                + "Fecha:    " + seleccionado.getFecha() + "  " + seleccionado.getHora() + "\n"
                + "Asientos: " + asientos + "\n"
                + "Total:    $" + String.format("%.2f", seleccionado.getTotal()),
                "Confirmar pago",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) return;

        try {
            seleccionado.setEstado(EstadoBoleto.PAGADO);
            controlBoleto.actualizar(seleccionado);
            cargarBoletos(); // refrescar la tabla
            JOptionPane.showMessageDialog(this,
                    "Boleto marcado como PAGADO exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar el boleto: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Color según estado ────────────────────────────────────────────────────

    private Color colorParaEstado(String estado) {
        switch (estado) {
            case "PENDIENTE":  return new Color(255, 193, 7);
            case "PAGADO":     return new Color(34, 160, 80);
            case "ESCANEADO":  return new Color(0, 136, 255);
            case "CANCELADO":  return new Color(220, 80, 80);
            default:           return TEXTO_SECUNDARIO;
        }
    }
}
