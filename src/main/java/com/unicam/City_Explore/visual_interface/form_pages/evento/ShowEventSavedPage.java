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
public class ShowEventSavedPage extends FormPage {

	@Autowired
	private EventService eventService;
	
	public ShowEventSavedPage() {
		super("Elenco di tutti gli Eventi salvati");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<Event> eventList = eventService.getAllEvent();
        if (eventList.isEmpty()) {
            System.out.println("Nessun Evento salvato!");
        } else {
            for (Event event : eventList) {
                System.out.println(event + "\n");
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
