package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;

@Component
public class SearchPage extends MenuPage {

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
		// TODO Auto-generated method stub

	}

}
