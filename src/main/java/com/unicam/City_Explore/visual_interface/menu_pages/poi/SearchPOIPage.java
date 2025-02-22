package com.unicam.City_Explore.visual_interface.menu_pages.poi;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class SearchPOIPage extends MenuPage {

	public SearchPOIPage() {
		super("Cerca un Punto di Interesse");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Ricerca Punto di Interesse tramite nome");
		chapters.add("Ricerca Punto di Interesse tramite descrizione");
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
