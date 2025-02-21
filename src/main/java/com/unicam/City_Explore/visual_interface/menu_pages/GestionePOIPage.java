package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.CreazionePOIOSMPage;
import com.unicam.City_Explore.visual_interface.form_pages.CreazionePOIUtentePage;
import com.unicam.City_Explore.visual_interface.form_pages.EliminazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.RicercaPOIDescrizione;
import com.unicam.City_Explore.visual_interface.form_pages.RicercaPOINome;
import com.unicam.City_Explore.visual_interface.form_pages.ValidazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.VisualizzazionePOISalvatiPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestionePOIPage extends MenuPage {

	@Autowired
	private CreazionePOIUtentePage creazionePOIUtentePage;
	@Autowired
	private CreazionePOIOSMPage creazionePOIOSMPage;
	@Autowired
	private VisualizzazionePOISalvatiPage visualizzazionePOISalvatiPage;
	@Autowired
	private RicercaPOINome ricercaPOINome;
	@Autowired
	private RicercaPOIDescrizione ricercaPOIDescrizione;
	
	public GestionePOIPage() {
		super ("Gestione POI");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea PointOfInterest da zero");
		chapters.add("Crea PointOfInterest da OSM");
		chapters.add("Visualizza tutti i PointOfInterest salvati");
		chapters.add("Ricerca Punto di Interesse tramite nome");
		chapters.add("Ricerca Punto di Interesse tramite descrizione");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) throws IllegalArgumentException {
		switch (idChapter) {
		case 1: {
			return this.creazionePOIUtentePage;
		}
		case 2: {
			return this.creazionePOIOSMPage;
		}
		case 3: {
			return this.visualizzazionePOISalvatiPage;
		}
		case 4: {
			return this.ricercaPOINome;
		}
		case 5: {
			return this.ricercaPOIDescrizione;
		}
		default:
			throw new IllegalArgumentException("Scelta non valida: " + idChapter);
		}
	}
}
