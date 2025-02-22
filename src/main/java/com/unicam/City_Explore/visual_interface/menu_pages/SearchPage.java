package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.menu_pages.contest.SearchContestPage;
import com.unicam.City_Explore.visual_interface.menu_pages.evento.SearchEventPage;
import com.unicam.City_Explore.visual_interface.menu_pages.poi.SearchPOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.tour.SearchTourPage;

@Component
public class SearchPage extends MenuPage {

	@Autowired
	private SearchPOIPage searchPOIPage;
	@Autowired
	private SearchTourPage searchTourPage;
	@Autowired
	private SearchContestPage searchContestPage;
	@Autowired
	private SearchEventPage searchEventPage;
	@Autowired
	private SearchContestPage searchcontentPage;
	
	public SearchPage() {
		super("Menu Ricerca");
		this.getChapters().add("Cerca POI");
		this.getChapters().add("Cerca Tour");
		this.getChapters().add("Cerca Contest");
		this.getChapters().add("Cerca Evento");
		this.getChapters().add("Cerca Contenuti Multimediali");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Cerca POI", Role.values());
		this.authService.addAuthorization("Cerca Tour", Role.values());
		this.authService.addAuthorization("Cerca Contest", Role.values());
		this.authService.addAuthorization("Cerca Evento", Role.values());
		this.authService.addAuthorization("Cerca Contenuti Multimediali", Role.values());
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Cerca POI", this.searchPOIPage);
		this.getLinksTable().put("Cerca Tour", this.searchTourPage);
		this.getLinksTable().put("Cerca Contest", this.searchContestPage);
		this.getLinksTable().put("Cerca Evento", this.searchEventPage);
		this.getLinksTable().put("Cerca Contenuti Multimediali", this.searchcontentPage);
	}
}
