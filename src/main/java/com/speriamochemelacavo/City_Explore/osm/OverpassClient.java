package osm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class OverpassClient {
    private static final String OVERPASS_URL = "https://overpass-api.de/api/interpreter";

    public static String fetchOverpassData(String query) throws Exception {
        URI uri = URI.create(OVERPASS_URL);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Java Overpass Client)");

        // Scrivi la query nel body della richiesta
        String postData = "data=" + URLEncoder.encode(query, "UTF-8");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(postData.getBytes("UTF-8"));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Errore HTTP: " + responseCode);
        }

        // Leggi la risposta JSON
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
        }
        return response.toString();
    }
}