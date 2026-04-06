package Elements.Displays;

import javax.swing.*;
import Elements.Buttons.GenericButton;
import Elements.Panels.MovieCover;
import Elements.Panels.SwitchPanel;

import java.awt.*;

public class TestDisplay extends JFrame {

    private GenericButton boton;
    private SwitchPanel contenedor;
    private GenericButton newPanelBoton;
    public TestDisplay(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contenedor = new SwitchPanel();
    }
}
