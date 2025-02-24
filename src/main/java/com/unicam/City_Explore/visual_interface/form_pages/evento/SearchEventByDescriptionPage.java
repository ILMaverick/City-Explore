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
public class SearchEventByDescriptionPage extends FormPage {

	@Autowired
	private EventService eventService;
	
	public SearchEventByDescriptionPage() {
		super("Ricerca Eventi tramite descrizione");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Event> eventList = eventService.searchEventByDescription(description);
        if(eventList.isEmpty()) {
            System.out.println("Non e' presente un Evento con questa descrizione.");
        } else {
            System.out.println("Elenco Eventi con la descrizione cercata:");
            for(Event event: eventList) {
                System.out.println(event);
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
