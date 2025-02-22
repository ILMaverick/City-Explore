package com.unicam.City_Explore.visual_interface.menu_pages.tour;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageTourPage extends MenuPage {

	public ManageTourPage() {
		super ("Gestione TOUR");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Tour da POI");
		chapters.add("Aggiorna un Tour");
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
