package com.unicam.City_Explore.visual_interface.menu_pages.poi;

import java.util.ArrayList;

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
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea PointOfInterest da zero");
		chapters.add("Crea PointOfInterest da OSM");
		chapters.add("Aggiorna un POI");
		this.setChapters(chapters);
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Crea PointOfInterest da zero", Role.CONTRIBUTOR, Role.AUTORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Crea PointOfInterest da OSM", Role.CONTRIBUTOR, Role.AUTORIZED_CONTRIBUTOR);
		this.authService.addAuthorization("Aggiorna un POI", Role.CURATOR);
		
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Crea PointOfInterest da zero", this.creationPOIUserPage);
		this.getLinksTable().put("Crea PointOfInterest da OSM", this.creationPOIOSMPage);
		this.getLinksTable().put("Aggiorna un POI", this.updatePOIPage);
		
	}
}
