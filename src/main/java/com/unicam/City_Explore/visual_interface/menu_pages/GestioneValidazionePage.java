package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;

@Component
public class GestioneValidazionePage extends MenuPage {

	public GestioneValidazionePage() {
		super ("Gestione Validazione");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Validazione");
		chapters.add("Mostra POI Pendenti");
		chapters.add("Mostra Itinerari Pendenti");
		chapters.add("Mostra Contenuti Multimediali Pendenti");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
