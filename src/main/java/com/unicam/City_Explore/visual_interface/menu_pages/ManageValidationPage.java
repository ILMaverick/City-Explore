package com.unicam.City_Explore.visual_interface.menu_pages;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.validation.contenuti.ValidationContentPage;
import com.unicam.City_Explore.visual_interface.form_pages.validation.contenuti.ValidationReportedContentPage;
import com.unicam.City_Explore.visual_interface.form_pages.validation.poi.ValidationPOIPage;
import com.unicam.City_Explore.visual_interface.form_pages.validation.tour.ValidationTourPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageValidationPage extends MenuPage {

	@Autowired
	private ValidationPOIPage validationPOIPage;
	@Autowired
	private ValidationTourPage validationTourPage;
	@Autowired
	private ValidationContentPage validationContentPage;
	@Autowired
	private ValidationReportedContentPage validationReportedContentPage;

	public ManageValidationPage() {
		super ("Gestione Validazione");
		this.getChapters().add("Valida POI Pendenti");
		this.getChapters().add("Valida Itinerari Pendenti");
		this.getChapters().add("Valida Contenuti Multimediali Pendenti");
		this.getChapters().add("Valida Contenuti Multimediali Segnalati");
	}

	@Override
	public void setAuthorization() {
		// TODO Auto-generated method stub
		this.authService.addAuthorization("Valida POI Pendenti", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Valida Itinerari Pendenti", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Valida Contenuti Multimediali Pendenti", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Valida Contenuti Multimediali Segnalati", Role.CURATOR, Role.ADMINISTRATOR);
	}

	@Override
	public void populateLinksTable() {
		// TODO Auto-generated method stub
		this.getLinksTable().put("Valida POI Pendenti", validationPOIPage);
		this.getLinksTable().put("Valida Itinerari Pendenti", validationTourPage);
		this.getLinksTable().put("Valida Contenuti Multimediali Pendenti", validationContentPage);
		this.getLinksTable().put("Valida Contenuti Multimediali Segnalati", validationReportedContentPage);
	}
}
