package POI;

import java.util.List;
import java.util.Scanner;

import OSM.OSMSearchService;
import OSM.OverpassElement;
import USER.User;

public class POIController {

    private POIService poiService;

    public POIController(POIService poiService) {
        this.poiService = poiService;
    }

    public void createPOIFromScratch() {
        poiService.createPOIFromScratch();
    }

    public void createPOIFromOSM() {
        poiService.createPOIFromOSM();
    }

    public void initializer() {
        poiService.initializer();
    }
    
    public void displayAllPOIs() {
        List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList.isEmpty()) {
            System.out.println("Nessun POI salvato.");
        } else {
            System.out.println("Elenco di tutti i POI salvati:");
            for (PointOfInterest poi : poiList) {
                System.out.println(poi);
            }
        }
    }

    public void close() {
        poiService.close();
    }

}

