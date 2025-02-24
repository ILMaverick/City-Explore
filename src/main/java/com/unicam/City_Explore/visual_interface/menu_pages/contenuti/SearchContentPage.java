package com.unicam.City_Explore.visual_interface.menu_pages.contenuti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.SearchContentByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.SearchContentByNamePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchContentPage extends MenuPage {
	@Autowired
	private SearchContentByNamePage searchContentByNamePage;
	@Autowired
	private SearchContentByDescriptionPage searchContentByDescriptionPage;

	public SearchContentPage() {
		super("Cerca un Contenuto Multimediale");
		this.getChapters().add("Ricerca Contenuto Multimediale tramite nome");
		this.getChapters().add("Ricerca Contenuto Multimediale tramite descrizione");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Ricerca Contenuto Multimediale tramite nome", Role.values());
		this.authService.addAuthorization("Ricerca Contenuto Multimediale tramite descrizione", Role.values());
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Ricerca Contenuto Multimediale tramite nome", this.searchContentByNamePage);
		this.getLinksTable().put("Ricerca Contenuto Multimediale tramite descrizione", this.searchContentByDescriptionPage);
	}

}
