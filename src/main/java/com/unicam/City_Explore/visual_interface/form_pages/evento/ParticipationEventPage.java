package com.unicam.City_Explore.visual_interface.form_pages.evento;

import com.unicam.City_Explore.evento.EventService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ParticipationEventPage extends FormPage {

    @Autowired
    private EventService eventService;

    public ParticipationEventPage() {
        super("Partecipa ad Evento");
    }

    @Override
    public void startForm(Scanner scanner) {
        System.out.println("==== Iscrizione ad Evento ===");
        System.out.println("Benvenuto, per iscriversi all'evento, bisogna essere registrati.");

        System.out.print("Aggiungi l'idUtente:");
        int idUser = scanner.nextInt();

        System.out.print("Aggiungi l'idEvento:");
        int idEvent = scanner.nextInt();

        eventService.participateEvent(idEvent, idUser);
        System.out.println("Iscrizione avvenuta con successo.");
    }

    @Override
    public Page getNext() {
        return this.getPrevious();
    }
}