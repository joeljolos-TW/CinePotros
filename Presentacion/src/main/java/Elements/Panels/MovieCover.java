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

    private static final int CARD_WIDTH=379;
    private static final int CARD_HEIGHT=430;
    public MovieCover(String posterPath, String movieName) {
        super(new BorderLayout());
        setVisible(true);
        setSize(200, 300);
        this.posterPath = posterPath;
        this.movieName = movieName;
        this.posterImage = new ImageIcon(posterPath);

        int width = posterImage.getIconWidth();
        int height = posterImage.getIconHeight();
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

        Image scaled = posterImage.getImage()
                .getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);

        posterLabel = new JLabel(new ImageIcon(scaled));
        posterLabel.setBounds(0, 0, CARD_WIDTH, CARD_HEIGHT);

        nameLabel = new JLabel(movieName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(51,51,51,100));
        nameLabel.setBounds(0, CARD_HEIGHT - 40, CARD_WIDTH, 28);


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
