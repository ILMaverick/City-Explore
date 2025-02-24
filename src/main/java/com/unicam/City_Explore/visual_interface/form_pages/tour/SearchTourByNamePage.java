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
public class SearchTourByNamePage extends FormPage {

	@Autowired
	private TourService tourService;
	
	public SearchTourByNamePage() {
		super("Ricerca Itinerari tramite nome");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();
        List<Tour> tourList = tourService.searchTourByName(name);
        if(tourList.isEmpty()) {
            System.out.println("Non e' presente un Itinerario con questo nome.");
        } else {
            System.out.println("Elenco Itinerari con il nome cercato:");
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
