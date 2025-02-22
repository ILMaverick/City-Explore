package com.unicam.City_Explore.visual_interface.form_pages.POI;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class SearchPOIByNamePage extends FormPage {

	@Autowired
	private POIService poiService;
	
	public SearchPOIByNamePage() {
		super("Ricerca Punti di Interesse tramite nome");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();
        List<PointOfInterest> pointOfInterestList = poiService.searchPOIByName(name);
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questo nome.");
        } else {
            System.out.println("Elenco Punti di Interesse con il nome cercato:");
            for(PointOfInterest pointOfInterest: pointOfInterestList) {
                System.out.println(pointOfInterest);
            }
        }
	}
}
