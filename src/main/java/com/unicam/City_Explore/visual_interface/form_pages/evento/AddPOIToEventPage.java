package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.util.Scanner;

import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;


@Component
public class AddPOIToEventPage extends FormPage {

    @Autowired
    private EventService eventService;

    public AddPOIToEventPage() {
        super("Aggiunta di un Punto di Interesse a un Evento.");
    }

    @Override
    public void startForm(Scanner scanner) {
        System.out.println("=== Aggiungi Punto di Interesse ad un Evento ===");

        System.out.print("Inserisci l'ID del Punto di Interesse: ");
        int idPOI = scanner.nextInt();

        System.out.print("Inserisci l'ID dell'Evento: ");
        int idEvent = scanner.nextInt();

        Event event = eventService.addEventToPOI(idPOI, idEvent);

        System.out.println("Evento aggiunto al Punto di Interesse: ");
        System.out.print(event);
    }

    @Override
    public Page getNext() {
        return this.getPrevious();
    }
}

