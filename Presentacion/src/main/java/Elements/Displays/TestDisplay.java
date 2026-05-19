package Elements.Displays;

import javax.swing.*;

import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import Elements.Buttons.GenericButton;
import Elements.Panels.*;
import Elements.Panels.Payment.PaymentSummary;
import Elements.Panels.ValidacionBoleto.BoletoValidadoPanel;

/**
 * Ventana principal de la aplicación que actúa como contenedor de todos
 * los paneles del sistema. Utiliza un {@link SwitchPanel} con {@code CardLayout}
 * para gestionar la navegación entre pantallas sin necesidad de múltiples ventanas.
 *
 * <p>Registra e instancia todos los paneles del flujo de la aplicación:
 * cartelera, selección de función, selección de asientos, generación de boleto,
 * pago, mis boletos, confirmación de cancelación y validación de boleto.</p>
 */
public class TestDisplay extends JFrame {

    private GenericButton boton;
    private SwitchPanel contenedor;
    private GenericButton newPanelBoton;
    private SwitchPanel panelNavegacion;

    /**
     * Constructor que inicializa la ventana principal, instancia todos los paneles
     * del sistema y los registra en el {@link SwitchPanel} con sus identificadores
     * correspondientes.
     *
     * <p>Identificadores de paneles registrados:</p>
     * <ul>
     *   <li>{@code "cartelera"} — Panel principal con la cartelera de películas.</li>
     *   <li>{@code "seleccionFuncion"} — Panel para seleccionar la función deseada.</li>
     *   <li>{@code "seleccionAsientos"} — Panel para seleccionar los asientos.</li>
     *   <li>{@code "generacionBoleto"} — Panel con el resumen y QR del boleto.</li>
     *   <li>{@code "confirmacionPago"} — Panel de resumen de pago.</li>
     *   <li>{@code "misBoletos"} — Panel con los boletos del usuario.</li>
     *   <li>{@code "confirmacionCancelacion"} — Panel para confirmar la cancelación.</li>
     *   <li>{@code "validacionBoleto"} — Panel de validación del QR del boleto.</li>
     * </ul>
     *
     * @param title título de la ventana mostrado en la barra superior.
     */
    public TestDisplay(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panelNavegacion = SwitchPanel.getInstance();

        BillboardPanel cartelera = new BillboardPanel();
        SeleccionAsientosPanel asientos = new SeleccionAsientosPanel(new FuncionDTO());
        SeleccionFuncionPanel funciones = new SeleccionFuncionPanel();
        GeneracionBoletoPanel boleto = new GeneracionBoletoPanel(new BoletoDTO());
        PaymentSummary pago = new PaymentSummary();
        MisBoletosPanel misBoletos = new MisBoletosPanel();
        ConfirmacionCancelacionPanel confirmacion = new ConfirmacionCancelacionPanel();
        BoletoValidadoPanel infoValidacion = new BoletoValidadoPanel();

        panelNavegacion.addPanel(cartelera, "cartelera");
        panelNavegacion.addPanel(funciones, "seleccionFuncion");
        panelNavegacion.addPanel(asientos, "seleccionAsientos");
        panelNavegacion.addPanel(boleto, "generacionBoleto");
        panelNavegacion.addPanel(pago, "confirmacionPago");
        panelNavegacion.addPanel(misBoletos, "misBoletos");
        panelNavegacion.addPanel(confirmacion, "confirmacionCancelacion");
        panelNavegacion.addPanel(infoValidacion, "validacionBoleto");

        add(panelNavegacion);
        setSize(1280, 720);
        setVisible(true);
    }

    /**
     * Navega al panel identificado por el nombre dado sin pasar datos adicionales.
     *
     * @param identificador el nombre del panel al que se desea navegar.
     */
    public void changePanel(String identificador) {
        panelNavegacion.changePanel(identificador);
    }

    /**
     * Navega al panel identificado por el nombre dado pasando un objeto con datos.
     * Llama a {@code onShow(object)} del panel destino si implementa {@link Refreshable}.
     *
     * @param identificador el nombre del panel al que se desea navegar.
     * @param object        el objeto con los datos a pasar al panel destino.
     */
    public void changePanel(String identificador, Object object) {
        panelNavegacion.changePanel(identificador, object);
    }
}