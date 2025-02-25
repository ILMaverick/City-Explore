package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class DeleteEventPage extends FormPage{
	@Autowired
	private EventService eventService;
	@Autowired
	private DeletionService deletionService;

	public DeleteEventPage() {
		super("Eliminazione Evento");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Eliminazione Evento===");
		List<Event> eventList = eventService.getAllEvent();
        if (eventList == null || eventList.isEmpty()) {
            System.out.println("Nessun Evento disponibile per l'eliminazione.");
            return;
        }

        // Visualizza la lista degli Eventi con indice
        System.out.println("Elenco degli Eventi disponibili:");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println((i + 1) + ". " + eventList.get(i));
        }
        
     // L'utente seleziona l'Evento da includere (inserisci un solo numero)
        System.out.print("Seleziona l'Evento da eliminare: (inserisci il numero corrispondente)");
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
        List<Contest> contestList = selectedEvent.getContestList();
        List<MultimediaContent> multimediaContentList = selectedEvent.getMultimediaContentList();
        if(contestList.isEmpty()) {
            System.out.println("Non ci sono Contest associati a questo Evento.");
        } else {
            System.out.print("L'Evento da eliminare è associato a questi Contest: ");
            for(Contest e: contestList) {
                System.out.println(e);
            }
        }
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non ci sono Contenuti associati a questo Evento.");
        } else {
            System.out.print("L'Evento da eliminare ha questi Contenuti: ");
            for(MultimediaContent mc: multimediaContentList) {
                System.out.println(mc);
            }
        }
        
        System.out.println("Sei sicuro di voler eliminare l'Evento? (si/no)");
        String risposta = scanner.nextLine().trim().toLowerCase();
        String reason = "";
        if (risposta.equals("s") || risposta.equals("si")) {
            System.out.println("Eliminazione confermata.");
            System.out.print("Aggiungi una motivazione per l'eliminazione: ");
            reason = scanner.nextLine();
            deletionService.deleteEvent(selectedEvent.getId(), reason);
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
