package Elements.Displays;

import javax.swing.*;
import Elements.Buttons.GenericButton;
import Elements.Panels.BillboardPanel;
import Elements.Panels.GeneracionBoletoPanel;
import Elements.Panels.Payment.PaymentSummary;
import Elements.Panels.SeleccionFuncionPanel;
import Elements.Panels.SeleccionAsientosPanel;
import Elements.Panels.MisBoletosPanel;
import Elements.Panels.SwitchPanel;




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
        panelNavegacion.addPanel(cartelera, "cartelera");
        add(panelNavegacion);
        setSize(1280,720);
        setVisible(true);



        // Instanciar todas las pantallas


        SeleccionAsientosPanel asientos = new SeleccionAsientosPanel();
        SeleccionFuncionPanel funciones = new SeleccionFuncionPanel();
        GeneracionBoletoPanel boleto = new GeneracionBoletoPanel();
        PaymentSummary pago = new PaymentSummary();
        MisBoletosPanel misBoletos = new MisBoletosPanel();
       

        // Agregar las pantallas al SwitchPanel con sus respectivos nombres clave
        panelNavegacion.addPanel(cartelera, "cartelera");
        panelNavegacion.addPanel(funciones, "seleccionFuncion");
        panelNavegacion.addPanel(asientos, "seleccionAsientos");
        panelNavegacion.addPanel(boleto, "generacionBoleto");
        panelNavegacion.addPanel(pago,"confirmacionPago");
        panelNavegacion.addPanel(misBoletos, "misBoletos");
      
        

        // Agregar el panel de navegación al Frame principal
        add(panelNavegacion);
        
        setSize(1280, 720);
        setVisible(true);

    }
}