package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;


import org.springframework.stereotype.Component;

@Component
public class GestioneContenutiPage extends MenuPage {

	public GestioneContenutiPage() {
		super ("Gestione Contenuti Multimediali");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Contenuto");
		chapters.add("Carica Contenuto ad un POI");
		chapters.add("Visualizza tutti i Contenuti Multimediali salvati");
		chapters.add("Ricerca Contenuto tramite nome");
		chapters.add("Ricerca Contenuto tramite descrizione");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
