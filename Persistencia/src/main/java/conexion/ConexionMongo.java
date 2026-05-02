package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author Ricardo
 */
public class ConexionMongo {
    private static ConexionMongo instancia;
    private MongoClient mongoClient;
    private MongoDatabase baseDatos;

    private ConexionMongo() {
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost:27017");

        MongoClientSettings configuracion = MongoClientSettings.builder()
                .applyConnectionString(cadenaConexion)
                .codecRegistry(pojoCodecRegistry)
                .build();

        this.mongoClient = MongoClients.create(configuracion);
        this.baseDatos = mongoClient.getDatabase("cinepotros_db");
    }

    public static ConexionMongo getInstancia() {
        if (instancia == null) {
            instancia = new ConexionMongo();
        }
        return instancia;
    }

    public MongoDatabase getBaseDatos() {
        return baseDatos;
    }
    
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
