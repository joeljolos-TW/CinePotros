package Elements.Panels;
/**
 * Interfaz que deben implementar los paneles que requieren
 * actualizarse al ser mostrados mediante el sistema de navegación.
 * Permite recibir datos externos en el momento de la transición entre paneles.
 */
public interface Refreshable {
    void onShow(Object object);
}
