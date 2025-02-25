package com.unicam.City_Explore.visual_interface.form_pages.contenuti;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.FormatFileEnum;
import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentService;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventService;
import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class LoadContentToEventPage extends FormPage {
	@Autowired
	private EventService eventService;
	@Autowired
	private MultimediaContentService contentService;
	
	public LoadContentToEventPage() {
		super("Aggiunta Contenuto a Evento");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Caricamento Contenuto Multimediale su un Evento ===");
		 
		 List<Event> eventList = eventService.getAllEvent();
	        if (eventList == null || eventList.isEmpty()) {
	            System.out.println("Nessun Evento disponibile per aggiungere un contenuto.");
	            return;
	        }

	        // Visualizza la lista degli Eventi con indice
	        System.out.println("Elenco degli Eventi disponibili:");
	        for (int i = 0; i < eventList.size(); i++) {
	            System.out.println((i + 1) + ". " + eventList.get(i));
	        }
	        
	     // L'utente seleziona l'Evento da includere (inserisci un solo numero)
	        System.out.print("Seleziona l'Eventio a cui aggiungere un Contenuto: (inserisci il numero corrispondente)");
	        String input = scanner.nextLine();
	        int index;
	        try {
	            index = Integer.parseInt(input.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Input non valido: " + input);
	            return;
	        }

	        if (index < 1 || index > eventList.size()) {
	            System.out.println("Indice fuori range. Selezione non valida.");
	            return;
	        }

	        Event selectedEvent = eventList.get(index - 1);
	        System.out.println("Hai selezionato l'Evento: " + selectedEvent);
	        
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
	        
	        //contentService.loadMultimediaContentToEvent(selectedEvent.getId(), content.getId());
	        
	        System.out.println("Contenuto Multimediale aggiunto all'Evento: ");
	        System.out.println(this.contentService.getMultimediaContentById(content.getId()));
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
