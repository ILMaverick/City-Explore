package com.unicam.City_Explore.visual_interface.menu_pages.evento;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageEventPage extends MenuPage {

	public ManageEventPage() {
		super ("Gestione Evento");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Evento");
		chapters.add("Aggiungi POI ad un Evento");
		chapters.add("Aggiorna un Evento");
		this.setChapters(chapters);
	}

	@Override
	public void setAuthorization() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void populateLinksTable() {
		// TODO Auto-generated method stub
		
	}
}
