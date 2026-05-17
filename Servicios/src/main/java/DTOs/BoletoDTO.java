package DTOs;

public class BoletoDTO {
    private String id;
    private String folioQR;
    private boolean usado;

    public BoletoDTO() {}

    public BoletoDTO(String id, String folioQR, boolean usado) {
        this.id = id;
        this.folioQR = folioQR;
        this.usado = usado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFolioQR() { return folioQR; }
    public void setFolioQR(String folioQR) { this.folioQR = folioQR; }
    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}
