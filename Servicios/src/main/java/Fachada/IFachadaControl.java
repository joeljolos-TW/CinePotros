package Fachada;

import DTOs.AsientoDTO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.SeleccionPeliculaDTO;
import excepcion.NegocioException;
import java.util.List;

/**
 * Interfaz de la fachada del subsistema Control.
 * Punto de entrada único para que Presentacion acceda a toda
 * la lógica de negocio sin conocer los controles internos.
 *
 * @author CinePotros
 */
public interface IFachadaControl {

    // ─── Pelicula ─────────────────────────────────────────────────────────
    List<SeleccionPeliculaDTO> obtenerPeliculas() throws NegocioException;

    // ─── Funcion ──────────────────────────────────────────────────────────
    List<FuncionDTO> obtenerFuncionesPorPelicula(String idPelicula) throws NegocioException;

    // ─── Asiento ──────────────────────────────────────────────────────────
    List<AsientoDTO> obtenerAsientosPorFuncion(String idFuncion) throws NegocioException;
    void ocuparAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;
    void liberarAsientos(String idFuncion, List<AsientoDTO> asientos) throws NegocioException;

    // ─── Boleto ───────────────────────────────────────────────────────────
    List<BoletoDTO> obtenerBoletos() throws NegocioException;
    BoletoDTO obtenerBoletoPorId(String id) throws NegocioException;
    void cancelarBoleto(String id) throws NegocioException;

    // ─── CodigoQr ─────────────────────────────────────────────────────────
    byte[] generarQR(String contenido, int ancho, int alto) throws Exception;
}
