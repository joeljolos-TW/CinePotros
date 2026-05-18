package Elements.Panels.ValidacionBoleto;

import DTO.ValidacionDTO;
import Elements.Buttons.GenericButton;
import Elements.Panels.SwitchPanel;
import Elements.Utileria.UtilGeneral;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BoletoValidadoPanel extends JPanel {
    private SwitchPanel panelNavegacion;
    private ValidacionDTO validacionDTO;

    public BoletoValidadoPanel(ValidacionDTO validacionDTO) {
        this.validacionDTO = validacionDTO;
        this.panelNavegacion = SwitchPanel.getInstance();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirContenido(), BorderLayout.CENTER);
        add(construirPieDePagina(), BorderLayout.SOUTH);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JLabel titulo = new JLabel("Validacion del boleto");
        titulo.setFont(UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);

        return encabezado;
    }

    private JPanel construirContenido(){
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBackground(UtilGeneral.FONDO_ENCABEZADO);

        String titulo;
        String descripcion;
        ImageIcon img;
//        if(validacionDTO.isVerificado()){
        if(true){
            titulo = "Boleto valido";
            descripcion = "Se ha usado el boleto";
            URL url = getClass().getResource("/images/check.png");
            img = new ImageIcon(url);
        } else {
            titulo = "Boleto invalido";
            descripcion = validacionDTO.getRazon();
            URL url = getClass().getResource("/images/cross.png");
            img = new ImageIcon(url);
        }

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(UtilGeneral.FUENTE_SUBTITULO);
        lblTitulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);

        JLabel lblDescripcion = new JLabel(descripcion, SwingConstants.CENTER);
        lblDescripcion.setFont(UtilGeneral.FUENTE_CUERPO);
        lblDescripcion.setForeground(UtilGeneral.TEXTO_SECUNDARIO);

        JLabel imagen = new JLabel();
        imagen.setIcon(img);
        imagen.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        contenido.add(lblTitulo, gbc);

        gbc.gridy = 1;
        contenido.add(imagen, gbc);

        gbc.gridy = 2;
        contenido.add(lblDescripcion, gbc);

        return contenido;
    }

    private JPanel construirPieDePagina(){
        JPanel pieDePagina = new JPanel();
        pieDePagina.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        GenericButton btnContinuar = new GenericButton("Continuar", false, 20, 200, 50, Color.WHITE, new Color(85, 60, 230), Color.RED);
        btnContinuar.addActionListener(e -> panelNavegacion.changePanel("generacionBoleto"));

        pieDePagina.add(btnContinuar);

        return pieDePagina;
    }
}
