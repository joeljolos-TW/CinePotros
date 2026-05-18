/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.BoletoDAO;
import DAO.FuncionDAO;
import DAO.PeliculaDAO;
import DTOs.BoletoDTO;
import adaptadores.BoletoNegocioAdapter;
import adaptadores.BoletoPersistenciaAdapter;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import entidadesMongo.PeliculaMongoEntidad;
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
    private final BoletoPersistenciaAdapter persistenciaAdapter;
    private final BoletoNegocioAdapter negocioAdapter;

    public BoletoBO() {
        this.boletoDAO = new BoletoDAO();
        this.funcionDAO = new FuncionDAO();
        this.peliculaDAO = new PeliculaDAO();
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

        for (BoletoMongoEntidad boleto : boletos) {
            FuncionMongoEntidad funcion = funcionDAO.obtenerPorId(boleto.getFuncion());
            funciones.add(funcion); // puede ser null, el adapter lo maneja

            PeliculaMongoEntidad pelicula = null;
            if (funcion != null) {
                pelicula = peliculaDAO.obtenerPorId(funcion.getPelicula());
            }
            peliculas.add(pelicula);
        }
        return negocioAdapter.convertirListaADTO(boletos, funciones, peliculas);
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
            return negocioAdapter.convertirADTO(boleto, funcion, pelicula);

        } catch (PersistenciaException e) {
            throw new NegocioException("Erro al obtener el boleto");
        }
    }

}
