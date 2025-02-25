package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import com.unicam.City_Explore.tour.Way;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class DeleteTourPage extends FormPage{
	@Autowired
	private TourService tourService;
	@Autowired
	private DeletionService deletionService;

	public DeleteTourPage() {
		super("Eliminazione Itinerario");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Eliminazione Itinerario ===");
		List<Tour> tourList = tourService.getAllTours();
        if (tourList == null || tourList.isEmpty()) {
            System.out.println("Nessun Tour disponibile per l'eliminazione.");
            return;
        }

        // Visualizza la lista dei Tour con indice
        System.out.println("Elenco dei Tour disponibili:");
        for (int i = 0; i < tourList.size(); i++) {
            System.out.println((i + 1) + ". " + tourList.get(i));
        }
        
     // L'utente seleziona il Tour da includere (inserisci un solo numero)
        System.out.print("Seleziona il Tour da eliminare: (inserisci il numero corrispondente)");
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
        List<Way> wayList = selectedTour.getWayList();
        List<MultimediaContent> multimediaContentList = selectedTour.getMultimediaContentList();
        if(wayList.isEmpty()) {
            System.out.println("Non ci sono Percorsi associati a questo Itinerario.");
        } else {
            System.out.print("L'Itinerario da eliminare ha questi percorsi: ");
            for(Way e: wayList) {
                System.out.println(e);
            }
        }
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non ci sono Contenuti associati a questo Itinerario.");
        } else {
            System.out.print("\"L'Itinerario da eliminare ha questi Contenuti: ");
            for(MultimediaContent mc: multimediaContentList) {
                System.out.println(mc);
            }
        }
        
        System.out.println("Sei sicuro di voler eliminare l'Itinerario? (si/no)");
        String risposta = scanner.nextLine().trim().toLowerCase();
        String reason = "";
        if (risposta.equals("s") || risposta.equals("si")) {
            System.out.println("Eliminazione confermata.");
            System.out.print("Aggiungi una motivazione per l'eliminazione: ");
            reason = scanner.nextLine();
            deletionService.deleteTour(selectedTour.getId(), reason);
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
