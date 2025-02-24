package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventService;
import com.unicam.City_Explore.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class CreationEventPage extends FormPage {
	
	@Autowired
	private EventService eventService;
	
	public CreationEventPage() {
		super("Creazione di un nuovo Evento da zero");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.println("=== Creazione di un nuovo Evento ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci lo scope: ");
        String scope = scanner.nextLine();

        System.out.print("Inserisci l'attività: ");
        String activity = scanner.nextLine();

        System.out.print("Inserisci l'organizzazione: ");
        String organization = scanner.nextLine();

        System.out.print("Inserisci il tema: ");
        String theme = scanner.nextLine();

        System.out.print("Inserisci la categoria: ");
        String category = scanner.nextLine();

        System.out.print("Inserisci il prezzo (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci l'orario (formato: dd-MM-yyyy HH:mm): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String timeString = scanner.nextLine();
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        System.out.print("Inserisci evento aperto o chiuso (true per aperto, false per chiuso): ");
        boolean isOpen = scanner.nextBoolean();

        Event event = eventService.createEvent(name, description, scope, activity, organization, theme, category, price, time, isOpen);

        System.out.println("\nEvento creato e salvato con successo:");
        System.out.println(event);
        scanner.nextLine();
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
