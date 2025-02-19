package osm;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserSingleton {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }
}