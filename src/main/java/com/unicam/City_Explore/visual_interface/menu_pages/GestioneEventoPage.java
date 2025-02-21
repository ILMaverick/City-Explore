package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;


import org.springframework.stereotype.Component;

@Component
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
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
