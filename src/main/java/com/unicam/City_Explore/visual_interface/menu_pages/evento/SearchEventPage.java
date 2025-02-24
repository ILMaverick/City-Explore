package com.unicam.City_Explore.visual_interface.menu_pages.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.evento.SearchEventByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.evento.SearchEventByNamePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchEventPage extends MenuPage {
	@Autowired
	private SearchEventByNamePage searchEventByNamePage;
	@Autowired
	private SearchEventByDescriptionPage searchEventByDescriptionPage;
	
	public SearchEventPage() {
		super("Cerca un Evento");
		this.getChapters().add("Ricerca un Evento tramite nome");
		this.getChapters().add("Ricerca un Evento tramite descrizione");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Ricerca un Evento tramite nome", Role.values());
		this.authService.addAuthorization("Ricerca un Evento tramite descrizione", Role.values());
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Ricerca un Evento tramite nome", this.searchEventByNamePage);
		this.getLinksTable().put("Ricerca un Evento tramite descrizione", this.searchEventByDescriptionPage);
	}

}
