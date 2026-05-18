package Elements.Panels.ValidacionBoleto;

import Control.ControlFactory;
import Control.IControlEntidades;
import DTO.ValidacionDTO;
import DTOs.BoletoDTO;
import Elements.Buttons.GenericButton;
import Elements.Panels.SwitchPanel;
import Elements.Panels.Refreshable;
import Elements.Utileria.UtilGeneral;
import Mediator.PanelMediator;
import excepcion.NegocioException;
import itson.dominio.EstadoBoleto;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BoletoValidadoPanel extends JPanel implements Refreshable {
    private PanelMediator panelNavegacion;
    private ValidacionDTO validacionDTO;
    private JPanel contenedorCentral;
    private IControlEntidades<BoletoDTO> controlerBoleto;

    public BoletoValidadoPanel() {
        this.panelNavegacion = SwitchPanel.getInstance();
        this.controlerBoleto = ControlFactory.getBoletoControl();
        setBackground(UtilGeneral.FONDO_PRINCIPAL);
        setLayout(new BorderLayout());

        add(construirEncabezado(), BorderLayout.NORTH);
        add(construirPieDePagina(), BorderLayout.SOUTH);

        contenedorCentral = new JPanel(new GridBagLayout());
        contenedorCentral.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JLabel titulo = new JLabel("Validación del boleto");
        titulo.setFont(UtilGeneral.FUENTE_TITULO);
        titulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);
        encabezado.add(titulo, BorderLayout.WEST);

        return encabezado;
    }

    private void actualizarContenidoDinamico() throws NegocioException {
        contenedorCentral.removeAll();

        if (validacionDTO == null) {
            contenedorCentral.add(new JLabel("No hay datos de validación disponibles."));
            return;
        }

        String titulo;
        String descripcion;
        ImageIcon img = null;

        if (validacionDTO.isVerificado()) {
            titulo = "Boleto válido";
            descripcion = "Se ha usado el boleto con éxito";
            URL url = getClass().getResource("/images/check.png");
            if (url != null) img = new ImageIcon(url);
        } else {
            titulo = "Boleto inválido";
            descripcion = validacionDTO.getRazon() != null ? validacionDTO.getRazon() : "Razón desconocida";
            URL url = getClass().getResource("/images/cross.png");
            if (url != null) img = new ImageIcon(url);
        }

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(UtilGeneral.FUENTE_SUBTITULO);
        lblTitulo.setForeground(UtilGeneral.TEXTO_PRINCIPAL);

        JLabel lblDescripcion = new JLabel(descripcion, SwingConstants.CENTER);
        lblDescripcion.setFont(UtilGeneral.FUENTE_CUERPO);
        lblDescripcion.setForeground(UtilGeneral.TEXTO_SECUNDARIO);

        JLabel imagen = new JLabel();
        if (img != null) {
            imagen.setIcon(img);
        } else {
            imagen.setText("[Imagen no encontrada]");
            imagen.setForeground(Color.GRAY);
        }
        imagen.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        contenedorCentral.add(lblTitulo, gbc);

        gbc.gridy = 1;
        contenedorCentral.add(imagen, gbc);

        gbc.gridy = 2;
        contenedorCentral.add(lblDescripcion, gbc);

        BoletoDTO boleto = controlerBoleto.obtenerPorId(validacionDTO.getIdBoleto().toString());
        boleto.setEstado(EstadoBoleto.ESCANEADO);
        controlerBoleto.actualizar(boleto);
    }

    private JPanel construirPieDePagina() {
        JPanel pieDePagina = new JPanel();
        pieDePagina.setBackground(UtilGeneral.FONDO_ENCABEZADO);
        GenericButton btnContinuar = new GenericButton("Continuar", false, 20, 200, 50, Color.WHITE, new Color(85, 60, 230), Color.RED);
        btnContinuar.addActionListener(e -> panelNavegacion.changePanel("cartelera")); // Volver a cartelera al terminar el flujo

        pieDePagina.add(btnContinuar);
        return pieDePagina;
    }

    @Override
    public void onShow(Object object) {
        if (object instanceof ValidacionDTO vDTO) {
            this.validacionDTO = vDTO;
        }
        try {
            actualizarContenidoDinamico();
        }catch (NegocioException e){
            e.printStackTrace();
        }

        revalidate();
        repaint();
    }
}