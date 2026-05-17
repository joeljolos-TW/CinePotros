/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;
 
import itson.dominio.Promocion;
import itson.dominio.TipoPromocion;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
/**
 * DAO (Data Access Object) para la entidad Promocion.
 * Segun el Diagrama de Clases de Analisis: buscarPorCodigo, obtenerTodas, insertar.
 *
 * NOTA: Esta implementacion usa una lista en memoria (mock) porque el proyecto
 * aun no tiene conexion a base de datos configurada. Cuando integren MongoDB u
 * otra BD, solo deben reemplazar el cuerpo de cada metodo; la firma NO cambia.
 */
public class PromocionDAO {
 
    // -----------------------------------------------------------------------
    // Datos de prueba en memoria
    // -----------------------------------------------------------------------
    private static final List<Promocion> promociones = new ArrayList<>();
 
    static {
        // Cargamos algunas promociones de ejemplo para poder probar sin BD
        Calendar cal = Calendar.getInstance();
 
        // Promo vigente: 20% de descuento, vence en 30 dias
        cal.add(Calendar.DAY_OF_YEAR, 30);
        promociones.add(new Promocion("Promo20", 0.20, cal.getTime(), TipoPromocion.PORCENTAJE));
 
        // Promo vigente: $50 fijos de descuento, vence en 7 dias
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        promociones.add(new Promocion("Des50", 50.0, cal.getTime(), TipoPromocion.MONTO_FIJO));
 
        // Promo VENCIDA para probar el flujo alternativo
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        promociones.add(new Promocion("Vencida", 0.10, cal.getTime(), TipoPromocion.PORCENTAJE));
    }
 
    // -----------------------------------------------------------------------
    // Metodos del DAO
    // -----------------------------------------------------------------------
 
    /**
     * Busca una promocion por su codigo unico.
     * @param codigo el codigo ingresado por el usuario
     * @return la Promocion encontrada, o null si no existe
     */
    public Promocion buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        for (Promocion p : promociones) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }
 
    /**
     * Regresa todas las promociones almacenadas.
     * @return lista de promociones
     */
    public List<Promocion> obtenerTodas() {
        return new ArrayList<>(promociones);
    }
 
    /**
     * Inserta una nueva promocion.
     * @param promo la promocion a guardar
     */
    public void insertar(Promocion promo) {
        if (promo != null) {
            promociones.add(promo);
        }
    }
}
