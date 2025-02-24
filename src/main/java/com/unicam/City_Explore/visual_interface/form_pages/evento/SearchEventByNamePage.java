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
public class SearchEventByNamePage extends FormPage {

	@Autowired
	private EventService eventService;
	
	public SearchEventByNamePage() {
		super("Ricerca Eventi tramite nome");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();
        List<Event> eventList = eventService.searchEventByName(name);
        if(eventList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questo nome.");
        } else {
            System.out.println("Elenco Punti di Interesse con il nome cercato:");
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
