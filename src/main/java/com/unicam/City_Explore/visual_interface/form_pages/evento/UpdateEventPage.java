package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class UpdateEventPage extends FormPage {

	@Autowired
	private EventService eventService;

	public UpdateEventPage() {
		super("Aggiorna Evento");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<Event> eventList = eventService.getAllEvent();
        if (eventList == null || eventList.isEmpty()) {
            System.out.println("Nessun Evento disponibile per aggiornamento.");
            return;
        }

        // Visualizza la lista degli Eventi con indice
        System.out.println("Elenco degli Eventi disponibili:");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println((i + 1) + ". " + eventList.get(i));
        }
        
     // L'utente seleziona l'Evento da includere (inserisci un solo numero)
        System.out.print("Seleziona l'Evento da aggiornare: (inserisci il numero corrispondente)");
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
        
        System.out.print("Nome corrente (" + selectedEvent.getName() + "). Inserisci nuovo nome -String- (premi invio per saltare): ");
        String name = scanner.nextLine();

        System.out.print("Descrizione corrente (" + selectedEvent.getDescription() + "). Inserisci nuova descrizione -String- (premi invio per saltare): ");
        String description = scanner.nextLine();
        
        System.out.print("Scope corrente (" + selectedEvent.getScope() + "). Inserisci nuovo scope -String- (premi invio per saltare): ");
        String scope = scanner.nextLine();
        
        System.out.print("Attività corrente (" + selectedEvent.getActivity() + "). Inserisci la nuova attività -String- (premi invio per saltare): ");
        String activity = scanner.nextLine();
        
        System.out.print("Organizzazione corrente (" + selectedEvent.getOrganization() + "). Inserisci la nuova organizzazione -String- (premi invio per saltare): ");
        String organization = scanner.nextLine();
        
        System.out.print("Tema corrente (" + selectedEvent.getTheme() + "). Inserisci il nuovo tema -String- (premi invio per saltare): ");
        String theme = scanner.nextLine();
        
        System.out.print("Categoria corrente (" + selectedEvent.getCategory() + "). Inserisci la nuova categoria -String- (premi invio per saltare): ");
        String category = scanner.nextLine();
        
        System.out.print("Prezzo corrente (" + selectedEvent.getPrice() + "). Inserisci il nuovo prezzo -double- (premi invio per saltare): ");
		String price = scanner.nextLine();
		
		System.out.print("Orario corrente (" + selectedEvent.getTime() + "). Inserisci il nuovo orario -String- formato: dd-MM-yyyy HH:mm (premi invio per saltare): ");
		String time = scanner.nextLine();
		
		System.out.print("Inserisci evento aperto o chiuso (true per aperto, false per chiuso): ");
		String isOpen = scanner.nextLine();
		
		selectedEvent = eventService.updateEvent(selectedEvent.getId(), name, description, scope, activity, organization, theme, category, price, time, isOpen);
		System.out.println("Evento aggiornato: ");
		System.out.println(selectedEvent);
		}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
