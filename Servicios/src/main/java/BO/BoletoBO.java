/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.BoletoDAO;
import DAO.FuncionDAO;
import DAO.PeliculaDAO;
import DAO.SalaDAO;
import DTOs.BoletoDTO;
import adaptadores.BoletoNegocioAdapter;
import adaptadores.BoletoPersistenciaAdapter;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import entidadesMongo.PeliculaMongoEntidad;
import entidadesMongo.SalaMongoEntidad;
import excepcion.NegocioException;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 * BO encargado de obtener boletos y convertirlos a DTOs para la presentación.
 *
 * @author Jazmin
 */
public class BoletoBO implements IBoletoBO {

    private final BoletoDAO boletoDAO;
    private final FuncionDAO funcionDAO;
    private final PeliculaDAO peliculaDAO;
    private final SalaDAO salaDAO;
    private final BoletoPersistenciaAdapter persistenciaAdapter;
    private final BoletoNegocioAdapter negocioAdapter;

    public BoletoBO() {
        this.boletoDAO = new BoletoDAO();
        this.funcionDAO = new FuncionDAO();
        this.peliculaDAO = new PeliculaDAO();
        this.salaDAO = new SalaDAO();
        this.persistenciaAdapter = new BoletoPersistenciaAdapter();
        this.negocioAdapter = new BoletoNegocioAdapter();
    }

    /**
     * Obtiene todos los boletos y los convierte en DTOS para mostrarlos en
     * pantalla
     *
     * @return
     */
    @Override
    public List<BoletoDTO> obtenerTodos() {
        List<BoletoMongoEntidad> boletos = boletoDAO.obtenerTodos();
        List<FuncionMongoEntidad> funciones = new ArrayList<>();
        List<PeliculaMongoEntidad> peliculas = new ArrayList<>();
        List<SalaMongoEntidad> salas = new ArrayList<>();

        for (BoletoMongoEntidad boleto : boletos) {
            FuncionMongoEntidad funcion = funcionDAO.obtenerPorId(boleto.getFuncion());
            funciones.add(funcion);
            peliculas.add(peliculaDAO.obtenerPorId(funcion.getPelicula()));
            salas.add(salaDAO.obtenerPorId(funcion.getSala()));
        }
        return negocioAdapter.convertirListaADTO(boletos, funciones, peliculas, salas);
    }

    @Override
    public BoletoDTO obtenerPorId(String id) throws NegocioException {
        try {
            BoletoMongoEntidad boleto = boletoDAO.obtenerPorId(persistenciaAdapter.convertirStringAObjectId(id));
            if (boleto == null) {
                return null;
            }
            FuncionMongoEntidad funcion = funcionDAO.obtenerPorId(boleto.getFuncion());
            PeliculaMongoEntidad pelicula = peliculaDAO.obtenerPorId(funcion.getPelicula());
            SalaMongoEntidad sala = salaDAO.obtenerPorId(funcion.getSala());
            return negocioAdapter.convertirADTO(boleto, funcion, pelicula,sala);

        } catch (PersistenciaException e) {
            throw new NegocioException("Erro al obtener el boleto");
        }
    }

}
