package Conexion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase Singleton para manejar la conexión a MongoDB.
 */
public class ConexionMongoDB {
    private static ConexionMongoDB instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private ConexionMongoDB() {
        String connectionString = "mongodb://localhost:27017";
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase("CinePotro_DB");
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
