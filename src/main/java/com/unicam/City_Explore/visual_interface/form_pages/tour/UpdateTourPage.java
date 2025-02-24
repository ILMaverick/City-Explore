package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class UpdateTourPage extends FormPage {
	@Autowired
	private TourService tourService;

	public UpdateTourPage() {
		super("Aggiorna Tour");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<Tour> tourList = tourService.getAllTours();
        if (tourList == null || tourList.isEmpty()) {
            System.out.println("Nessun Tour disponibile per aggiungere un contenuto.");
            return;
        }

        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei Tour disponibili:");
        for (int i = 0; i < tourList.size(); i++) {
            System.out.println((i + 1) + ". " + tourList.get(i));
        }
        
     // L'utente seleziona il POI da includere (inserisci un solo numero)
        System.out.print("Seleziona il Tour a cui aggiungere un Contenuto: (inserisci il numero corrispondente)");
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido: " + input);
            return;
        }

        if (index < 1 || index > tourList.size()) {
            System.out.println("Indice fuori range. Selezione non valida.");
            return;
        }

        Tour selectedTour = tourList.get(index - 1);
        System.out.print("Nome corrente (" + selectedTour.getName() + "). Inserisci nuovo nome -String- (premi invio per saltare): ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione corrente (" + selectedTour.getDescription() + "). Inserisci nuova descrizione -String- (premi invio per saltare): ");
        String descrizione = scanner.nextLine();
        
        tourService.updateTour(selectedTour.getId(), nome, descrizione);
        
        System.out.println("Hai aggiornato il seguente Tour: ");
        System.out.println(this.tourService.getTourById(selectedTour.getId()));
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
