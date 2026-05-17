package DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class FuncionDTO {
    private String id;
    private LocalDate fecha;
    private LocalTime hora;
    private SalaDTO sala;

    public FuncionDTO() {}

    public FuncionDTO(String id, LocalDate fecha, LocalTime hora, SalaDTO sala) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.sala = sala;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public SalaDTO getSala() { return sala; }
    public void setSala(SalaDTO sala) { this.sala = sala; }
}
