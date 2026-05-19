package DAO;

import Conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import itson.dominio.Asiento;
import itson.dominio.EstadoAsiento;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de integración para AsientoDAO.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Antes de cada prueba inserta una función de prueba con asientos
 * y la elimina al terminar.
 */
public class AsientoDAOTest {

    private AsientoDAO asientoDAO;
    private ObjectId idFuncion;
    private MongoCollection<Document> coleccion;

    /**
     * Crea una función de prueba en MongoDB con asientos iniciales
     * antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        asientoDAO = new AsientoDAO();
        coleccion = ConexionMongoDB.getInstance().getDatabase().getCollection("funcion");

        // Insertar función de prueba con un asiento inicial
        idFuncion = new ObjectId();
        Document funcionPrueba = new Document("_id", idFuncion)
                .append("fecha", "2026-06-01")
                .append("hora", "18:00")
                .append("asientos", List.of(
                        new Document("fila", "A").append("numero", 1).append("estado", "DISPONIBLE"),
                        new Document("fila", "A").append("numero", 2).append("estado", "DISPONIBLE"),
                        new Document("fila", "B").append("numero", 1).append("estado", "OCUPADO")
                ));
        coleccion.insertOne(funcionPrueba);
    }

    /**
     * Elimina la función de prueba de MongoDB después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        coleccion.deleteOne(eq("_id", idFuncion));
    }

    /**
     * Verifica que obtenerPorFuncion retorna todos los asientos
     * de una función existente.
     */
    @Test
    void testObtenerPorFuncion_retornaTodosLosAsientos() {
        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(idFuncion);
        assertEquals(3, asientos.size());
    }

    /**
     * Verifica que obtenerPorFuncion retorna lista vacía
     * si la función no existe.
     */
    @Test
    void testObtenerPorFuncion_funcionNoExiste() {
        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(new ObjectId());
        assertTrue(asientos.isEmpty());
    }

    /**
     * Verifica que obtenerDisponibles retorna solo los asientos
     * con estado DISPONIBLE.
     */
    @Test
    void testObtenerDisponibles_soloRetornaDisponibles() {
        List<Asiento> disponibles = asientoDAO.obtenerDisponibles(idFuncion);
        assertEquals(2, disponibles.size());
        assertTrue(disponibles.stream().allMatch(a -> a.getEstado() == EstadoAsiento.DISPONIBLE));
    }

    /**
     * Verifica que agregarAsiento agrega correctamente un asiento
     * al arreglo de la función.
     */
    @Test
    void testAgregarAsiento_agregaCorrectamente() {
        Asiento nuevo = new Asiento("C", 1, EstadoAsiento.DISPONIBLE);
        asientoDAO.agregarAsiento(idFuncion, nuevo);

        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(idFuncion);
        assertEquals(4, asientos.size());
        assertTrue(asientos.stream().anyMatch(a -> a.getFila().equals("C") && a.getNumero() == 1));
    }

    /**
     * Verifica que inicializarAsientos reemplaza todos los asientos
     * de una función con la nueva lista.
     */
    @Test
    void testInicializarAsientos_reemplazaAsientos() {
        List<Asiento> nuevos = List.of(
                new Asiento("D", 1, EstadoAsiento.DISPONIBLE),
                new Asiento("D", 2, EstadoAsiento.DISPONIBLE)
        );
        asientoDAO.inicializarAsientos(idFuncion, nuevos);

        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(idFuncion);
        assertEquals(2, asientos.size());
        assertEquals("D", asientos.get(0).getFila());
    }

    /**
     * Verifica que actualizarEstado cambia el estado de un asiento
     * específico correctamente.
     */
    @Test
    void testActualizarEstado_cambiaEstadoCorrectamente() {
        boolean resultado = asientoDAO.actualizarEstado(idFuncion, "A", 1, EstadoAsiento.OCUPADO);
        assertTrue(resultado);

        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(idFuncion);
        Asiento a1 = asientos.stream()
                .filter(a -> a.getFila().equals("A") && a.getNumero() == 1)
                .findFirst()
                .orElse(null);
        assertNotNull(a1);
        assertEquals(EstadoAsiento.OCUPADO, a1.getEstado());
    }

    /**
     * Verifica que actualizarEstado retorna false si el asiento
     * no existe en la función.
     */
    @Test
    void testActualizarEstado_asientoNoExiste() {
        boolean resultado = asientoDAO.actualizarEstado(idFuncion, "Z", 99, EstadoAsiento.OCUPADO);
        assertFalse(resultado);
    }

    /**
     * Verifica que eliminarAsiento elimina correctamente un asiento
     * del arreglo de la función.
     */
    @Test
    void testEliminarAsiento_eliminaCorrectamente() {
        asientoDAO.eliminarAsiento(idFuncion, "A", 1);

        List<Asiento> asientos = asientoDAO.obtenerPorFuncion(idFuncion);
        assertEquals(2, asientos.size());
        assertTrue(asientos.stream().noneMatch(a -> a.getFila().equals("A") && a.getNumero() == 1));
    }
}