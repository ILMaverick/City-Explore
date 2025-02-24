package com.unicam.City_Explore.visual_interface.form_pages.contenuti;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.FormatFileEnum;
import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentService;
import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class LoadContentToTourPage extends FormPage {
	
	@Autowired
	private TourService tourService;
	@Autowired
	private MultimediaContentService contentService;
	
	public LoadContentToTourPage() {
		super("Aggiunta di un nuovo Contenuto in un Tour");
	}

	@Override
	public void startForm(Scanner scanner) {
 System.out.println("=== Caricamento Contenuto Multimediale su un Tour ===");
		 
		 List<Tour> tourList = tourService.getAllTours();
	        if (tourList == null || tourList.isEmpty()) {
	            System.out.println("Nessun Tour disponibile per aggiungere un contenuto.");
	            return;
	        }

	        // Visualizza la lista dei Tour con indice
	        System.out.println("Elenco dei Tour disponibili:");
	        for (int i = 0; i < tourList.size(); i++) {
	            System.out.println((i + 1) + ". " + tourList.get(i));
	        }
	        
	     // L'utente seleziona il Tour da includere (inserisci un solo numero)
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
	        System.out.println("Hai selezionato il Tour: " + selectedTour);
	        
	        System.out.print("Inserisci il nome del Contenuto: ");
	        String name = scanner.nextLine();
	        
	        System.out.print("Inserisci la descrizione del Contenuto: ");
	        String description = scanner.nextLine();
	        
	        System.out.print("Inserisci il formato: ");
	        String formatString = scanner.nextLine();
	        FormatFileEnum format = FormatFileEnum.formatFile(formatString);
	        
	        System.out.print("Inserisci la durata: ");
	        float duration = scanner.nextFloat();

	        System.out.print("Inserisci la dimensione: ");
	        float dimension = scanner.nextFloat();

	        System.out.print("Inserisci la risoluzione: ");
	        float resolution = scanner.nextFloat();
	        MultimediaContent content = contentService.createMultimediaContent(name, description, format, duration, dimension, resolution);
	        
	        contentService.loadMultimediaContentToTour(selectedTour.getId(), content.getId());
	        
	        System.out.println("Contenuto Multimediale aggiunto al Punto di Interesse: ");
	        System.out.println(this.contentService.getMultimediaContentById(content.getId()));
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
