package BO;

import DAO.BoletoDAO;
import DTO.ValidacionDTO;
import itson.dominio.Boleto;

public class ValidacionBO {
    private ValidacionDTO dto;
    private BoletoDAO boletoDAO;

    public ValidacionBO(ValidacionDTO dto) {
        this.dto = dto;
        this.boletoDAO = new BoletoDAO();
    }

    public boolean marcarComoUsado(){
        Boleto boleto = boletoDAO.obtenerPorId(dto.getIdBoleto());
        if(boleto.)
    }

    public ValidacionDTO getDto() {
        return dto;
    }

    public void setDto(ValidacionDTO dto) {
        this.dto = dto;
    }
}
