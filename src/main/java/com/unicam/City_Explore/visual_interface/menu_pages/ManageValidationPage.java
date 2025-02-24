package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;

@Component
public class ManageValidationPage extends MenuPage {

	public ManageValidationPage() {
		super ("Gestione Validazione");
		this.getChapters().add("Valida POI Pendenti");
		this.getChapters().add("Valida Tour Pendenti");
		this.getChapters().add("Valida Contenuti Multimediali Pendenti");
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
