package com.unicam.City_Explore.visual_interface.menu_pages.evento;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

public class SearchEventPage extends MenuPage {

	
	
	public SearchEventPage(String title) {
		super("Cerca un Evento");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Ricerca un Evento tramite nome");
		chapters.add("Ricerca un Evento tramite descrizione");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAuthorization() {
		// TODO Auto-generated method stub

	}

	@Override
	public void populateLinksTable() {
		// TODO Auto-generated method stub

	}

}
