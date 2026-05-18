package Control;

import DAO.BoletoDAO;
import DAO.FuncionDAO;
import DAO.PeliculaDAO;
import DAO.SalaDAO;
import DTOs.BoletoDTO;
import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import DTOs.SalaDTO;
import entidadesMongo.BoletoMongoEntidad;
import entidadesMongo.FuncionMongoEntidad;
import entidadesMongo.PeliculaMongoEntidad;
import entidadesMongo.SalaMongoEntidad;
import itson.dominio.Sala;
import itson.dominio.TipoSala;
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

    public static IControlEntidades<FuncionDTO> getFuncionControl() {
        return new ControlEntidades<>(
                new FuncionDAO(),
                dto -> convertirFuncionDTOAEntidad(dto),
                ent -> convertirFuncionEntidadADTO(ent),
                idStr -> new ObjectId(idStr)
        );
    }

    public static IControlEntidades<SalaDTO> getSalaControl() {
        return new ControlEntidades<>(
                new SalaDAO(),
                dto -> convertirSalaDTOAEntidad(dto),
                ent -> convertirSalaEntidadADTO(ent),
                idStr -> new ObjectId(idStr)
        );
    }

    public static IControlEntidades<PeliculaDTO> getPeliculaControl() {
        return new ControlEntidades<>(
                new PeliculaDAO(),
                dto -> convertirPeliculaDTOAEntidad(dto),
                ent -> convertirPeliculaEntidadADTO(ent),
                idStr -> new ObjectId(idStr)
        );
    }

    // -------------------- MAPEOS---------------------------
    //   BOLETO
    private static BoletoMongoEntidad convertirBoletoDtoAEntidad(BoletoDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entidad == null) {
            return null;
        }

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
                dto.setIdFuncion(entidad.getFuncion().toString());
                dto.setFecha(funcion.getFecha());
                dto.setHora(funcion.getHora());

                // ── AGREGAR ESTAS LÍNEAS ──
                if (funcion.getPelicula() != null) {
                    PeliculaDAO peliculaDAO = new PeliculaDAO();
                    PeliculaMongoEntidad pelicula = peliculaDAO.obtenerPorId(funcion.getPelicula());
                    if (pelicula != null) {
                        dto.setTituloPelicula(pelicula.getTitulo());
                    }
                }
                // ─────────────────────────
            }
        }

        return dto;
    }

    //   FUNCION
    private static FuncionMongoEntidad convertirFuncionDTOAEntidad(FuncionDTO dto) {
        if (dto == null) {
            return null;
        }

        FuncionMongoEntidad entidad = new FuncionMongoEntidad();

        if (dto.getId() != null && !dto.getId().trim().isEmpty()) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        if (dto.getIdPelicula() != null && !dto.getIdPelicula().trim().isEmpty()) {
            entidad.setPelicula(new ObjectId(dto.getIdPelicula()));
        }

        entidad.setFecha(dto.getFecha());
        entidad.setHora(dto.getHora());

        if (dto.getSalaFuncion() != null && !dto.getSalaFuncion().trim().isEmpty()) {
            entidad.setSala(new ObjectId(dto.getSalaFuncion()));
        }

        entidad.setIdioma(dto.getIdioma());
        entidad.setPrecio(dto.getPrecio());

        return entidad;
    }

    private static FuncionDTO convertirFuncionEntidadADTO(FuncionMongoEntidad entidad) {
        if (entidad == null) {
            return null;
        }

        FuncionDTO dto = new FuncionDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setFecha(entidad.getFecha());
        dto.setHora(entidad.getHora());

        if (entidad.getSala() != null) {
            dto.setSalaFuncion(entidad.getSala().toString());
        }

        if (entidad.getPelicula() != null) {
            dto.setIdPelicula(entidad.getPelicula().toString());
        }

        dto.setPrecio(entidad.getPrecio());
        dto.setIdioma(entidad.getIdioma());

        return dto;
    }

    private static SalaMongoEntidad convertirSalaDTOAEntidad(SalaDTO dto) {
        if (dto == null) {
            return null;
        }

        SalaMongoEntidad entidad = new SalaMongoEntidad();

        if (dto.getId() != null && !dto.getId().trim().isBlank()) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        switch (dto.getTipoSala()) {
            case "TRADICIONAL":
                entidad.setTipo(TipoSala.TRADICIONAL);
            case "VIP":
                entidad.setTipo(TipoSala.VIP);
            case "KIDS":
                entidad.setTipo(TipoSala.KIDS);
            default:
                entidad.setTipo(TipoSala.TRADICIONAL);
        }

        entidad.setCapacidad(dto.getCapacidad());

        return entidad;
    }

    private static SalaDTO convertirSalaEntidadADTO(SalaMongoEntidad entidad) {
        if (entidad == null) {
            return null;
        }

        SalaDTO dto = new SalaDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setTipoSala(entidad.getTipo().toString());
        dto.setNombre(entidad.getNombre());
        dto.setCapacidad(entidad.getCapacidad());

        return dto;
    }

    private static PeliculaMongoEntidad convertirPeliculaDTOAEntidad(PeliculaDTO dto) {
        if (dto == null) {
            return null;
        }

        PeliculaMongoEntidad entidad = new PeliculaMongoEntidad();

        if (dto.getId() != null && !dto.getId().trim().isBlank()) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        entidad.setTitulo(dto.getTitulo());
        entidad.setClasificacion(dto.getClasificacion());
        entidad.setGenero(dto.getGenero());
        entidad.setDuracion(dto.getDuracion());
        entidad.setCategoria(dto.getCategoria());
        entidad.setImagen(dto.getImagen());

        return entidad;
    }

    private static PeliculaDTO convertirPeliculaEntidadADTO(PeliculaMongoEntidad entidad) {
        if (entidad == null) {
            return null;
        }

        PeliculaDTO dto = new PeliculaDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setTitulo(entidad.getTitulo());
        dto.setClasificacion(entidad.getClasificacion());
        dto.setGenero(entidad.getGenero());
        dto.setDuracion(entidad.getDuracion());
        dto.setCategoria(entidad.getCategoria());
        dto.setImagen(entidad.getImagen());

        return dto;
    }
}
