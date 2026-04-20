package Elements.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MovieCarousel extends JPanel {

    private static final int VISIBLE = 3;

    private final List<MovieCover> movies;
    private int currentPage = 0;

    private final JPanel coversPanel;
    private final JLabel prevArrow;
    private final JLabel nextArrow;

    public MovieCarousel(List<MovieCover> movies) {
        this.movies = movies;
        setLayout(new BorderLayout(10, 0));

        prevArrow = new JLabel("<");
        nextArrow = new JLabel(">");
        coversPanel = new JPanel(new GridLayout(1, VISIBLE, 10, 0));

        setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        coversPanel.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        styleArrow(prevArrow);
        styleArrow(nextArrow);

        prevArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    refresh();
                }
            }
        });

        nextArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPage < totalPages() - 1) {
                    currentPage++;
                    refresh();
                }
            }
        });

        add(prevArrow, BorderLayout.WEST);
        add(coversPanel, BorderLayout.CENTER);
        add(nextArrow, BorderLayout.EAST);

        refresh();
    }

    private void styleArrow(JLabel arrow) {
        arrow.setFont(arrow.getFont().deriveFont(Font.BOLD, 24f));
        arrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        arrow.setHorizontalAlignment(SwingConstants.CENTER);
        arrow.setVerticalAlignment(SwingConstants.CENTER);
        arrow.setPreferredSize(new Dimension(40, 0));
    }

    private int totalPages() {
        return (int) Math.ceil(movies.size() / (double) VISIBLE);
    }

    private void refresh() {
        coversPanel.removeAll();

        int from = currentPage * VISIBLE;
        int to = Math.min(from + VISIBLE, movies.size());
        List<MovieCover> visible = movies.subList(from, to);

        for (MovieCover cover : visible) {
            coversPanel.add(cover);
        }

        for (int i = visible.size(); i < VISIBLE; i++) {
            JPanel vacio = new JPanel();
            vacio.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
            vacio.setOpaque(false);
            coversPanel.add(vacio);
        }

        updateArrows();
        coversPanel.revalidate();
        coversPanel.repaint();
    }

    private void updateArrows() {
        prevArrow.setEnabled(currentPage > 0);
        nextArrow.setEnabled(currentPage < totalPages() - 1);
    }
}
