/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;
 
import entidadesMongo.SalaMongoEntidad;
import itson.dominio.TipoSala;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
/**
 * Pruebas de integración para {@link SalaDAO}.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Antes de cada prueba inserta una sala de prueba y la elimina al terminar.
 */
public class SalaDAOTest {
 
    private SalaDAO salaDAO;
    private SalaMongoEntidad salaPrueba;
 
    /**
     * Inserta una sala de prueba en MongoDB antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        salaDAO = new SalaDAO();
 
        salaPrueba = new SalaMongoEntidad();
        salaPrueba.setNombre("Sala Test");
        salaPrueba.setTipo(TipoSala.TRADICIONAL);
        salaPrueba.setCapacidad(40);
 
        salaDAO.insertar(salaPrueba);
    }
 
    /**
     * Elimina la sala de prueba de MongoDB después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        if (salaPrueba != null && salaPrueba.getId() != null) {
            salaDAO.eliminar(salaPrueba.getId());
        }
    }
 
    /**
     * Verifica que {@code insertar} guarda la sala y asigna un ID válido.
     */
    @Test
    void testInsertar_asignaId() {
        assertNotNull(salaPrueba.getId(), "El ID no debe ser nulo después de insertar");
    }
 
    /**
     * Verifica que {@code obtenerTodos} retorna al menos la sala insertada.
     */
    @Test
    void testObtenerTodos_retornaAlMenosUna() {
        List<SalaMongoEntidad> salas = salaDAO.obtenerTodos();
        assertFalse(salas.isEmpty());
    }
 
    /**
     * Verifica que {@code obtenerPorId} retorna la sala correcta.
     */
    @Test
    void testObtenerPorId_retornaSalaCorrecta() {
        SalaMongoEntidad encontrada = salaDAO.obtenerPorId(salaPrueba.getId());
        assertNotNull(encontrada);
        assertEquals(salaPrueba.getId(), encontrada.getId());
        assertEquals("Sala Test", encontrada.getNombre());
        assertEquals(TipoSala.TRADICIONAL, encontrada.getTipo());
        assertEquals(40, encontrada.getCapacidad());
    }
 
    /**
     * Verifica que {@code obtenerPorId} retorna null si el ID no existe.
     */
    @Test
    void testObtenerPorId_noExiste() {
        SalaMongoEntidad resultado = salaDAO.obtenerPorId(new ObjectId());
        assertNull(resultado);
    }
 
    /**
     * Verifica que {@code actualizar} modifica correctamente los datos de la sala.
     */
    @Test
    void testActualizar_modificaDatos() {
        salaPrueba.setNombre("Sala VIP Test");
        salaPrueba.setTipo(TipoSala.VIP);
        salaPrueba.setCapacidad(20);
        boolean resultado = salaDAO.actualizar(salaPrueba);
        assertTrue(resultado);
 
        SalaMongoEntidad actualizada = salaDAO.obtenerPorId(salaPrueba.getId());
        assertEquals("Sala VIP Test", actualizada.getNombre());
        assertEquals(TipoSala.VIP, actualizada.getTipo());
        assertEquals(20, actualizada.getCapacidad());
    }
 
    /**
     * Verifica que {@code eliminar} borra la sala correctamente.
     */
    @Test
    void testEliminar_eliminaCorrectamente() {
        boolean resultado = salaDAO.eliminar(salaPrueba.getId());
        assertTrue(resultado);
 
        SalaMongoEntidad eliminada = salaDAO.obtenerPorId(salaPrueba.getId());
        assertNull(eliminada);
 
        salaPrueba = null;
    }
 
    /**
     * Verifica que {@code eliminar} retorna false si la sala no existe.
     */
    @Test
    void testEliminar_noExiste() {
        boolean resultado = salaDAO.eliminar(new ObjectId());
        assertFalse(resultado);
    }
}