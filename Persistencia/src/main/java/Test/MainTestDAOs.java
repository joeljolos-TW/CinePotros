package Test;

import Conexion.ConexionMongoDB;
import DAO.*;
import itson.dominio.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MainTestDAOs {

    public static void main(String[] args) {
        try {
            probarPeliculaDAO();
            probarFuncionDAO();
            probarBoletoDAO();
            probarUsuarioDAO();
            probarAsientoDAO();
        } finally {
            ConexionMongoDB.getInstance().cerrarConexion();
            System.out.println("\n✔ Conexión cerrada.");
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PELICULA
    // ─────────────────────────────────────────────────────────────────────────
    static void probarPeliculaDAO() {
        System.out.println("\n══════════════ PeliculaDAO ══════════════");
        PeliculaDAO dao = new PeliculaDAO();

        // INSERT
        Pelicula nueva = new Pelicula(null, "Avatar 3", "B15", "Ciencia Ficción", 192, "/avatar3.png");
        dao.insertar(nueva);
        System.out.println("INSERT  → id asignado: " + nueva.getId());

        // GET BY ID
        Pelicula encontrada = dao.obtenerPorId(nueva.getId());
        System.out.println("GET ID  → " + encontrada.getTitulo() + " | " + encontrada.getGenero());

        // GET ALL
        List<Pelicula> todas = dao.obtenerTodas();
        System.out.println("GET ALL → " + todas.size() + " película(s) en BD");

        // GET POR GENERO
        List<Pelicula> cf = dao.obtenerPorGenero("Ciencia Ficción");
        System.out.println("GET GEN → " + cf.size() + " película(s) de Ciencia Ficción");

        // UPDATE
        encontrada.setClasificacion("A");
        boolean actualizado = dao.actualizar(encontrada);
        System.out.println("UPDATE  → modificado: " + actualizado);

        // DELETE
        boolean eliminado = dao.eliminar(nueva.getId());
        System.out.println("DELETE  → eliminado: " + eliminado);
        System.out.println("GET ALL post-delete → " + dao.obtenerTodas().size() + " película(s)");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FUNCION
    // ─────────────────────────────────────────────────────────────────────────
    static void probarFuncionDAO() {
        System.out.println("\n══════════════ FuncionDAO ══════════════");
        FuncionDAO dao = new FuncionDAO();

        ObjectId idPelicula = new ObjectId();
        ObjectId idSala     = new ObjectId();

        // INSERT
        Funcion nueva = new Funcion(null, idPelicula, LocalDate.now(), LocalTime.of(18, 30), idSala, "Español", 95.0);
        dao.insertar(nueva);
        System.out.println("INSERT  → id asignado: " + nueva.getId());

        // GET BY ID
        Funcion encontrada = dao.obtenerPorId(nueva.getId());
        System.out.println("GET ID  → " + encontrada.getFecha() + " " + encontrada.getHora() + " | $" + encontrada.getPrecio());

        // GET ALL
        System.out.println("GET ALL → " + dao.obtenerTodas().size() + " función(es) en BD");

        // GET POR PELICULA
        List<Funcion> porPeli = dao.obtenerPorPelicula(idPelicula);
        System.out.println("GET PEL → " + porPeli.size() + " función(es) para esa película");

        // GET POR PELICULA Y FECHA
        List<Funcion> porFecha = dao.obtenerPorPeliculaYFecha(idPelicula, LocalDate.now());
        System.out.println("GET FEC → " + porFecha.size() + " función(es) hoy");

        // UPDATE
        encontrada.setPrecio(110.0);
        System.out.println("UPDATE  → modificado: " + dao.actualizar(encontrada));

        // DELETE
        System.out.println("DELETE  → eliminado: " + dao.eliminar(nueva.getId()));
        System.out.println("GET ALL post-delete → " + dao.obtenerTodas().size() + " función(es)");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // BOLETO
    // ─────────────────────────────────────────────────────────────────────────
    static void probarBoletoDAO() {
        System.out.println("\n══════════════ BoletoDAO ══════════════");
        BoletoDAO dao = new BoletoDAO();

        ObjectId idFuncion = new ObjectId();

        // INSERT
        Boleto nuevo = new Boleto(null, idFuncion, List.of("A-1", "A-2"), 190.0, false);
        dao.insertar(nuevo);
        System.out.println("INSERT  → id asignado: " + nuevo.getId());

        // GET BY ID
        Boleto encontrado = dao.obtenerPorId(nuevo.getId());
        System.out.println("GET ID  → asientos: " + encontrado.getNumAsiento() + " | total: $" + encontrado.getTotal());

        // GET ALL
        System.out.println("GET ALL → " + dao.obtenerTodos().size() + " boleto(s) en BD");

        // GET POR FUNCION
        List<Boleto> porFuncion = dao.obtenerPorFuncion(idFuncion);
        System.out.println("GET FUN → " + porFuncion.size() + " boleto(s) para esa función");

        // UPDATE
        encontrado.setTotal(200.0);
        System.out.println("UPDATE  → modificado: " + dao.actualizar(encontrado));

        // DELETE
        System.out.println("DELETE  → eliminado: " + dao.eliminar(nuevo.getId()));
        System.out.println("GET ALL post-delete → " + dao.obtenerTodos().size() + " boleto(s)");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // USUARIO
    // ─────────────────────────────────────────────────────────────────────────
    static void probarUsuarioDAO() {
        System.out.println("\n══════════════ UsuarioDAO ══════════════");
        UsuarioDAO dao = new UsuarioDAO();

        // INSERT
        Usuario nuevo = new Usuario(null, "empleado01", "1234");
        dao.insertar(nuevo);
        System.out.println("INSERT  → id asignado: " + nuevo.getId());

        // GET BY ID
        Usuario porId = dao.obtenerPorId(nuevo.getId());
        System.out.println("GET ID  → " + porId.getNombreUsuario());

        // GET ALL
        System.out.println("GET ALL → " + dao.obtenerTodos().size() + " usuario(s) en BD");

        // GET POR NOMBRE
        Usuario porNombre = dao.obtenerPorNombreUsuario("empleado01");
        System.out.println("GET NOM → encontrado: " + (porNombre != null));

        // AUTENTICAR — correcto
        Usuario autOk = dao.autenticar("empleado01", "1234");
        System.out.println("AUTH OK → " + (autOk != null ? "acceso concedido" : "acceso denegado"));

        // AUTENTICAR — incorrecto
        Usuario autFail = dao.autenticar("empleado01", "wrong");
        System.out.println("AUTH FL → " + (autFail != null ? "acceso concedido" : "acceso denegado"));

        // UPDATE
        porId.setContrasena("nuevaClave99");
        System.out.println("UPDATE  → modificado: " + dao.actualizar(porId));

        // DELETE
        System.out.println("DELETE  → eliminado: " + dao.eliminar(nuevo.getId()));
        System.out.println("GET ALL post-delete → " + dao.obtenerTodos().size() + " usuario(s)");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ASIENTO  (viven dentro de Funcion)
    // ─────────────────────────────────────────────────────────────────────────
    static void probarAsientoDAO() {
        System.out.println("\n══════════════ AsientoDAO ══════════════");
        AsientoDAO asientoDAO = new AsientoDAO();
        FuncionDAO funcionDAO = new FuncionDAO();

        // Primero creamos una función real para alojar los asientos
        Funcion funcion = new Funcion(null, new ObjectId(), LocalDate.now(),
                LocalTime.of(20, 0), new ObjectId(), "Español", 85.0);
        funcionDAO.insertar(funcion);
        ObjectId idFuncion = funcion.getId();
        System.out.println("SETUP   → función creada con id: " + idFuncion);

        // INICIALIZAR asientos de la función (5 asientos de prueba)
        List<Asiento> asientos = List.of(
                new Asiento("A", 1, EstadoAsiento.DISPONIBLE),
                new Asiento("A", 2, EstadoAsiento.DISPONIBLE),
                new Asiento("A", 3, EstadoAsiento.OCUPADO),
                new Asiento("B", 1, EstadoAsiento.DISPONIBLE),
                new Asiento("B", 2, EstadoAsiento.DISPONIBLE)
        );
        asientoDAO.inicializarAsientos(idFuncion, asientos);
        System.out.println("INIT    → " + asientos.size() + " asientos inicializados");

        // GET POR FUNCION
        List<Asiento> recuperados = asientoDAO.obtenerPorFuncion(idFuncion);
        System.out.println("GET FUN → " + recuperados.size() + " asiento(s) recuperados");
        for (Asiento a : recuperados) {
            System.out.println("         " + a.getFila() + "-" + a.getNumero() + " → " + a.getEstado());
        }

        // GET DISPONIBLES
        List<Asiento> disponibles = asientoDAO.obtenerDisponibles(idFuncion);
        System.out.println("GET DIS → " + disponibles.size() + " asiento(s) disponibles");

        // UPDATE ESTADO — seleccionamos A-1
        boolean actualizado = asientoDAO.actualizarEstado(idFuncion, "A", 1, EstadoAsiento.SELECCIONADO);
        System.out.println("UPDATE  → A-1 marcado SELECCIONADO: " + actualizado);

        // Verificar cambio
        Asiento a1 = asientoDAO.obtenerPorFuncion(idFuncion)
                .stream().filter(a -> a.getFila().equals("A") && a.getNumero() == 1)
                .findFirst().orElse(null);
        System.out.println("VERIFY  → A-1 estado ahora: " + (a1 != null ? a1.getEstado() : "no encontrado"));

        // AGREGAR un asiento extra
        asientoDAO.agregarAsiento(idFuncion, new Asiento("C", 1, EstadoAsiento.DISPONIBLE));
        System.out.println("ADD     → total ahora: " + asientoDAO.obtenerPorFuncion(idFuncion).size() + " asiento(s)");

        // ELIMINAR asiento C-1
        asientoDAO.eliminarAsiento(idFuncion, "C", 1);
        System.out.println("DELETE  → total tras eliminar C-1: " + asientoDAO.obtenerPorFuncion(idFuncion).size() + " asiento(s)");

        // Limpieza — eliminar la función de prueba
        funcionDAO.eliminar(idFuncion);
        System.out.println("CLEAN   → función de prueba eliminada");
    }
}
