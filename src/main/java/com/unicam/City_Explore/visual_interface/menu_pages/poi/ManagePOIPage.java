package com.unicam.City_Explore.visual_interface.menu_pages.poi;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.poi.CreationPOIOSMPage;
import com.unicam.City_Explore.visual_interface.form_pages.poi.CreationPOIUserPage;
import com.unicam.City_Explore.visual_interface.form_pages.poi.UpdatePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagePOIPage extends MenuPage {

	@Autowired
	private CreationPOIUserPage creationPOIUserPage;
	@Autowired
	private CreationPOIOSMPage creationPOIOSMPage;
	@Autowired
	private UpdatePOIPage updatePOIPage;
	
	public ManagePOIPage() {
		super ("Gestione POI");
		this.getChapters().add("Crea PointOfInterest da zero");
		this.getChapters().add("Crea PointOfInterest da OSM");
		this.getChapters().add("Aggiorna un POI");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Crea PointOfInterest da zero", Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Crea PointOfInterest da OSM", Role.CONTRIBUTOR, Role.AUTHORIZED_CONTRIBUTOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Aggiorna un POI", Role.CURATOR, Role.ADMINISTRATOR);
		
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Crea PointOfInterest da zero", this.creationPOIUserPage);
		this.getLinksTable().put("Crea PointOfInterest da OSM", this.creationPOIOSMPage);
		this.getLinksTable().put("Aggiorna un POI", this.updatePOIPage);
		
	}
}
