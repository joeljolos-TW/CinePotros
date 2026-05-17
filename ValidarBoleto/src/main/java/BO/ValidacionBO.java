package BO;

import DAO.BoletoDAO;
import DAO.FuncionDAO;
import DTO.ValidacionDTO;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import itson.dominio.EstadoBoleto;

import java.time.LocalDate;

public class ValidacionBO {
    private ValidacionDTO dto;
    private BoletoDAO boletoDAO;
    private FuncionDAO funcionDAO;

    public ValidacionBO() {
        this.boletoDAO = new BoletoDAO();
        this.funcionDAO = new FuncionDAO();
    }

    public ValidacionBO(ValidacionDTO dto) {
        this.dto = dto;
        this.boletoDAO = new BoletoDAO();
        this.funcionDAO = new FuncionDAO();
    }

    public ValidacionDTO escanearBoleto(){
        BoletoMongoEntidad boleto = boletoDAO.obtenerPorId(dto.getIdBoleto());
        if(boleto.getEstado() == EstadoBoleto.PENDIENTE && laFechaEsCorrecta(boleto)){
            boleto.setEstado(EstadoBoleto.ESCANEADO);
            boletoDAO.actualizar(boleto);
            return new ValidacionDTO(true, dto.getIdBoleto());
        }
        String razon;
        if(boleto.getEstado() == EstadoBoleto.ESCANEADO){
            razon = "El boleto ya ha sido usado";
        }else if(boleto.getEstado() == EstadoBoleto.CANCELADO){
            razon = "El boleto ha sido cancelado";
        }else{
            razon = "La funcion no es el dia de hoy";
        }
        return new ValidacionDTO(razon, dto.getIdBoleto(), false);
    }

    private boolean laFechaEsCorrecta(BoletoMongoEntidad boleto){
        FuncionMongoEntidad funcion = funcionDAO.obtenerPorId(boleto.getFuncion());
        LocalDate hoy = LocalDate.now();
        LocalDate fechaFuncion = LocalDate.parse(funcion.getFecha());
        return hoy.isEqual(fechaFuncion);
    }

    public ValidacionDTO getDto() {
        return dto;
    }

    public void setDto(ValidacionDTO dto) {
        this.dto = dto;
    }
}
