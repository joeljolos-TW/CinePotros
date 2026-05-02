/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

/**
 *
 * @author Jazmin
 */
public class Pelicula {
    private String id;
    private String titulo;
    private String clasificacion;
    private String genero;
    private int duracion;
    private String imagen;

    public Pelicula() {
    }

    public Pelicula(String id, String titulo, String clasificacion, String genero, int duracion, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.duracion = duracion;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
}
