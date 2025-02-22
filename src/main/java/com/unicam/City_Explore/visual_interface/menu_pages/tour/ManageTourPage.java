package com.unicam.City_Explore.visual_interface.menu_pages.tour;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageTourPage extends MenuPage {

	public ManageTourPage() {
		super ("Gestione TOUR");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Itinerario da POI");
		chapters.add("Visualizza tutti gli Itinerari salvati");
		chapters.add("Ricerca Itinerario tramite nome");
		chapters.add("Ricerca Itinerario tramite descrizione");
		this.setChapters(chapters);
	}
}
