package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;

public class GestionePOIPage extends MenuPage {

	public GestionePOIPage() {
		super ("Gestione POI");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea PointOfInterest da zero");
		chapters.add("Crea PointOfInterest da OSM");
		chapters.add("Visualizza tutti i PointOfInterest salvati");
		chapters.add("Ricerca Punto di Interesse tramite nome");
		chapters.add("Ricerca Punto di Interesse tramite descrizione");
		this.setChapters(chapters);
		this.setPrevious(null);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
