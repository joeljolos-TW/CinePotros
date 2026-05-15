package DTOs;


public class SeleccionPeliculaDTO {
    private String id;
    private String name;
    private String categoria;
    private String imagen;

    
    public SeleccionPeliculaDTO() {
    }

    public SeleccionPeliculaDTO(String id,String imagen ,String name,String categoria) {
        this.id = id;
        this.imagen = imagen;
        this.name = name;
        this.categoria = categoria;
       
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

   
}
