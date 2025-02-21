package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;


import org.springframework.stereotype.Component;

@Component
public class GestioneContestPage extends MenuPage {

	public GestioneContestPage() {
		super ("Gestione Contest");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Contest");
		chapters.add("Visualizza tutti i Contest salvati");
		chapters.add("Ricerca Contest tramite nome");
		chapters.add("Ricerca Contest tramite descrizione");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
