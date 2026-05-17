package DTOs;

public class SalaDTO {
    private String tipo;
    private String nombre;

    public SalaDTO() {}

    public SalaDTO(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
