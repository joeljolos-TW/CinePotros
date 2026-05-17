package Control;

import DAO.BoletoDAO;
import DAO.FuncionDAO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import org.bson.types.ObjectId;

public class ControlFactory {
    public static IControlEntidades<BoletoDTO> getBoletoControl() {
        FuncionDAO dao = new FuncionDAO();
        return new ControlEntidades<>(
                new BoletoDAO(),
                dto -> convertirBoletoDtoAEntidad(dto),
                ent -> convertirBoletoEntidadADto(ent, dao),
                idStr -> new ObjectId(idStr)
        );
    }

    public static IControlEntidades<FuncionDTO> getFuncionControl(){
        return new ControlEntidades<>(
                new FuncionDAO(),
                dto -> convertirFuncionDTOAEntidad(dto),
                ent -> convertirFuncionEntidadADTO(ent),
                idStr -> new ObjectId(idStr)
        );
    }

    // -------------------- MAPEOS---------------------------

    //   BOLETO
    private static BoletoMongoEntidad convertirBoletoDtoAEntidad(BoletoDTO dto) {
        if (dto == null) return null;

        BoletoMongoEntidad entidad = new BoletoMongoEntidad();

        if (dto.getId() != null && !dto.getId().trim().isEmpty()) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        if (dto.getIdFuncion() != null && !dto.getIdFuncion().trim().isEmpty()) {
            entidad.setFuncion(new ObjectId(dto.getIdFuncion()));
        }
        entidad.setNumAsiento(dto.getNumAsiento());
        entidad.setTotal(dto.getTotal());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    private static BoletoDTO convertirBoletoEntidadADto(BoletoMongoEntidad entidad, FuncionDAO funcionDAO) {
        if (entidad == null) return null;

        BoletoDTO dto = new BoletoDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setNumAsiento(entidad.getNumAsiento());
        dto.setTotal(entidad.getTotal());
        dto.setEstado(entidad.getEstado());

        if (entidad.getFuncion() != null) {
            FuncionMongoEntidad funcion = funcionDAO.obtenerPorId(entidad.getFuncion());

            if (funcion != null) {
                dto.setSala(funcion.getSala() != null ? funcion.getSala().toString() : "");
                dto.setPelicula(funcion.getPelicula() != null ? funcion.getPelicula().toString() : "");
                dto.setFecha(funcion.getFecha());
                dto.setHora(funcion.getHora());
            }
        }

        return dto;
    }

    //   FUNCION
    private static FuncionMongoEntidad convertirFuncionDTOAEntidad(FuncionDTO dto){
        if(dto == null) return null;

        FuncionMongoEntidad entidad = new FuncionMongoEntidad();

        if (dto.getId() != null && !dto.getId().trim().isEmpty()) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        if(dto.getIdPelicula() != null && !dto.getIdPelicula().trim().isEmpty()){
            entidad.setPelicula(new ObjectId(dto.getIdPelicula()));
        }

        entidad.setFecha(dto.getFecha());
        entidad.setHora(dto.getHora());

        if(dto.getSalaFuncion() != null && !dto.getSalaFuncion().trim().isEmpty()){
            entidad.setSala(new ObjectId(dto.getSalaFuncion()));
        }

        entidad.setIdioma(dto.getIdioma());
        entidad.setPrecio(dto.getPrecio());

        return entidad;
    }

    private static FuncionDTO convertirFuncionEntidadADTO(FuncionMongoEntidad entidad){
        if(entidad == null) return null;

        FuncionDTO dto = new FuncionDTO();

        if(entidad.getId() != null){
            dto.setId(entidad.getId().toString());
        }

        dto.setFecha(entidad.getFecha());
        dto.setHora(entidad.getHora());

        if(entidad.getSala() != null){
            dto.setSalaFuncion(entidad.getSala().toString());
        }

        if(entidad.getPelicula() != null){
            dto.setIdPelicula(entidad.getPelicula().toString());
        }

        dto.setPrecio(entidad.getPrecio());
        dto.setIdioma(entidad.getIdioma());

        return dto;
    }
}