package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;

public class GestioneValidazionePage extends MenuPage {

	public GestioneValidazionePage() {
		super ("Gestione Validazione");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Validazione");
		chapters.add("Mostra POI Pendenti");
		chapters.add("Mostra Itinerari Pendenti");
		chapters.add("Mostra Contenuti Multimediali Pendenti");
		this.setChapters(chapters);
		this.setPrevious(new MainPage());
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
