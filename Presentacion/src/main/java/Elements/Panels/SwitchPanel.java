package Elements.Panels;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import DTOs.SeleccionPeliculaDTO;
import Mediator.PanelMediator;

/**
 * Esta clase permite que no tengamos que hacer uso de muchos Frames para navegar en el
 * sistema
 */
public class SwitchPanel extends JPanel implements PanelMediator {
    /**
     * Metodo Constructor que genera el panel con CardLayout
     * ayudara con la navegacion del sistema
     */
    private static SwitchPanel Instance;
    private CardLayout cardLayout;

    //esta variable para tener una lista de identificadores de paneles
    private List<String> identifiers;
    private Map<String,JPanel> panels = new HashMap<>();

    public SwitchPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        identifiers = new ArrayList<>();
    }

    public static SwitchPanel getInstance() {
        if(Instance == null){
            Instance = new SwitchPanel();
        }
        return Instance;
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
        panels.put(identificador, panel);
    }

    @Override
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


    @Override
    public void changePanel(String identificador, Object object) {

        JPanel panel = panels.get(identificador);
        if(panel instanceof Elements.Panels.Refreshable){
            ((Refreshable) panel).onShow(object);
        }
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
