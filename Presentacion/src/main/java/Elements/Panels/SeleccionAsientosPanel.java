package Elements.Panels;

import DAO.PromocionDAO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import Elements.Buttons.GenericButton;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
import itson.dominio.Promocion;
import itson.dominio.TipoPromocion;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Panel de Seleccion de Asientos con funcionalidad de promociones integrada.
 *
 * Flujo principal (Diagrama de Secuencia):
 *   1. Usuario selecciona asientos  -> se calcula subtotal base
 *   2. Usuario ingresa codigo promo -> aplicarPromocion(codigo)
 *   3. PromocionDAO.buscarPorCodigo -> Promocion.esValida()
 *   4a. Valida: calcularTotal(monto) -> mostrarPrecioFinal()
 *   4b. Invalida/vencida: mostrar error, continuar sin promocion
 *   5. Usuario confirma             -> guardarBoleto(totalFinal)
 */
public class SeleccionAsientosPanel extends JPanel implements Refreshable {

    // -----------------------------------------------------------------------
    // Constantes de negocio
    // -----------------------------------------------------------------------
    private static final double PRECIO_POR_ASIENTO = 120.0;

    // -----------------------------------------------------------------------
    // Navegacion y mediador
    // -----------------------------------------------------------------------
    private PanelMediator panelMediator;
    private FuncionDTO funcionActual;

    // -----------------------------------------------------------------------
    // Estado de asientos
    // -----------------------------------------------------------------------
    private final List<String> asientosSeleccionados = new ArrayList<>();
    private final List<JButton> botonesAsiento = new ArrayList<>();

    // -----------------------------------------------------------------------
    // Estado de promocion
    // -----------------------------------------------------------------------
    private PromocionDAO promoDAO;
    private Promocion promocionAplicada;
    private double totalConDescuento;

    // -----------------------------------------------------------------------
    // Componentes de UI dinamicos
    // -----------------------------------------------------------------------
    private JPanel panelCentral;
    private JLabel lblSubtotal;
    private JLabel lblDescuento;
    private JLabel lblTotal;
    private JTextField txtCodigoPromo;

    public SeleccionAsientosPanel() {
        this.panelMediator = SwitchPanel.getInstance();
        this.promoDAO = new PromocionDAO();
        this.promocionAplicada = null;
        this.totalConDescuento = 0.0;

        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());
        add(construirEncabezado(), BorderLayout.NORTH);
        panelCentral = construirMatrizAsientos();
        add(panelCentral, BorderLayout.CENTER);
        add(construirPiePagina(), BorderLayout.SOUTH);
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

    private JPanel construirMatrizAsientos() {
        asientosSeleccionados.clear();
        botonesAsiento.clear();

        JPanel contenedorCentral = new JPanel(new BorderLayout());
        contenedorCentral.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Info de la función si ya se recibió
        if (funcionActual != null) {
            String fecha = funcionActual.getFecha().format(DateTimeFormatter.ofPattern("dd 'de' MMMM"));
            String hora  = funcionActual.getHora().format(DateTimeFormatter.ofPattern("HH:mm"));
            String sala  = funcionActual.getSalaFuncion().getNombre();
            JLabel lblFuncion = new JLabel(sala + "  ·  " + fecha + " - " + hora, SwingConstants.CENTER);
            lblFuncion.setFont(new Font("Arial", Font.PLAIN, 13));
            lblFuncion.setForeground(UtilGeneral.TEXTO_SECUNDARIO);
            lblFuncion.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            contenedorCentral.add(lblFuncion, BorderLayout.NORTH);
        }

        JLabel pantalla = new JLabel("P A N T A L L A", SwingConstants.CENTER);
        pantalla.setOpaque(true);
        pantalla.setBackground(Color.DARK_GRAY);
        pantalla.setForeground(Color.WHITE);
        pantalla.setFont(new Font("Arial", Font.BOLD, 14));
        pantalla.setPreferredSize(new Dimension(800, 40));
        pantalla.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel wrapPantalla = new JPanel(new BorderLayout());
        wrapPantalla.setOpaque(false);
        wrapPantalla.add(pantalla, BorderLayout.CENTER);

        JPanel matriz = new JPanel(new GridLayout(5, 8, 15, 15));
        matriz.setBackground(UtilGeneral.FONDO_PRINCIPAL);
        matriz.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        String[] filas = {"A", "B", "C", "D", "E"};
        int[] ocupados = {};

        for (int i = 0; i < 40; i++) {
            String fila = filas[i / 8];
            int numAsiento = (i % 8) + 1;
            String idAsiento = fila + numAsiento;

            JButton asiento = new JButton("💺 " + idAsiento);
            asiento.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
            asiento.setForeground(Color.WHITE);
            asiento.setFocusPainted(false);
            asiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            boolean estaOcupado = false;
            for (int o : ocupados) if (o == i) { estaOcupado = true; break; }

            if (estaOcupado) {
                asiento.setBackground(UtilGeneral.ASIENTO_OCUPADO);
                asiento.setEnabled(false);
                asiento.setToolTipText("Asiento ocupado");
            } else {
                asiento.setBackground(UtilGeneral.ASIENTO_DISPONIBLE);
                asiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
                asiento.addActionListener(e -> {
                    if (asientosSeleccionados.contains(idAsiento)) {
                        asientosSeleccionados.remove(idAsiento);
                        asiento.setBackground(UtilGeneral.ASIENTO_DISPONIBLE);
                        asiento.setForeground(Color.WHITE);
                    } else {
                        asientosSeleccionados.add(idAsiento);
                        asiento.setBackground(UtilGeneral.ASIENTO_SELECCIONADO);
                        asiento.setForeground(new Color(10, 25, 49));
                    }
                    actualizarPrecios();
                });
            }

            botonesAsiento.add(asiento);
            matriz.add(asiento);
        }

        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(wrapPantalla, BorderLayout.NORTH);
        centro.add(matriz, BorderLayout.CENTER);

        contenedorCentral.add(centro, BorderLayout.CENTER);
        return contenedorCentral;
    }

