package com.unicam.City_Explore.visual_interface.menu_pages.contenuti;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageContentPage extends MenuPage {

	public ManageContentPage() {
		super ("Gestione Contenuti Multimediali");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Carica Contenuto ad un POI");
		chapters.add("Carica Contenuto ad un Tour");
		chapters.add("Carica Contenuto ad un Contest");
		chapters.add("Carica Contenuto ad un Evento");
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
