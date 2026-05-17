package Elements.Displays;

import javax.swing.*;

import DTO.ValidacionDTO;
import Elements.Buttons.GenericButton;
import Elements.Panels.*;
import Elements.Panels.Payment.PaymentSummary;
import Elements.Panels.ValidacionBoleto.BoletoValidadoPanel;


public class TestDisplay extends JFrame {

    private GenericButton boton;
    private SwitchPanel contenedor;
    private GenericButton newPanelBoton;
    private SwitchPanel panelNavegacion;


    public TestDisplay(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // Inicializar el controlador de navegación
        panelNavegacion = SwitchPanel.getInstance();
        BillboardPanel cartelera = new BillboardPanel();

        // Instanciar todas las pantallas

        SeleccionAsientosPanel asientos = new SeleccionAsientosPanel();
        SeleccionFuncionPanel funciones = new SeleccionFuncionPanel();
        GeneracionBoletoPanel boleto = new GeneracionBoletoPanel();
        PaymentSummary pago = new PaymentSummary();
        MisBoletosPanel misBoletos = new MisBoletosPanel();
        BoletoValidadoPanel infoValidacion = new BoletoValidadoPanel(new ValidacionDTO());

        // Agregar las pantallas al SwitchPanel con sus respectivos nombres clave
        panelNavegacion.addPanel(cartelera, "cartelera");
        panelNavegacion.addPanel(funciones, "seleccionFuncion");
        panelNavegacion.addPanel(asientos, "seleccionAsientos");
        panelNavegacion.addPanel(boleto, "generacionBoleto");
        panelNavegacion.addPanel(pago,"confirmacionPago");
        panelNavegacion.addPanel(misBoletos, "misBoletos");
        panelNavegacion.addPanel(infoValidacion, "validacionBoleto");

        // Agregar el panel de navegación al Frame principal
        add(panelNavegacion);
        
        setSize(1280, 720);
        setVisible(true);

    }

    public void changePanel(String identificador){
        panelNavegacion.changePanel(identificador);
    }

    public void changePanel(String identificador, Object object){
        panelNavegacion.changePanel(identificador, object);
    }
}