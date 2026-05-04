package itson.dominio;

public class QR {
    private String Contenido;
    private int ancho;
    private int alto;

    public QR(String contenido, int ancho, int alto) {
        Contenido = contenido;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
