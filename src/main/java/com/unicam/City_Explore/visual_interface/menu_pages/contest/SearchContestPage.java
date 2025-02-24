package com.unicam.City_Explore.visual_interface.menu_pages.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.contest.SearchContestByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.contest.SearchContestByNamePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchContestPage extends MenuPage {
	
	@Autowired
	private SearchContestByNamePage searchContestByNamePage;
	@Autowired
	private SearchContestByDescriptionPage searchContestByDescriptionPage;

	public SearchContestPage() {
		super("Cerca un Contest");
		this.getChapters().add("Ricerca un Contest tramite nome");
		this.getChapters().add("Ricerca un Contest tramite descrizione");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Ricerca un Contest tramite nome", Role.values());
		this.authService.addAuthorization("Ricerca un Contest tramite descrizione", Role.values());

	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Ricerca un Contest tramite nome", this.searchContestByNamePage);
		this.getLinksTable().put("Ricerca un Contest tramite descrizione", this.searchContestByDescriptionPage);
	}

}
