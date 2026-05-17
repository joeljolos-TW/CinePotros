package daos;

import entidades.Sala;
import org.bson.types.ObjectId;
import java.util.List;

public interface ISalaDAO {
    boolean agregarSala(Sala sala);
    List<Sala> obtenerTodasLasSalas();
    Sala buscarSalaPorId(ObjectId id);
    boolean actualizarSala(Sala sala);
    boolean eliminarSala(ObjectId id);
}
