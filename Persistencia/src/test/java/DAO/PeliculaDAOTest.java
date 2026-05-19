/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;
 
import entidadesMongo.PeliculaMongoEntidad;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
/**
 * Pruebas de integración para {@link PeliculaDAO}.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Antes de cada prueba inserta una película de prueba y la elimina al terminar.
 */
public class PeliculaDAOTest {
 
    private PeliculaDAO peliculaDAO;
    private PeliculaMongoEntidad peliculaPrueba;
 
    /**
     * Inserta una película de prueba en MongoDB antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        peliculaDAO = new PeliculaDAO();
 
        peliculaPrueba = new PeliculaMongoEntidad();
        peliculaPrueba.setTitulo("Pelicula de prueba");
        peliculaPrueba.setClasificacion("B");
        peliculaPrueba.setGenero("Acción");
        peliculaPrueba.setDuracion(120);
        peliculaPrueba.setCategoria("Estreno");
        peliculaPrueba.setImagen("/prueba.png");
 
        peliculaDAO.insertar(peliculaPrueba);
    }
 
    /**
     * Elimina la película de prueba de MongoDB después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        if (peliculaPrueba != null && peliculaPrueba.getId() != null) {
            peliculaDAO.eliminar(peliculaPrueba.getId());
        }
    }
 
    /**
     * Verifica que {@code insertar} guarda la película y asigna un ID válido.
     */
    @Test
    void testInsertar_asignaId() {
        assertNotNull(peliculaPrueba.getId(), "El ID no debe ser nulo después de insertar");
    }
 
    /**
     * Verifica que {@code obtenerTodos} retorna al menos la película insertada.
     */
    @Test
    void testObtenerTodos_retornaAlMenosUna() {
        List<PeliculaMongoEntidad> peliculas = peliculaDAO.obtenerTodos();
        assertFalse(peliculas.isEmpty());
    }
 
    /**
     * Verifica que {@code obtenerPorId} retorna la película correcta.
     */
    @Test
    void testObtenerPorId_retornaPeliculaCorrecta() {
        PeliculaMongoEntidad encontrada = peliculaDAO.obtenerPorId(peliculaPrueba.getId());
        assertNotNull(encontrada);
        assertEquals(peliculaPrueba.getId(), encontrada.getId());
        assertEquals("Pelicula de prueba", encontrada.getTitulo());
        assertEquals("Acción", encontrada.getGenero());
    }
 
    /**
     * Verifica que {@code obtenerPorId} retorna null si el ID no existe.
     */
    @Test
    void testObtenerPorId_noExiste() {
        PeliculaMongoEntidad resultado = peliculaDAO.obtenerPorId(new ObjectId());
        assertNull(resultado);
    }
 
    /**
     * Verifica que {@code actualizar} modifica correctamente los datos de la película.
     */
    @Test
    void testActualizar_modificaDatos() {
        peliculaPrueba.setTitulo("Titulo actualizado");
        peliculaPrueba.setDuracion(150);
        boolean resultado = peliculaDAO.actualizar(peliculaPrueba);
        assertTrue(resultado);
 
        PeliculaMongoEntidad actualizada = peliculaDAO.obtenerPorId(peliculaPrueba.getId());
        assertEquals("Titulo actualizado", actualizada.getTitulo());
        assertEquals(150, actualizada.getDuracion());
    }
 
    /**
     * Verifica que {@code eliminar} borra la película correctamente.
     */
    @Test
    void testEliminar_eliminaCorrectamente() {
        boolean resultado = peliculaDAO.eliminar(peliculaPrueba.getId());
        assertTrue(resultado);
 
        PeliculaMongoEntidad eliminada = peliculaDAO.obtenerPorId(peliculaPrueba.getId());
        assertNull(eliminada);
 
        peliculaPrueba = null;
    }
 
    /**
     * Verifica que {@code eliminar} retorna false si la película no existe.
     */
    @Test
    void testEliminar_noExiste() {
        boolean resultado = peliculaDAO.eliminar(new ObjectId());
        assertFalse(resultado);
    }
}