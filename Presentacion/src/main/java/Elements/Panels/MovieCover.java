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

    private Runnable alHacerClic;
    private ImageIcon posterImage;

    public MovieCover(String posterPath, String movieName, Runnable alHacerClic) {
        super(new BorderLayout());
        setVisible(true);
        this.posterPath = posterPath;
        this.movieName = movieName;
        this.alHacerClic = alHacerClic;
        this.posterImage = new ImageIcon(getClass().getResource(posterPath));
        initComponents();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (alHacerClic != null) {
                    alHacerClic.run();
                }
            }
        });
    }

    public void initComponents() {

        posterLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (posterImage != null) {
                    Image img = posterImage.getImage();
                    int imgW = img.getWidth(this);
                    int imgH = img.getHeight(this);
                    int panelW = getWidth();
                    int panelH = getHeight();

                    double escala = Math.max((double) panelW / imgW, (double) panelH / imgH);
                    int drawW = (int) (imgW * escala);
                    int drawH = (int) (imgH * escala);
                    int x = (panelW - drawW) / 2;
                    int y = (panelH - drawH) / 2;

                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setClip(0, 0, panelW, panelH);
                    g2.drawImage(img, x, y, drawW, drawH, this);
                    g2.dispose();
                }
            }
        };
        posterLabel.setOpaque(false);

        nameLabel = new JLabel(movieName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(51, 51, 51, 180));
        nameLabel.setPreferredSize(new Dimension(0, 28));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));

        add(posterLabel, BorderLayout.CENTER);

    }

    //prueba que el boton funciona como es debido
    private void test() {
//        IO.println("Click Exitoso");
    }

    public JLabel getPosterLabel() {
        return posterLabel;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 248);
    }
}
