package OSM;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import POI.POIType;
import POI.PointOfInterest;
import POI.PointOfInterestFactory;
import USER.User;

public class OSMSearchService {
	public List<OverpassElement> search(String city, String poi) {
	System.out.println("Ricerca per: " + poi + " a " + city);
    String poiFilter = POIFilterStrategy.getFilter(poi);

    List<OverpassElement> filteredElements = null;
    
    try {
        String overpassQuery = OverpassQueryFactory.createQuery(city, poiFilter);
        System.out.println("Query Overpass:\n" + overpassQuery);

        String jsonResponse = OverpassClient.fetchOverpassData(overpassQuery);
        System.out.println("Risultato JSON:\n" + jsonResponse);

        OverpassResponse overpassResponse = JsonParserSingleton.getInstance()
                .readValue(jsonResponse, OverpassResponse.class);

        System.out.println("\nRisultati filtrati:");
        filteredElements = Arrays.stream(overpassResponse.getElements())
                .filter(element -> element.tags != null && element.tags.name != null && !element.tags.name.trim().isEmpty())
                .collect(Collectors.toList());
        

        if (filteredElements.isEmpty()) {
            System.out.println("Nessun elemento con nome trovato.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    // Restituisci la lista degli oggetti trovati
    return filteredElements;
}
}

