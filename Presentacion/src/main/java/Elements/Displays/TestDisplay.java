package Elements.Displays;

import javax.swing.*;
import Elements.Buttons.GenericButton;
import Elements.Panels.BillboardPanel;
import Elements.Panels.MovieCover;
import Elements.Panels.SwitchPanel;

import java.awt.*;

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
        panelNavegacion = SwitchPanel.getInstance();
        BillboardPanel cartelera = new BillboardPanel(panelNavegacion);
        panelNavegacion.addPanel(cartelera, "cartelera");
        add(panelNavegacion);
        setSize(1280,720);
        setVisible(true);
        

//        contenedor = new SwitchPanel();
    }
}
