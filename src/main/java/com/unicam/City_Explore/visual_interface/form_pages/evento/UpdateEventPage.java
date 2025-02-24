package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		System.out.println("=== Aggiorna Evento ===");

		System.out.print("Inserisci l'ID dell'Evento: ");
		int idEvent = scanner.nextInt();

		Event selectedEvent = eventService.getEventById(idEvent);
		System.out.println(selectedEvent);
		if(selectedEvent != null) {

			System.out.println("Per aggiornare l'Evento, inserire i dati negli appositi campi, compresi quelli del precedente");

			scanner.nextLine();

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

			Event newEvent = eventService.createEvent(name, description, scope, activity, organization, theme, category, price, time, isOpen);

			selectedEvent = eventService.updateEvent(idEvent, newEvent);

			System.out.println("Evento aggiornato: ");
			System.out.println(selectedEvent);
			scanner.nextLine();
		} else {
			System.out.println("L'Evento non e' presente ");
		}
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
