package Elements.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*; 

public class BillboardPanel extends JPanel {

    private SwitchPanel panelNavegacion;

    public BillboardPanel(SwitchPanel panelNavegacion) {
        this.panelNavegacion = panelNavegacion;
        setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirContenido(), BorderLayout.CENTER);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(Utileria.UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JLabel titulo = new JLabel("Cartelera");
        titulo.setFont(Utileria.UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(Utileria.UtilGeneral.TEXTO_PRINCIPAL);

        JLabel cine = new JLabel("Cinepolis Bella Vista");
        cine.setFont(Utileria.UtilGeneral.FUENTE_CUERPO);
        cine.setForeground(Utileria.UtilGeneral.TEXTO_SECUNDARIO);

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.add(titulo);
        panelIzquierdo.add(cine);

        encabezado.add(panelIzquierdo, BorderLayout.WEST);
        return encabezado;
    }

    private JScrollPane construirContenido() {
        JPanel contenido = new JPanel();
        contenido.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(null);
        contenido.add(construirSeccion("MÁS POPULARES", construirMasPopulares()));
        contenido.add(Box.createVerticalStrut(20));
        contenido.add(construirSeccion("TODAS LAS FUNCIONES", construirTodasFunciones()));
        contenido.add(Box.createVerticalStrut(20));
        contenido.add(construirSeccion("SALA KIDS", construirSalaKids()));

        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        return scroll;
    }

    private JPanel construirSeccion(String titulo, JPanel carrusel) {
        JPanel seccion = new JPanel();
        seccion.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);
        seccion.setLayout(new BoxLayout(seccion, BoxLayout.Y_AXIS));

        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filaTitulo.setBackground(Utileria.UtilGeneral.FONDO_PRINCIPAL);

        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(Utileria.UtilGeneral.FUENTE_SUBTITULO);
        etiqueta.setForeground(Utileria.UtilGeneral.TEXTO_PRINCIPAL);
        filaTitulo.add(etiqueta);

        seccion.add(filaTitulo);
        seccion.add(Box.createVerticalStrut(8));
        seccion.add(carrusel);
        carrusel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        carrusel.setPreferredSize(new Dimension(800, 420));
        return seccion;
    }

    private JPanel construirMasPopulares() {
        List<MovieCover> peliculas = new ArrayList<>();
        peliculas.add(new MovieCover("/images/avatarPoster.jpg", "Avatar 3",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/jurassicPoster.jpg", "Jurassic World",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/starWarsPoster.jpg", "Star Wars",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        return new MovieCarousel(peliculas);
    }

    private JPanel construirTodasFunciones() {
        List<MovieCover> peliculas = new ArrayList<>();
        peliculas.add(new MovieCover("/images/dramaPoster.jpg", "Drama",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/michaelPoster.jpg", "Michael",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/mummyPoster.jpg", "The Mummy",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        return new MovieCarousel(peliculas);
    }

    private JPanel construirSalaKids() {
        List<MovieCover> peliculas = new ArrayList<>();
        peliculas.add(new MovieCover("/images/hoppersPoster.jpg", "Hoppers",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/minionsPoster.jpg", "Minions",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        peliculas.add(new MovieCover("/images/marioPoster.jpg", "Mario Bros",
                () -> panelNavegacion.changePanel("seleccionFuncion")));
        return new MovieCarousel(peliculas);
    }

}
