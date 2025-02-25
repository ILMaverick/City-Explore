package com.unicam.City_Explore.visual_interface.menu_pages.evento;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.evento.*;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageEventPage extends MenuPage {
	@Autowired
	private CreationEventPage creationEventPage;
	@Autowired
	private AddPOIToEventPage addPOIToEventPage;
	@Autowired
	private UpdateEventPage updateEventPage;
	@Autowired
	private ParticipationEventPage participationEventPage;
	@Autowired
	private DeleteParticipationEventPage deleteParticipationEventPage;

	public ManageEventPage() {
		super ("Gestione Evento");
		
		this.getChapters().add("Crea Evento");
		this.getChapters().add("Aggiungi POI ad un Evento");
		this.getChapters().add("Aggiorna un Evento");
		this.getChapters().add("Partecipa ad un Evento");
		this.getChapters().add("Cancella Partecipazione ad un Evento");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Crea Evento", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Aggiungi POI ad un Evento", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Aggiorna un Evento", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Partecipa ad un Evento", Role.TOURIST, Role.AUTHENTICATED_TOURIST);
		this.authService.addAuthorization("Cancella Partecipazione ad un Evento", Role.TOURIST, Role.AUTHENTICATED_TOURIST);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Crea Evento", this.creationEventPage);
		this.getLinksTable().put("Aggiungi POI ad un Evento", this.addPOIToEventPage);
		this.getLinksTable().put("Aggiorna un Evento", this.updateEventPage);
		this.getLinksTable().put("Partecipa ad un Evento", this.participationEventPage);
		this.getLinksTable().put("Cancella Partecipazione ad un Evento", this.deleteParticipationEventPage);
	}
}
