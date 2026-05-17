package DTOs;

public class SeleccionPeliculaDTO {
    private String id;
    private String imagen;
    private String titulo;
    private String categoria;

    public SeleccionPeliculaDTO() {}

    public SeleccionPeliculaDTO(String id, String imagen, String titulo, String categoria) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
