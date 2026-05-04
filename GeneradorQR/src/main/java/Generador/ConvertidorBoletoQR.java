package Generador;

import com.google.gson.Gson;
import itson.dominio.Boleto;
import itson.dominio.QR;
public class ConvertidorBoletoQR {
    private Gson gson = GsonUtil.crear();
    private QRBoundary qrBoundary = new QRBoundary();

    /**
     * Metodo para generar QR segun los datos que almacena el boleto
     * @param boleto
     * @param rutaSalida
     * @throws Exception
     */
    public void generarQRDeBoleto(Boleto boleto, String rutaSalida) throws Exception {
        try{
            String json = gson.toJson(boleto);
            System.out.println("Json del boleto:\n" + json);

            QR qr = new QR(json, 400,400);
            qrBoundary.guardarQr(qr, rutaSalida);

            System.out.println("QR generado en: " + rutaSalida);
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
