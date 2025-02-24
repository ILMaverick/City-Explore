package com.unicam.City_Explore.visual_interface.menu_pages.tour;

import java.util.ArrayList;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.tour.CreationTourPage;
import com.unicam.City_Explore.visual_interface.form_pages.tour.UpdateTourPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageTourPage extends MenuPage {
	@Autowired
	private CreationTourPage creationTourPage;
	
	@Autowired
	private UpdateTourPage updateTourPage;

	public ManageTourPage() {
		super ("Gestione TOUR");
		this.getChapters().add("Crea Tour da POI");
		this.getChapters().add("Aggiorna un Tour");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Crea Tour da POI", Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Aggiorna un Tour", Role.CURATOR);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Crea Tour da POI", this.creationTourPage);
		this.getLinksTable().put("Aggiorna un Tour", this.updateTourPage);
		
	}
}
