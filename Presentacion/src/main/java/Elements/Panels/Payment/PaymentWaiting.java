package Elements.Panels.Payment;
import Elements.Panels.SwitchPanel;
import Mediator.PanelMediator;
import java.net.URL;
import javax.swing.*;

public class PaymentWaiting extends JPanel {
    private PanelMediator panelMediator;

    public PaymentWaiting() {
        panelMediator= SwitchPanel.getInstance();

        while(verificacion()){
            panelMediator.changePanel("pagoConfirmacion");
            add(crearRueda());

        }
    }
    public JLabel crearEncabezado(){return null;}


    public JLabel crearRueda(){
        try{
            URL gifUrl = new URL("https://media1.tenor.com/m/cYG1cbaNN6cAAAAC/waiting-ugh.gif");

            ImageIcon imagen = new ImageIcon(gifUrl);
            JLabel lblRueda = new JLabel(imagen);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public JLabel crearConsejoOeste(){return null;}
    public JLabel crearConsejoEste(){return null;}
    public boolean verificacion() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "El cliente pago con lo que es debido?",
                "Confirmacion de Pago",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if(opcion == JOptionPane.YES_OPTION){return true;}
        else{return false;}
    }
}
