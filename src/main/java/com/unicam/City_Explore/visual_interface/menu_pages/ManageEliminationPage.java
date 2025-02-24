package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.stereotype.Component;

@Component
public class ManageEliminationPage extends MenuPage{

	public ManageEliminationPage() {
		super ("Gestione Eliminazione");
		this.getChapters().add("Elimina POI");
		this.getChapters().add("Elimina Itinerario");
		this.getChapters().add("Elimina Contest");
		this.getChapters().add("Elimina Evento");
		this.getChapters().add("Elimina Contenuto");
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
