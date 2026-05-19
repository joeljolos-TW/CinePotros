/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;
 
import entidadesMongo.BoletoMongoEntidad;
import itson.dominio.EstadoBoleto;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
/**
 * Pruebas de integración para BoletoDAO.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Antes de cada prueba inserta un boleto de prueba y lo elimina al terminar.
 */
public class FuncionDAOTest {
 
    private BoletoDAO boletoDAO;
    private BoletoMongoEntidad boletoPrueba;
 
    /**
     * Inserta un boleto de prueba en MongoDB antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        boletoDAO = new BoletoDAO();
 
        boletoPrueba = new BoletoMongoEntidad();
        boletoPrueba.setFuncion(new ObjectId());
        boletoPrueba.setNumAsiento(List.of("A1", "A2"));
        boletoPrueba.setTotal(144.0);
        boletoPrueba.setEstado(EstadoBoleto.PENDIENTE);
 
        boletoDAO.insertar(boletoPrueba);
    }
 
    /**
     * Elimina el boleto de prueba de MongoDB después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        if (boletoPrueba != null && boletoPrueba.getId() != null) {
            boletoDAO.eliminar(boletoPrueba.getId());
        }
    }
 
    /**
     * Verifica que insertar guarda el boleto y asigna un ID válido.
     */
    @Test
    void testInsertar_asignaId() {
        assertNotNull(boletoPrueba.getId(), "El ID no debe ser nulo después de insertar");
    }
 
    /**
     * Verifica que obtenerTodos retorna al menos el boleto insertado.
     */
    @Test
    void testObtenerTodos_retornaAlMenosUno() {
        List<BoletoMongoEntidad> boletos = boletoDAO.obtenerTodos();
        assertFalse(boletos.isEmpty());
    }
 
    /**
     * Verifica que obtenerPorId retorna el boleto correcto.
     */
    @Test
    void testObtenerPorId_retornaBoletoCorrector() {
        BoletoMongoEntidad encontrado = boletoDAO.obtenerPorId(boletoPrueba.getId());
        assertNotNull(encontrado);
        assertEquals(boletoPrueba.getId(), encontrado.getId());
        assertEquals(EstadoBoleto.PENDIENTE, encontrado.getEstado());
    }
 
    /**
     * Verifica que obtenerPorId retorna null si el ID no existe.
     */
    @Test
    void testObtenerPorId_noExiste() {
        BoletoMongoEntidad resultado = boletoDAO.obtenerPorId(new ObjectId());
        assertNull(resultado);
    }
 
    /**
     * Verifica que obtenerPorFuncion retorna los boletos de una función.
     */
    @Test
    void testObtenerPorFuncion_retornaBoletosDelaFuncion() {
        List<BoletoMongoEntidad> boletos = boletoDAO.obtenerPorFuncion(boletoPrueba.getFuncion());
        assertFalse(boletos.isEmpty());
        assertTrue(boletos.stream().allMatch(b -> b.getFuncion().equals(boletoPrueba.getFuncion())));
    }
 
    /**
     * Verifica que obtenerPorFuncion retorna lista vacía
     * si no hay boletos para esa función.
     */
    @Test
    void testObtenerPorFuncion_funcionSinBoletos() {
        List<BoletoMongoEntidad> boletos = boletoDAO.obtenerPorFuncion(new ObjectId());
        assertTrue(boletos.isEmpty());
    }
 
    /**
     * Verifica que actualizar modifica correctamente el estado del boleto.
     */
    @Test
    void testActualizar_modificaEstado() {
        boletoPrueba.setEstado(EstadoBoleto.CANCELADO);
        boolean resultado = boletoDAO.actualizar(boletoPrueba);
        assertTrue(resultado);
 
        BoletoMongoEntidad actualizado = boletoDAO.obtenerPorId(boletoPrueba.getId());
        assertEquals(EstadoBoleto.CANCELADO, actualizado.getEstado());
    }
 
    /**
     * Verifica que eliminar borra el boleto correctamente.
     */
    @Test
    void testEliminar_eliminaCorrectamente() {
        boolean resultado = boletoDAO.eliminar(boletoPrueba.getId());
        assertTrue(resultado);
 
        BoletoMongoEntidad eliminado = boletoDAO.obtenerPorId(boletoPrueba.getId());
        assertNull(eliminado);
 
        boletoPrueba = null; // para que el @AfterEach no intente eliminarlo de nuevo
    }
 
    /**
     * Verifica que  eliminar retorna false si el boleto no existe.
     */
    @Test
    void testEliminar_noExiste() {
        boolean resultado = boletoDAO.eliminar(new ObjectId());
        assertFalse(resultado);
    }
}
 