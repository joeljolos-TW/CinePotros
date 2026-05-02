package DTOs;

import javax.swing.*;

public class SeleccionPeliculaDTO {
    private String name;
    private ImageIcon cover;

    public SeleccionPeliculaDTO(String name, ImageIcon cover) {
        this.name = name;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getCover() {
        return cover;
    }

    public void setCover(ImageIcon cover) {
        this.cover = cover;
    }
}
