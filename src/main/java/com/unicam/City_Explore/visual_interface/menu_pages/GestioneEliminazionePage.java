package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;


import org.springframework.stereotype.Component;

@Component
public class GestioneEliminazionePage extends MenuPage{

	public GestioneEliminazionePage() {
		super ("Gestione Eliminazione");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Elimina POI");
		chapters.add("Elimina Itinerario");
		chapters.add("Elimina Contest");
		chapters.add("Elimina Evento");
		chapters.add("Elimina Contenuto");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
