package Conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase Singleton para manejar la conexión a MongoDB.
 */
public class ConexionMongoDB {

    private static ConexionMongoDB instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private ConexionMongoDB() {
        if (mongoClient == null) {
            CodecProvider proveedorPojo = PojoCodecProvider.builder()
                    .automatic(true)
                    .register("itson.dominio")
                    .register("entidadesMongo")
                    .build();
            CodecRegistry registroCodecs = CodecRegistries.fromRegistries(// Combina el registro de codecs por defecto de MongoDB con el nuevo proveedor de POJOs
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(proveedorPojo));
            //MongoClientSettings para encapsular la conexion y el registro de codecs y una creacion de cliente limpia
            MongoClientSettings configuracion = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                    .codecRegistry(registroCodecs)
                    .build();
            //crea una instancia unica del cliente
            this.mongoClient = MongoClients.create(configuracion);
            this.database = mongoClient.getDatabase("CinePotro_DB");
        }

    }

    public static synchronized ConexionMongoDB getInstance() {
        if (instancia == null) {
            instancia = new ConexionMongoDB();
        }
        return instancia;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
