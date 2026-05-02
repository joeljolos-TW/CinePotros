package entidades;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Ricardo
 */
public class Pelicula {

    private ObjectId id;
    private String titulo;
    private int duracion; // minutos
    private String genero;

    public Pelicula() {}

    public Pelicula(ObjectId id, String titulo, int duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("titulo", titulo)
                .append("duracion", duracion)
                .append("genero", genero);
    }

    public static Pelicula fromDocument(Document doc) {
        return new Pelicula(
                doc.getObjectId("_id"),
                doc.getString("titulo"),
                doc.getInteger("duracion"),
                doc.getString("genero")
        );
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}