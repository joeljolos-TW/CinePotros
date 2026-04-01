package Elements.Displays;

import javax.swing.*;
import Elements.Buttons.GenericButton;
import Elements.Panels.MovieCover;

import java.awt.*;

public class TestDisplay extends JFrame {

    private GenericButton boton;

    public TestDisplay(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        boton = new GenericButton("Click me", false, 10, 120, 40, Color.WHITE, Color.BLUE, Color.CYAN);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(boton);

        MovieCover scottPilgrim = new MovieCover("src/main/resources/images.jpeg","Scott Pilgrim");
        scottPilgrim.setPreferredSize(new Dimension(200, 300));
        panel.add(scottPilgrim);

        add(panel);

        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        IO.println(scottPilgrim.getPosterLabel().getIcon().getIconWidth());
    }
}
