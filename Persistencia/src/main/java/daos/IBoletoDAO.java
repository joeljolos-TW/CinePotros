package daos;

import entidades.Boleto;
import org.bson.types.ObjectId;
import java.util.List;

public interface IBoletoDAO {
    boolean guardarBoleto(Boleto boleto);
    Boleto buscarPorFolioQR(String folioQR);
    boolean marcarComoUsado(String folioQR);
    List<Boleto> obtenerTodosLosBoletos();
    boolean actualizarBoleto(Boleto boleto);
    boolean eliminarBoleto(ObjectId id);
}
