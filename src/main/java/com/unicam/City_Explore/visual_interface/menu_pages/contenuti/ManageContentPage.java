package com.unicam.City_Explore.visual_interface.menu_pages.contenuti;


import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.LoadContentToContestPage;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.LoadContentToEventPage;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.LoadContentToPOIPage;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.LoadContentToTourPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageContentPage extends MenuPage {
	
	@Autowired
	private LoadContentToPOIPage loadContentToPOIPage;
	@Autowired
	private LoadContentToTourPage loadContentToTourPage;
	@Autowired
	private LoadContentToEventPage loadContentToEventPage;
	@Autowired
	private LoadContentToContestPage loadContentToContestPage;

	public ManageContentPage() {
		super ("Gestione Contenuti Multimediali");
		this.getChapters().add("Carica Contenuto ad un POI");
		this.getChapters().add("Carica Contenuto ad un Tour");
		this.getChapters().add("Carica Contenuto ad un Evento");
		this.getChapters().add("Carica Contenuto ad un Contest");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Carica Contenuto ad un POI", Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Carica Contenuto ad un Tour", Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Carica Contenuto ad un Evento", Role.TOURIST, Role.AUTHENTICATED_TOURIST, Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Carica Contenuto ad un Contest", Role.TOURIST, Role.AUTHENTICATED_TOURIST, Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Carica Contenuto ad un POI", this.loadContentToPOIPage);
		this.getLinksTable().put("Carica Contenuto ad un Tour", this.loadContentToTourPage);
		this.getLinksTable().put("Carica Contenuto ad un Evento", this.loadContentToEventPage);
		this.getLinksTable().put("Carica Contenuto ad un Contest", this.loadContentToContestPage);
	}
}
