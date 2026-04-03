package Elements.Panels;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase permite que no tengamos que hacer uso de muchos Frames para navegar en el
 * sistema
 */
public class SwitchPanel extends JPanel {
    /**
     * Metodo Constructor que genera el panel con CardLayout
     * ayudara con la navegacion del sistema
     */

    private CardLayout cardLayout;
    //esta variable para tener una lista de identificadores de paneles
    private List<String> identifiers;

    public SwitchPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        identifiers = new ArrayList<>();
    }

    /**
     * Metodo que agrega paneles al panel padre
     * ayudara para agregar funcionalidades al sistema
     * @param panel
     * @param identificador
     */
    public void addPanel(JPanel panel, String identificador) {
        add(panel, identificador);
        identifiers.add(identificador);
    }

    /**
     * Metodo que apartir del identificador del panel reedirigira
     * al panel deseado
     *
     * Ejemplo: servira para ponerle acciones a los botones
     * @param identificador
     */
    public void changePanel(String identificador) {
        cardLayout.show(this, identificador);
    }

    /**
     * metodo que regresa los identificadores de los paneles
     * @return
     */
    public List<String> showInfo(){
        return identifiers;
    }
}
