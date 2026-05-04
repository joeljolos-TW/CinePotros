package Generador;

import com.google.gson.*;
import org.bson.types.ObjectId;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;

public class GsonUtil {

    public static Gson crear() {
        return new GsonBuilder()
                .registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
                    public JsonElement serialize(ObjectId src, Type type, JsonSerializationContext ctx) {
                        return new JsonPrimitive(src.toHexString());
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    public JsonElement serialize(LocalDate src, Type type, JsonSerializationContext ctx) {
                        return new JsonPrimitive(src.toString()); // "2025-05-03"
                    }
                })
                .registerTypeAdapter(LocalTime.class, new JsonSerializer<LocalTime>() {
                    public JsonElement serialize(LocalTime src, Type type, JsonSerializationContext ctx) {
                        return new JsonPrimitive(src.toString()); // "18:30"
                    }
                })
                .create();
    }
}
