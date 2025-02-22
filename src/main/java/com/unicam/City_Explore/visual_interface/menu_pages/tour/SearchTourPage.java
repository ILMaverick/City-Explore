package com.unicam.City_Explore.visual_interface.menu_pages.tour;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchTourPage extends MenuPage {

	public SearchTourPage() {
		super("Cerca un Tour");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Ricerca Tour tramite nome");
		chapters.add("Ricerca Tour tramite descrizione");
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
