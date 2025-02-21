package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.CreationPOIOSMPage;
import com.unicam.City_Explore.visual_interface.form_pages.CreationPOIUserPage;
import com.unicam.City_Explore.visual_interface.form_pages.EliminationPage;
import com.unicam.City_Explore.visual_interface.form_pages.SearchPOIByDescriptionPage;
import com.unicam.City_Explore.visual_interface.form_pages.SearchPOIByNamePage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidationPage;
import com.unicam.City_Explore.visual_interface.form_pages.ShowPOISavedPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagePOIPage extends MenuPage {

	@Autowired
	private CreationPOIUserPage creazionePOIUtentePage;
	@Autowired
	private CreationPOIOSMPage creazionePOIOSMPage;
	@Autowired
	private ShowPOISavedPage visualizzazionePOISalvatiPage;
	@Autowired
	private SearchPOIByNamePage ricercaPOINome;
	@Autowired
	private SearchPOIByDescriptionPage ricercaPOIDescrizione;
	
	public ManagePOIPage() {
		super ("Gestione POI");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea PointOfInterest da zero");
		chapters.add("Crea PointOfInterest da OSM");
		chapters.add("Visualizza tutti i PointOfInterest salvati");
		chapters.add("Ricerca Punto di Interesse tramite nome");
		chapters.add("Ricerca Punto di Interesse tramite descrizione");
		this.setChapters(chapters);
	}
}
