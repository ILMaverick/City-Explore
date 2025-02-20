package com.unicam.City_Explore.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class POIController {
    @Autowired
    private POIService poiService;

    public POIController() {
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
    public void searchPOIByName() {
        poiService.searchPOIByName();
    }

    public void searchPOIByDescription() {
        poiService.searchPOIByDescription();
    }
    public void close() {
        poiService.close();
    }

}

