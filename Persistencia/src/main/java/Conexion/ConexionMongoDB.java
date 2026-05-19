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
 * Clase Singleton encargada de gestionar la conexión a MongoDB.
 * Garantiza que solo exista una instancia del cliente de MongoDB
 * durante toda la ejecución de la aplicación.
 *
 */
public class ConexionMongoDB {

    private static ConexionMongoDB instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    /**
     * Constructor privado que inicializa el cliente de MongoDB con el
     * registro de codecs POJO y la cadena de conexión a localhost.
     * Solo se ejecuta una vez gracias al patrón Singleton.
     */
    private ConexionMongoDB() {
        if (mongoClient == null) {
            CodecProvider proveedorPojo = PojoCodecProvider.builder()
                    .automatic(true)
                    .register("itson.dominio")
                    .register("entidadesMongo")
                    .build();
            CodecRegistry registroCodecs = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(proveedorPojo));
            MongoClientSettings configuracion = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                    .codecRegistry(registroCodecs)
                    .build();
            this.mongoClient = MongoClients.create(configuracion);
            this.database = mongoClient.getDatabase("CinePotro_DB");
        }
    }

    /**
     * Retorna la única instancia de ConexionMongoDB.
     * Si no existe, la crea. Método sincronizado para garantizar
     * seguridad en entornos multihilo.
     *
     * @return la instancia única de {@code ConexionMongoDB}.
     */
    public static synchronized ConexionMongoDB getInstance() {
        if (instancia == null) {
            instancia = new ConexionMongoDB();
        }
        return instancia;
    }

    /**
     * Retorna la base de datos {@code CinePotro_DB} para realizar
     * operaciones sobre sus colecciones.
     *
     * @return la instancia de MongoDatabase conectada a CinePotro_DB.
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Cierra la conexión con el cliente de MongoDB liberando los recursos.
     * Se recomienda llamar este método al finalizar la aplicación.
     */
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}