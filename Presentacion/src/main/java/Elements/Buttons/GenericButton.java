package Elements.Buttons;

import javax.swing.*;
import java.awt.*;


public class GenericButton extends JButton {
    //parametros del boton

    //hover significa que el boton tendra una transicion de que flotara
    //se hara cuando el mouse este encima de el
    //(si el tiempo y la necesidad lo ameritan se quitara esta opcion)
    private boolean hovered;
    private int cornerRadius;
    private int width;
    private int height;
    private Color colorText;
    private Color colorBackground;
    private Color colorHover;

    /**
     * Metodo Constructor para crear los botones sencillos que indiquen acciones
     * @param text
     * @param hovered
     * @param cornerRadius
     * @param width
     * @param height
     * @param colorText
     * @param colorBackground
     * @param colorHover
     */
    public GenericButton(String text, boolean hovered, int cornerRadius, int width, int height, Color colorText, Color colorBackground, Color colorHover) {
        super(text);
        this.hovered = hovered;
        this.cornerRadius = cornerRadius;
        this.width = width;
        this.height = height;
        this.colorText = colorText;
        this.colorBackground = colorBackground;
        this.colorHover = colorHover;


        setFont(null);
        setForeground(this.colorText);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addActionListener(e -> {test();});
    }

    //prueba que el boton funciona como es debido
    private void test(){
        IO.println("Click Exitoso");
    }

    /**
     * Metodo que dibuja el boton dentro de la pantalla
     * @param g the <code>Graphics</code> object to protect
     */
    // este metodo se invoca solo
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(hovered ? colorHover : colorBackground);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2d.setStroke(new BasicStroke(2));
        super.paintComponent(g);
        g2d.dispose();

    }

    /**
     * Metodo que le define un tamaño preferible al boton
     * @return
     */
    // este metodo se invoca solo
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
