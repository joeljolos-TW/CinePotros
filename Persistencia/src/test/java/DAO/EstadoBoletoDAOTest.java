/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import entidadesMongo.BoletoMongoEntidad;
import exception.PersistenciaException;
import itson.dominio.EstadoBoleto;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/*
 * Pruebas de integración para EstadoBoletoDAO.
 * Se conecta a MongoDB real (localhost:27017 / CinePotro_DB).
 * Cada prueba inserta un boleto de prueba y lo elimina al terminar.
 */
public class EstadoBoletoDAOTest {
 
    private static EstadoBoletoDAO estadoBoletoDAO;
    private static BoletoDAO boletoDAO;
    private static ObjectId idBoleto;
 
    /**
     * Inicializa los DAOs y crea un boleto de prueba en MongoDB
     * antes de ejecutar las pruebas.
     */
    @BeforeAll
    static void setUp() {
        estadoBoletoDAO = new EstadoBoletoDAO();
        boletoDAO = new BoletoDAO();
 
        // Insertar boleto de prueba con estado PENDIENTE
        BoletoMongoEntidad boleto = new BoletoMongoEntidad();
        boleto.setFuncion(new ObjectId());
        boleto.setNumAsiento(List.of("A1", "A2"));
        boleto.setTotal(144.0);
        boleto.setEstado(EstadoBoleto.PENDIENTE);
 
        BoletoMongoEntidad insertado = boletoDAO.insertar(boleto);
        idBoleto = insertado.getId();
    }
 
    /**
     * Elimina el boleto de prueba de la base de datos
     * al finalizar todas las pruebas.
     */
    @AfterAll
    static void tearDown() {
        if (idBoleto != null) {
            boletoDAO.eliminar(idBoleto);
        }
    }
 
    /**
     * Verifica que se puede actualizar el estado de un boleto existente
     * de PENDIENTE a CANCELADO correctamente.
     */
    @Test
    void testActualizarEstado_exitoso() throws PersistenciaException {
        boolean resultado = estadoBoletoDAO.actualizarEstado(
                idBoleto.toHexString(), EstadoBoleto.CANCELADO
        );
        assertTrue(resultado, "Debería retornar true al actualizar el estado correctamente");
 
        BoletoMongoEntidad actualizado = boletoDAO.obtenerPorId(idBoleto);
        assertNotNull(actualizado);
        assertEquals(EstadoBoleto.CANCELADO, actualizado.getEstado());
    }
 
    /**
     * Verifica que al pasar un ID inexistente se lanza {@link PersistenciaException}
     * con el mensaje "Boleto no encontrado."
     */
    @Test
    void testActualizarEstado_boletoNoExiste() {
        String idInexistente = new ObjectId().toHexString();
 
        PersistenciaException excepcion = assertThrows(PersistenciaException.class, () ->
                estadoBoletoDAO.actualizarEstado(idInexistente, EstadoBoleto.CANCELADO)
        );
 
        assertEquals("Boleto no encontrado.", excepcion.getMessage());
    }
 
    /**
     * Verifica que al pasar un ID con formato inválido
     * se lanza PersistenciaException.
     */
    @Test
    void testActualizarEstado_idInvalido() {
        assertThrows(PersistenciaException.class, () ->
                estadoBoletoDAO.actualizarEstado("id-invalido", EstadoBoleto.CANCELADO)
        );
    }
}