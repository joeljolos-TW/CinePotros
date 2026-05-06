package Test;
import Generador.ConvertidorBoletoQR;
import Generador.GsonUtil;
import itson.dominio.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ConvertidorBoletoQR convertidorBoletoQR = new ConvertidorBoletoQR();
        Pelicula pelicula = new Pelicula(
                new ObjectId("69f7e4cc6915e34dc99ac87c"),
                "La Familia del barrio",
                "C",
                "Comedia",
                105,
                "/LaFamiliaDelBarrio.png"
        );
        Sala sala = new Sala(
                new ObjectId("69f7e6d8a347bef02f35798d"),
                "Sala2",
                TipoSala.TRADICIONAL,
                35
        );

        Funcion funcion = new Funcion(
                new ObjectId("69f7e49a047d2bbcec19bee7"),
                pelicula.getId(),
                LocalDate.now(),
                LocalTime.now(),
                sala.getId(),
                "Español",
                75.0
        );
        String[] asientos = {"A-19","A-20"};
        Boleto boleto = new Boleto(
                new ObjectId("69f7e448236d399400a0930f"),
                funcion.getId(),
                Arrays.asList(asientos),
                funcion.getPrecio()*2
        );
        convertidorBoletoQR.generarQRDeBoleto(boleto, "GeneradorQR/src/main/resources/boleto_qr-1.png");
    }
}
