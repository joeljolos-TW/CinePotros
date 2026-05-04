package Generador;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QRBoundary {

    private static final String API_URL = "https://api.qrserver.com/v1/create-qr-code/";

    public byte[] obtenerQR(itson.dominio.QR qr) throws Exception {

        String url = API_URL
                + "?size" + qr.getAncho() + "x" +  qr.getAlto()
                + "&data" + URLEncoder.encode(qr.getContenido(), "UTF-8");

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();

        if (status != 200) {
            throw new Exception("Error de API. CODIGO: " + status);
        }

        try (InputStream is = connection.getInputStream()) {
            return is.readAllBytes();
        }
    }

    public void guardarQr(itson.dominio.QR qr, String rutaSalida) throws Exception {
        byte[] imagen =  obtenerQR(qr);
        Files.write(Paths.get(rutaSalida), imagen);
    }
}
