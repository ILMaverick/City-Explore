package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.EliminazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.InizializzazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidazionePage;

@Component
public class MainPage extends MenuPage{

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
		this.setPrevious(null);
	}

	@Override
	public Page getNext(int idChapter) {
		switch (idChapter) {
		case 1: {
			return new GestionePOIPage();
		}
		case 2: {
			return new GestioneTOURPage();
		}
		case 3: {
			return new GestioneContestPage();
		}
		case 4: {
			return new GestioneEventoPage();
		}
		case 5: {
			return new GestioneContenutiPage();
		}		
		case 6: {
			return new ValidazionePage();
		}
		case 7: {
			return new EliminazionePage();
		}
		case 8: {
			return new InizializzazionePage();
		}
		default:
			throw new IllegalArgumentException("Scelta non valida: " + idChapter);
		}
	}
}
