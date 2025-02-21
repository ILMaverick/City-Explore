package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.EliminazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidazionePage;

@Component
public class MainPage extends MenuPage{

	@Autowired
	private GestionePOIPage gestionePOIPage;
	@Autowired
	private GestioneTOURPage gestioneTOURPage;
	@Autowired
	private GestioneContestPage gestioneContestPage;
	@Autowired
	private GestioneEventoPage gestioneEventoPage;
	@Autowired
	private GestioneContenutiPage gestioneContenutiPage;
	@Autowired
	private ValidazionePage validazionePage;
	@Autowired
	private EliminazionePage eliminazionePage;
	
	public MainPage() {
		super ("Menu Principale");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Gestione POI");
		chapters.add("Gestione Tour");
		chapters.add("Gestione Contest");
		chapters.add("Gestione Evento");
		chapters.add("Gestione Contenuti Multimediali");
		chapters.add("Validazione Elementi e Contenuti Pendenti");
		chapters.add("Eliminazione Elementi e Contenuti");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) throws IllegalArgumentException {
		switch (idChapter) {
		case 1: {
			return this.gestionePOIPage;
		}
		case 2: {
			return this.gestioneTOURPage;
		}
		case 3: {
			return this.gestioneContestPage;
		}
		case 4: {
			return this.gestioneEventoPage;
		}
		case 5: {
			return this.gestioneContenutiPage;
		}		
		case 6: {
			return this.validazionePage;
		}
		case 7: {
			return this.eliminazionePage;
		}
		default:
			throw new IllegalArgumentException("Scelta non valida: " + idChapter);
		}
	}
}
