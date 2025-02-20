package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;

public class GestioneEventoPage extends MenuPage {

	public GestioneEventoPage() {
		super ("Gestione Evento");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Evento");
		chapters.add("Aggiungi Evento a POI");
		chapters.add("Aggiorna Evento");
		chapters.add("Visualizza tutti gli Eventi salvati");
		chapters.add("Visualizza tutti i POI salvati");
		chapters.add("Ricerca Evento tramite nome");
		chapters.add("Ricerca Evento tramite descrizione");
		this.setChapters(chapters);
		ArrayList<Page> pages = new ArrayList<Page>();
//		pages.add(new GestionePOIPage());
//		pages.add(new GestioneTOURPage());
//		pages.add(new GestioneContestPage());
//		pages.add(new GestioneEventoPage());
//		pages.add(new GestioneContenutiPage());
//		pages.add(new GestioneContenutiPage());
//		pages.add(new GestioneContenutiPage());
		this.setPages(pages);
		this.setPrevious(new MainPage());
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
