package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;

@Component
public class ManageValidationPage extends MenuPage {

	public ManageValidationPage() {
		super ("Gestione Validazione");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Mostra POI Pendenti");
		chapters.add("Mostra Itinerari Pendenti");
		chapters.add("Mostra Contenuti Multimediali Pendenti");
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
