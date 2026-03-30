package Elements.Displays;

import javax.swing.*;
import Elements.Buttons.GenericButton;

import java.awt.*;

public class TestDisplay extends JFrame {

    private GenericButton boton;

    public TestDisplay(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        boton = new GenericButton("Click me", false, 10, 120, 40, Color.WHITE, Color.BLUE, Color.CYAN);
        JPanel panel = new JPanel();
        panel.add(boton);
        add(panel);
    }
}
