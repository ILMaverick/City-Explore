package com.unicam.City_Explore.visual_interface.form_pages.poi;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class DeletePOIPage extends FormPage{
	@Autowired
	private DeletionService deletionService;
	@Autowired
	private POIService poiService;

	public DeletePOIPage() {
		super("Eliminazione POI");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Eliminazione Punto di Interesse ===");
		List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList == null || poiList.isEmpty()) {
            System.out.println("Nessun POI disponibile per l'eliminazione.");
            return;
        }

        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println((i + 1) + ". " + poiList.get(i));
        }
        
     // L'utente seleziona il POI da includere (inserisci un solo numero)
        System.out.print("Seleziona il POI da eliminare: (inserisci il numero corrispondente)");
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido: " + input);
            return;
        }

        if (index < 1 || index > poiList.size()) {
            System.out.println("Indice fuori range. Selezione non valida.");
            return;
        }

        PointOfInterest selectedPOI = poiList.get(index - 1);
        List<Event> eventList = selectedPOI.getEvents();
        List<MultimediaContent> multimediaContentList = selectedPOI.getMultimediaContentList();
        if(eventList.isEmpty()) {
            System.out.println("Non ci sono Eventi associati a questo Punto di Interesse.");
        } else {
            System.out.print("Il Punto di Interesse da eliminare si trova in questi eventi: ");
            for(Event e: eventList) {
                System.out.println(e);
            }
        }
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non ci sono Contenuti associati a questo Punto di Interesse.");
        } else {
            System.out.print("Il Punto di Interesse da eliminare ha questi Contenuti: ");
            for(MultimediaContent mc: multimediaContentList) {
                System.out.println(mc);
            }
        }
        
        System.out.println("Sei sicuro di voler eliminare il Punto di Interesse? (si/no)");
        String risposta = scanner.nextLine().trim().toLowerCase();
        String reason = "";
        if (risposta.equals("s") || risposta.equals("si")) {
            System.out.println("Eliminazione confermata.");
            System.out.print("Aggiungi una motivazione per l'eliminazione: ");
            reason = scanner.nextLine();
            deletionService.deletePOI(selectedPOI.getId(), reason);
        } else if (risposta.equals("n") || risposta.equals("no")) {
            System.out.println("Operazione annullata.");
        } else {
            System.out.println("Risposta non valida. Operazione annullata.");
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}

}
