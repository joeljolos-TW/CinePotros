/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import entidadesMongo.PagoMongoEntidad;
import itson.dominio.EstadoPago;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de integración para PagoDAO.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Antes de cada prueba inserta un pago de prueba y lo elimina al terminar.
 */
public class PagoDAOTest {
 
    private PagoDAO pagoDAO;
    private PagoMongoEntidad pagoPrueba;
 
    /**
     * Inserta un pago de prueba en MongoDB antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        pagoDAO = new PagoDAO();
 
        pagoPrueba = new PagoMongoEntidad();
        pagoPrueba.setBoleto(new ObjectId());
        pagoPrueba.setMonto(144.0);
        pagoPrueba.setEmpleado(new ObjectId());
        pagoPrueba.setEstado(EstadoPago.PENDIENTE);
 
        pagoDAO.insertar(pagoPrueba);
    }
 
    /**
     * Elimina el pago de prueba de MongoDB después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        if (pagoPrueba != null && pagoPrueba.getId() != null) {
            pagoDAO.eliminar(pagoPrueba.getId());
        }
    }
 
    /**
     * Verifica que insertar guarda el pago y asigna un ID válido.
     */
    @Test
    void testInsertar_asignaId() {
        assertNotNull(pagoPrueba.getId(), "El ID no debe ser nulo después de insertar");
    }
 
    /**
     * Verifica que obtenerTodos retorna al menos el pago insertado.
     */
    @Test
    void testObtenerTodos_retornaAlMenosUno() {
        List<PagoMongoEntidad> pagos = pagoDAO.obtenerTodos();
        assertFalse(pagos.isEmpty());
    }
 
    /**
     * Verifica que obtenerPorId retorna el pago correcto.
     */
    @Test
    void testObtenerPorId_retornaPagoCorrector() {
        PagoMongoEntidad encontrado = pagoDAO.obtenerPorId(pagoPrueba.getId());
        assertNotNull(encontrado);
        assertEquals(pagoPrueba.getId(), encontrado.getId());
        assertEquals(EstadoPago.PENDIENTE, encontrado.getEstado());
        assertEquals(144.0, encontrado.getMonto());
    }
 
    /**
     * Verifica que obtenerPorId retorna null si el ID no existe.
     */
    @Test
    void testObtenerPorId_noExiste() {
        PagoMongoEntidad resultado = pagoDAO.obtenerPorId(new ObjectId());
        assertNull(resultado);
    }
 
    /**
     * Verifica que eliminar borra el pago correctamente.
     */
    @Test
    void testEliminar_eliminaCorrectamente() {
        boolean resultado = pagoDAO.eliminar(pagoPrueba.getId());
        assertTrue(resultado);
 
        PagoMongoEntidad eliminado = pagoDAO.obtenerPorId(pagoPrueba.getId());
        assertNull(eliminado);
 
        pagoPrueba = null;
    }
 
    /**
     * Verifica que eliminar retorna false si el pago no existe.
     */
    @Test
    void testEliminar_noExiste() {
        boolean resultado = pagoDAO.eliminar(new ObjectId());
        assertFalse(resultado);
    }
}