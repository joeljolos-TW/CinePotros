package Test;

import javax.swing.*;
import java.awt.*;

public class testFrame1 extends JFrame {

    public testFrame1(){
        setBounds(0,0,1366, 768);
        setLayout(null);
        getContentPane().setBackground(Color.blue);

        add(crearDatos());
        add(crearDetalles());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public JPanel crearDatos(){
        JPanel datos = new JPanel(null){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillRoundRect(30, 90, 570, 200, 10, 10);

                float[] patron = {5,5};
                g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10, patron, 0));
                g2d.setColor(Color.white);
                g2d.drawLine(30, 157, getWidth()+30, 157);
                g2d.drawLine(30, 210, getWidth()+30, 210);
                g2d.dispose();
            }
        };
        datos.setBorder(BorderFactory.createEmptyBorder());
        datos.setBounds(0,0,600,400);

        return datos;

    }
    
    public JPanel crearDetalles(){
        int entradas = 144;
        int servicio = 0;
        int total = entradas+servicio;
        JPanel detalles = new JPanel(null){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.WHITE);
                g2d.drawLine(30, 90, getWidth()+30, 90);

                float[] patron = {5,5};
                g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10, patron, 0));
                g2d.drawLine(30, 0, getWidth()+30, 0);
                g2d.drawLine(30, 170, getWidth()+30, 170);

                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillRoundRect(30, 210, 600, 80, 10, 10);
            }

        };

        JLabel tituloDetalles = new JLabel("Detalles del pago:");
        JLabel entradasLabel = new JLabel("Entradas:");
        JLabel ServicioLabel = new JLabel("Cargo por servicio:");
        JLabel totalLabel = new JLabel("Total a pagar:");

        JLabel entradasPrecio= new JLabel("$ "+entradas);
        JLabel servicioPrecio= new JLabel("$ "+servicio);
        JLabel totalPrecio = new JLabel("$ "+ total);

        detalles.add(tituloDetalles);
        tituloDetalles.setFont(new Font("Arial", Font.BOLD, 30));
        tituloDetalles.setSize(300,40);
        tituloDetalles.setForeground(Color.white);
        tituloDetalles.setLocation(30,10);
        tituloDetalles.setVisible(true);

        detalles.add(entradasLabel);
        entradasLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        entradasLabel.setSize(300,30);
        entradasLabel.setForeground(Color.white);
        entradasLabel.setLocation(30,60);
        entradasLabel.setVisible(true);

        detalles.add(ServicioLabel);
        ServicioLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ServicioLabel.setSize(300,30);
        ServicioLabel.setForeground(Color.white);
        ServicioLabel.setLocation(30,120);
        ServicioLabel.setVisible(true);

        detalles.add(totalLabel);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalLabel.setSize(300,30);
        totalLabel.setForeground(Color.white);
        totalLabel.setLocation(30,230);
        totalLabel.setVisible(true);

        detalles.add(entradasPrecio);
        entradasPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
        entradasPrecio.setSize(300,30);
        entradasPrecio.setForeground(Color.white);
        entradasPrecio.setLocation(540,60);
        entradasPrecio.setVisible(true);

        detalles.add(servicioPrecio);
        servicioPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
        servicioPrecio.setSize(300,30);
        servicioPrecio.setForeground(Color.white);
        servicioPrecio.setLocation(540,120);
        servicioPrecio.setVisible(true);

        detalles.add(totalPrecio);
        totalPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
        totalPrecio.setSize(300,30);
        totalPrecio.setForeground(Color.white);
        totalPrecio.setLocation(540,230);
        totalPrecio.setVisible(true);

        detalles.setBorder(BorderFactory.createEmptyBorder());
        detalles.setBounds(0,400,659,330);
        return detalles;
    }
}
