package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class SearchTourByDescriptionPage extends FormPage {

	@Autowired
	private POIService poiService;
	
	public SearchTourByDescriptionPage() {
		super("Ricerca Punti di Interesse tramite descrizione");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<PointOfInterest> pointOfInterestList = poiService.searchPOIByDescription(description);
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questa descrizione.");
        } else {
            System.out.println("Elenco Punti di Interesse con la descrizione cercata:");
            for(PointOfInterest pointOfInterest: pointOfInterestList) {
                System.out.println(pointOfInterest);
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