    private JPanel construirPiePagina() {
        JPanel pie = new JPanel();
        pie.setLayout(new BoxLayout(pie, BoxLayout.Y_AXIS));
        pie.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        pie.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        // --- Sección de promoción ---
        JPanel panelPromo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        panelPromo.setOpaque(false);

        JLabel lblPromoLabel = new JLabel("Código de promoción:");
        lblPromoLabel.setForeground(UtilGeneral.TEXTO_PRINCIPAL);

        txtCodigoPromo = new JTextField(12);

        JButton btnAplicarPromo = new JButton("Aplicar");
        btnAplicarPromo.setBackground(UtilGeneral.BOTON_AZUL);
        btnAplicarPromo.setForeground(Color.WHITE);
        btnAplicarPromo.setFocusPainted(false);
        btnAplicarPromo.addActionListener(e -> aplicarPromocion(txtCodigoPromo.getText()));

        panelPromo.add(lblPromoLabel);
        panelPromo.add(txtCodigoPromo);
        panelPromo.add(btnAplicarPromo);

        // --- Sección de precios ---
        JPanel panelPrecios = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 4));
        panelPrecios.setOpaque(false);

        lblSubtotal  = new JLabel("Subtotal: $0.00");
        lblDescuento = new JLabel("Descuento: $0.00");
        lblTotal     = new JLabel("Total: $0.00");

        lblSubtotal.setForeground(UtilGeneral.TEXTO_SECUNDARIO);
        lblDescuento.setForeground(new Color(46, 204, 113));
        lblTotal.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        lblTotal.setFont(UtilGeneral.FUENTE_SUBTITULO);

        panelPrecios.add(lblSubtotal);
        panelPrecios.add(lblDescuento);
        panelPrecios.add(lblTotal);

        // --- Botones de navegación ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);

        GenericButton backBtn = new GenericButton(
                "Volver a Horarios", true, 10, 160, 110,
                UtilGeneral.TEXTO_PRINCIPAL, UtilGeneral.BOTON_AZUL, UtilGeneral.FONDO_SECUNDARIO);
        backBtn.addActionListener(e -> panelMediator.changePanel("seleccionFuncion"));

        GenericButton confirmBtn = new GenericButton(
                "Confirmar Compra", true, 10, 160, 110,
                UtilGeneral.TEXTO_PRINCIPAL, UtilGeneral.BOTON_AZUL, UtilGeneral.FONDO_SECUNDARIO);
        confirmBtn.addActionListener(e -> confirmarCompra());

        panelBotones.add(backBtn);
        panelBotones.add(confirmBtn);

        pie.add(panelPromo);
        pie.add(panelPrecios);
        pie.add(panelBotones);

        return pie;
    }

    // -----------------------------------------------------------------------
    // Lógica de promociones
    // -----------------------------------------------------------------------

    /**
     * Aplica una promoción al total actual según el flujo del diagrama de secuencia.
     * Flujo principal: buscar -> validar -> calcular -> mostrar.
     * Flujo alternativo: codigo no existe o vencido -> error -> continuar sin promo.
     */
    public void aplicarPromocion(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingresa un código de promoción.",
                    "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Promocion promo = promoDAO.buscarPorCodigo(codigo);

        if (promo == null) {
            JOptionPane.showMessageDialog(this,
                    "El código \"" + codigo + "\" no existe.",
                    "Promoción inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!promo.esValida()) {
            JOptionPane.showMessageDialog(this,
                    "La promoción \"" + codigo + "\" ha vencido o no es válida.",
                    "Promoción vencida", JOptionPane.WARNING_MESSAGE);
            promocionAplicada = null;
            actualizarPrecios();
            return;
        }

        promocionAplicada = promo;
        actualizarPrecios();
        mostrarPrecioFinal();
    }

    /**
     * Calcula el total aplicando la promoción activa (si existe).
     */
    public double calcularTotal(double subtotal) {
        if (promocionAplicada == null) {
            return subtotal;
        }
        if (promocionAplicada.getTipo() == TipoPromocion.PORCENTAJE) {
            double descuento = subtotal * promocionAplicada.getDescuento();
            return Math.max(0, subtotal - descuento);
        } else {
            return Math.max(0, subtotal - promocionAplicada.getDescuento());
        }
    }

    /**
     * Muestra el precio final con descuento en un diálogo y actualiza los labels.
     */
    public void mostrarPrecioFinal() {
        double subtotal = asientosSeleccionados.size() * PRECIO_POR_ASIENTO;
        double descuento = subtotal - totalConDescuento;

        String porciento = (promocionAplicada != null &&
                            promocionAplicada.getTipo() == TipoPromocion.PORCENTAJE)
                ? String.format(" (%.0f%%)", promocionAplicada.getDescuento() * 100) : "";

        JOptionPane.showMessageDialog(this,
                String.format("¡Promoción aplicada!\nDescuento%s: -$%.2f\nTotal: $%.2f",
                        porciento, descuento, totalConDescuento),
                "Promoción válida ✓", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Confirma la compra generando un BoletoDTO y navegando al panel de generación.
     */
    public void confirmarCompra() {
        if (asientosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor selecciona al menos un asiento.",
                    "Sin asientos seleccionados",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double subtotal = asientosSeleccionados.size() * PRECIO_POR_ASIENTO;
        double total    = calcularTotal(subtotal);
        double descuento = subtotal - total;

//        BoletoDTO boleto = new BoletoDTO(false, null, funcionActual, new ArrayList<>(asientosSeleccionados));
//        // Guardamos descuento en el DTO si BoletoDTO lo soporta (extensible)
//        panelMediator.changePanel("generacionBoleto", boleto);
    }

    // -----------------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------------

    private void actualizarPrecios() {
        double subtotal = asientosSeleccionados.size() * PRECIO_POR_ASIENTO;
        totalConDescuento = calcularTotal(subtotal);
        double descuento = subtotal - totalConDescuento;

        if (lblSubtotal != null) {
            lblSubtotal.setText(String.format("Subtotal: $%.2f", subtotal));
            lblDescuento.setText(String.format("Descuento: -$%.2f", descuento));
            lblTotal.setText(String.format("Total: $%.2f", totalConDescuento));
        }
    }

    private void limpiarEstado() {
        asientosSeleccionados.clear();
        promocionAplicada = null;
        totalConDescuento = 0.0;
        if (txtCodigoPromo != null) txtCodigoPromo.setText("");
        if (lblSubtotal != null) {
            lblSubtotal.setText("Subtotal: $0.00");
            lblDescuento.setText("Descuento: $0.00");
            lblTotal.setText("Total: $0.00");
        }
    }

    @Override
    public void onShow(Object object) {
        if (object instanceof FuncionDTO funcion) {
            funcionActual = funcion;
        }

        limpiarEstado();

        if (panelCentral != null) remove(panelCentral);
        panelCentral = construirMatrizAsientos();
        add(panelCentral, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
