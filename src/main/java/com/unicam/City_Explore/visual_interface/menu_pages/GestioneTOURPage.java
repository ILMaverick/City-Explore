package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;

public class GestioneTOURPage extends MenuPage {

	public GestioneTOURPage() {
		super ("Gestione TOUR");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Itinerario da POI");
		chapters.add("Visualizza tutti gli Itinerari salvati");
		chapters.add("Ricerca Itinerario tramite nome");
		chapters.add("Ricerca Itinerario tramite descrizione");
		this.setChapters(chapters);
		this.setPrevious(new MainPage());
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
