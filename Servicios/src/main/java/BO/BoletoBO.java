/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import daos.BoletoDAO;
import daos.FuncionDAO;
import daos.PeliculaDAO;
import daos.IBoletoDAO;
import daos.IFuncionDAO;
import daos.IPeliculaDAO;
import DTOs.BoletoDTO;
import entidades.Boleto;
import entidades.Funcion;
import entidades.Pelicula;
import excepcion.NegocioException;
import exception.PersistenciaException;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class BoletoBO implements IBoletoBO {
    private final IBoletoDAO boletoDAO;
    private final IFuncionDAO funcionDAO;
    private final IPeliculaDAO peliculaDAO;

    public BoletoBO() {
        this.boletoDAO = new BoletoDAO();
        this.funcionDAO = new FuncionDAO();
        this.peliculaDAO = new PeliculaDAO();
    }
    
    @Override
    public List<BoletoDTO> obtenerTodos() {
        List<Boleto> boletos = boletoDAO.obtenerTodosLosBoletos();
        List<BoletoDTO> dtos = new ArrayList<>();
        
        for (Boleto boleto : boletos) {
            BoletoDTO dto = new BoletoDTO(
                boleto.getId() != null ? boleto.getId().toHexString() : null,
                boleto.getFolioQR(),
                boleto.isUsado()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public BoletoDTO obtenerPorId(String id) throws NegocioException {
        try {
            // El DAO solo tiene buscarPorFolioQR o obtenerTodos, así que
            // adaptamos si es necesario o buscamos todos.
            // Para mantener compatibilidad con IBoletoBO, buscamos manual o lanzamos excepcion
            throw new NegocioException("Método no implementado en el DAO directo para ID de Boleto");
        } catch (Exception e) {
            throw new NegocioException("Error al obtener el boleto: " + e.getMessage());  
        }
    }
}
