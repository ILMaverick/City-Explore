package com.unicam.City_Explore.visual_interface.form_pages.evento;

import com.unicam.City_Explore.evento.EventService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteParticipationEventPage extends FormPage {

    @Autowired
    private EventService eventService;

    public DeleteParticipationEventPage() {
        super("Cancella Partecipazione ad Evento");
    }

    @Override
    public void startForm(Scanner scanner) {

        //TODO
    }

    @Override
    public Page getNext() {
        return this.getPrevious();
    }
}
