package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.EliminationPage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidationPage;
import com.unicam.City_Explore.visual_interface.menu_pages.content.ManageContentPage;
import com.unicam.City_Explore.visual_interface.menu_pages.contest.ManageContestPage;
import com.unicam.City_Explore.visual_interface.menu_pages.evento.ManageEventPage;
import com.unicam.City_Explore.visual_interface.menu_pages.poi.ManagePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.tour.ManageTourPage;

@Component
public class HomePage extends MenuPage{

	@Autowired
	private ManagePOIPage managePOIPage;
	@Autowired
	private ManageTourPage manageTourPage;
	@Autowired
	private ManageContestPage manageContestPage;
	@Autowired
	private ManageEventPage manageEventPage;
	@Autowired
	private ManageContentPage manageContentPage;
	@Autowired
	private ValidationPage validationPage;
	@Autowired
	private EliminationPage eliminationPage;
	@Autowired
	private SearchPage searchPage;
	
	public HomePage() {
		super ("Menu Principale");
		this.getChapters().add("Cerca POI, Tour, etc...");
		this.getChapters().add("Gestione POI");
		this.getChapters().add("Gestione Tour");
		this.getChapters().add("Gestione Contest");
		this.getChapters().add("Gestione Evento");
		this.getChapters().add("Gestione Contenuti Multimediali");
		this.getChapters().add("Validazione Elementi e Contenuti Pendenti");
		this.getChapters().add("Eliminazione Elementi e Contenuti");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Cerca POI, Tour, etc...", Role.values());
		this.authService.addAuthorization("Gestione POI", Role.CONTRIBUTOR, Role.AUTORIZED_CONTRIBUTOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Gestione Tour", Role.CONTRIBUTOR, Role.AUTORIZED_CONTRIBUTOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Gestione Contest", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Gestione Evento", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Gestione Contenuti Multimediali", Role.CONTRIBUTOR, Role.AUTORIZED_CONTRIBUTOR, Role.AUTHENTICATED_TOURIST, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Validazione Elementi e Contenuti Pendenti", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Eliminazione Elementi e Contenuti", Role.CURATOR, Role.ADMINISTRATOR);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Cerca POI, Tour, etc...", this.searchPage);
		this.getLinksTable().put("Gestione POI", this.managePOIPage);
		this.getLinksTable().put("Gestione Tour", this.manageTourPage);
		this.getLinksTable().put("Gestione Contest", this.manageContestPage);
		this.getLinksTable().put("Gestione Evento", this.manageEventPage);
		this.getLinksTable().put("Gestione Contenuti Multimediali", this.manageContentPage);
		this.getLinksTable().put("Validazione Elementi e Contenuti Pendenti", this.validationPage);
		this.getLinksTable().put("Eliminazione Elementi e Contenuti", this.eliminationPage);
	}
}
