package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class SearchTourByDescriptionPage extends FormPage {

	@Autowired
	private TourService tourService;
	
	public SearchTourByDescriptionPage() {
		super("Ricerca Itinerari tramite descrizione");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Tour> tourList = tourService.searchTourByDescription(description);
        if(tourList.isEmpty()) {
            System.out.println("Non e' presente un Itinerario con questa descrizione.");
        } else {
            System.out.println("Elenco Itinerari con la descrizione cercata:");
            for(Tour tour: tourList) {
                System.out.println(tour);
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
