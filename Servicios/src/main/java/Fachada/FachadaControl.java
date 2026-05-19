package Fachada;

import BO.AsientoBO;
import BO.BoletoBO;
import BO.CancelacionBO;
import BO.FuncionBO;
import BO.IAsientoBO;
import BO.IBoletoBO;
import BO.ICancelacionBO;
import BO.IFuncionBO;
import BO.IPeliculaBO;
import BO.PeliculaBO;
import DTOs.AsientoDTO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.SeleccionPeliculaDTO;
import excepcion.NegocioException;
import itson.dominio.QR;
import java.util.List;

/**
 * Implementación de la fachada del subsistema Control. Concentra todas las
 * operaciones de negocio que Presentacion necesita y delega cada una al BO
 * correspondiente.
 *
 * Presentacion solo conoce IFachadaControl — nunca instancia un BO
 * directamente.
 *
 * @author CinePotros
 */
public class FachadaControl implements IFachadaControl {

    private final IPeliculaBO peliculaBO;
    private final IFuncionBO funcionBO;
    private final IAsientoBO asientoBO;
    private final IBoletoBO boletoBO;
    private final ICancelacionBO cancelacionBO;

    public FachadaControl() {
        this.peliculaBO = new PeliculaBO();
        this.funcionBO = new FuncionBO();
        this.asientoBO = new AsientoBO();
        this.boletoBO = new BoletoBO();
        this.cancelacionBO = new CancelacionBO();
    }

    // ─── Pelicula ─────────────────────────────────────────────────────────
    @Override
    public List<SeleccionPeliculaDTO> obtenerPeliculas() throws NegocioException {
        return peliculaBO.obtenerTodos();
    }

    // ─── Funcion ──────────────────────────────────────────────────────────
    @Override
    public List<FuncionDTO> obtenerFuncionesPorPelicula(String idPelicula) throws NegocioException {
        return funcionBO.obtenerPorPelicula(idPelicula);
    }

    // ─── Asiento ──────────────────────────────────────────────────────────
    @Override
    public List<AsientoDTO> obtenerAsientosPorFuncion(String idFuncion) throws NegocioException {
        return asientoBO.obtenerPorFuncion(idFuncion);
    }

    @Override
    public void ocuparAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException {
        asientoBO.ocuparAsientos(idFuncion, asientos);
    }

    @Override
    public void liberarAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException {
        asientoBO.liberarAsientos(idFuncion, asientos);
    }

    // ─── Boleto ───────────────────────────────────────────────────────────
    @Override
    public List<BoletoDTO> obtenerBoletos() throws NegocioException {
        return boletoBO.obtenerTodos();
    }

    @Override
    public BoletoDTO obtenerBoletoPorId(String id) throws NegocioException {
        return boletoBO.obtenerPorId(id);
    }

    @Override
    public void cancelarBoleto(String id) throws NegocioException {
        cancelacionBO.cancelarBoleto(id);
    }

    // ─── CodigoQr ─────────────────────────────────────────────────────────
    @Override
    public byte[] generarQR(String contenido, int ancho, int alto) throws Exception {
        // QRBoundary vive en el módulo GeneradorQR; si ese módulo está en el
        // classpath puedes instanciarlo aquí. Si no, delégalo al módulo
        // GeneradorQR desde Presentacion usando su propia fachada.
        QR qr = new QR(contenido, ancho, alto);
        // return new Generador.QRBoundary().obtenerQR(qr);
        throw new UnsupportedOperationException(
                "Conectar con GeneradorQR.FachadaQR para generar el código.");
    }
}
