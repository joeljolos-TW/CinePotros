package Elements.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieCover extends JPanel {
    private JLabel posterLabel;
    private JLabel nameLabel;

    private String posterPath;
    private String movieName;

    private ImageIcon posterImage;

    public MovieCover(String posterPath, String movieName) {
        super(null);
        setVisible(true);
        setSize(200, 300);
        this.posterPath = posterPath;
        this.movieName = movieName;
        this.posterImage = new ImageIcon(posterPath);

        int width = posterImage.getIconWidth();
        int height = posterImage.getIconHeight();
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        initComponents();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                test();
            }
        });
    }


    public void initComponents() {
        int width = posterImage.getIconWidth();
        int height = posterImage.getIconHeight();

        posterLabel = new JLabel();
        posterLabel.setIcon(new ImageIcon(posterPath));
        posterLabel.setOpaque(false);
        posterLabel.setBounds(0, 0, 200, 300);

        nameLabel = new JLabel();
        nameLabel.setText(movieName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(51,51,51,100));
        nameLabel.setBounds(0, height - 40, width, 28);


        add(nameLabel);
        add(posterLabel);

    }

    //prueba que el boton funciona como es debido
    private void test(){
        IO.println("Click Exitoso");
    }

    public JLabel getPosterLabel() {
        return posterLabel;
    }
}
