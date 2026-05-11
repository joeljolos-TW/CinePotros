package Elements.Panels.Payment;

import javax.swing.*;

import Elements.Panels.SwitchPanel;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
import PaymentDTO.PaymentSummaryDTO;

import java.awt.*;

public class PaymentSummary extends JPanel {
    private PanelMediator panelMediator;
    private PaymentSummaryDTO paymentSummaryDTO;

    public PaymentSummary() {
        this.panelMediator= SwitchPanel.getInstance();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());


    }

    public JPanel crearDetalles(){
        JPanel detalles = new JPanel(null){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                float[] patron = {5,5};
                g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10, patron, 0));
                g2d.setColor(Color.white);
                g2d.drawLine(0, getHeight()/3, getWidth(), getHeight()/3);
                g2d.drawLine(0, getHeight()-(getHeight()/3), getWidth(), getHeight()-(getHeight()/3));
                g2d.dispose();
            }
        };
        detalles.setBorder(BorderFactory.createEmptyBorder());
        detalles.setBounds(0,0,600,400);

        return detalles;

    }
}
