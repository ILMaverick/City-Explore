package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class CreationTourPage extends FormPage {
	
	@Autowired
	private TourService tourService;
	@Autowired
	private POIService poiService;
	
	public CreationTourPage() {
		super("Creazione di un Tour a partire da POI");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList == null || poiList.isEmpty()) {
            System.out.println("Nessun POI disponibile per creare un Tour.");
            return;
        }

        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println((i + 1) + ". " + poiList.get(i));
        }

        // L'utente seleziona i POI da includere, separati da virgola (es. "1,3,5")
        System.out.print("Seleziona i POI da includere nel Tour (inserisci i numeri separati da virgola): ");
        String input = scanner.nextLine();
        String[] tokens = input.split(",");
        List<PointOfInterest> selectedPOIs = new ArrayList<>();
        for (String token : tokens) {
            try {
                int index = Integer.parseInt(token.trim());
                if (index >= 1 && index <= poiList.size()) {
                    selectedPOIs.add(poiList.get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido: " + token);
            }
        }

        if (selectedPOIs.isEmpty()) {
            System.out.println("Nessun POI selezionato.");
            return;
        }
        
        Tour tour = tourService.buildTourFromPOIs(selectedPOIs);

        System.out.println("\nTour creato e salvato con successo:");
        System.out.println(this.tourService.searchTourByName(tour.getName()));
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
