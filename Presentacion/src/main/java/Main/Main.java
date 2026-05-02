package Main;
import Elements.Displays.TestDisplay;
import javax.swing.SwingUtilities;
import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestDisplay("Cartelera"));

    }
}
