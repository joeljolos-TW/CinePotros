package Main;
import DTO.ValidacionDTO;
import Elements.Displays.TestDisplay;
import org.bson.types.ObjectId;

import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestDisplay td = new TestDisplay("cartelera");
            td.changePanel("validacionBoleto", new ValidacionDTO(true, new ObjectId()));
        });

    }
}
