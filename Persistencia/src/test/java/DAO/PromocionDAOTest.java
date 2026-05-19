/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import itson.dominio.Promocion;
import itson.dominio.TipoPromocion;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sonia
 */
public class PromocionDAOTest {

    // ─── obtenerTodas ─────────────────────────────────────────────────────────
    @Test
    public void testObtenerTodas_noEsNula() {
        PromocionDAO dao = new PromocionDAO();
        List<Promocion> result = dao.obtenerTodas();
        assertNotNull(result);
    }

    @Test
    public void testObtenerTodas_tieneElementos() {
        PromocionDAO dao = new PromocionDAO();
        List<Promocion> result = dao.obtenerTodas();
        assertFalse(result.isEmpty());
    }

    // ─── buscarPorCodigo ──────────────────────────────────────────────────────
    @Test
    public void testBuscarPorCodigo_existente() {
        PromocionDAO dao = new PromocionDAO();
        Promocion result = dao.buscarPorCodigo("Promo20");
        assertNotNull(result);
    }

    @Test
    public void testBuscarPorCodigo_caseInsensitive() {
        PromocionDAO dao = new PromocionDAO();
        Promocion result = dao.buscarPorCodigo("promo20");
        assertNotNull(result);
    }

    @Test
    public void testBuscarPorCodigo_inexistente() {
        PromocionDAO dao = new PromocionDAO();
        Promocion result = dao.buscarPorCodigo("NOEXISTE");
        assertNull(result);
    }

    @Test
    public void testBuscarPorCodigo_nulo() {
        PromocionDAO dao = new PromocionDAO();
        Promocion result = dao.buscarPorCodigo(null);
        assertNull(result);
    }

    @Test
    public void testBuscarPorCodigo_vacio() {
        PromocionDAO dao = new PromocionDAO();
        Promocion result = dao.buscarPorCodigo("   ");
        assertNull(result);
    }

    // ─── insertar ─────────────────────────────────────────────────────────────
    @Test
    public void testInsertar_aumentaLista() {
        PromocionDAO dao = new PromocionDAO();
        int antes = dao.obtenerTodas().size();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        dao.insertar(new Promocion("NUEVA10", 0.10, cal.getTime(), TipoPromocion.PORCENTAJE));

        int despues = dao.obtenerTodas().size();
        assertEquals(antes + 1, despues);
    }

    @Test
    public void testInsertar_seEncuentraDespues() {
        PromocionDAO dao = new PromocionDAO();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 15);
        dao.insertar(new Promocion("BUSCA50", 50.0, cal.getTime(), TipoPromocion.MONTO_FIJO));

        assertNotNull(dao.buscarPorCodigo("BUSCA50"));
    }

    @Test
    public void testInsertar_nulo_noLanzaExcepcion() {
        PromocionDAO dao = new PromocionDAO();
        assertDoesNotThrow(() -> dao.insertar(null));
    }
}
