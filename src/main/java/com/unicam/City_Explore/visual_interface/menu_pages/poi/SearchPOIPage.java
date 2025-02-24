package com.unicam.City_Explore.visual_interface.menu_pages.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.poi.SearchPOIByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.poi.SearchPOIByNamePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchPOIPage extends MenuPage {
	
	@Autowired
	private SearchPOIByNamePage searchPOIByNamePage;
	@Autowired
	private SearchPOIByDescriptionPage seadesByDescriptionPage;

	public SearchPOIPage() {
		super("Cerca un Punto di Interesse");
		this.getChapters().add("Ricerca Punto di Interesse tramite nome");
		this.getChapters().add("Ricerca Punto di Interesse tramite descrizione");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Ricerca Punto di Interesse tramite nome", Role.values());
		this.authService.addAuthorization("Ricerca Punto di Interesse tramite descrizione", Role.values());
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Ricerca Punto di Interesse tramite nome", this.searchPOIByNamePage);
		this.getLinksTable().put("Ricerca Punto di Interesse tramite descrizione", this.seadesByDescriptionPage);
	}

}
