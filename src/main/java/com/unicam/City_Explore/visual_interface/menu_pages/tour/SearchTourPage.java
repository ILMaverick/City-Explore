package com.unicam.City_Explore.visual_interface.menu_pages.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.tour.SearchTourByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.tour.SearchTourByNamePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchTourPage extends MenuPage {
	@Autowired
	private SearchTourByNamePage searchTourByNamePage;
	@Autowired
	private SearchTourByDescriptionPage searchTourByDescriptionPage;

	public SearchTourPage() {
		super("Cerca un Tour");
		this.getChapters().add("Ricerca Tour tramite nome");
		this.getChapters().add("Ricerca Tour tramite descrizione");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Ricerca Tour tramite nome", Role.values());
		this.authService.addAuthorization("Ricerca Tour tramite descrizione", Role.values());

	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Ricerca Tour tramite nome", this.searchTourByNamePage);
		this.getLinksTable().put("Ricerca Tour tramite descrizione", this.searchTourByDescriptionPage);
	}

}
