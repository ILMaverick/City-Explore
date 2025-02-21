package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.EliminationPage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidationPage;

@Component
public class HomePage extends MenuPage{

	@Autowired
	private ManagePOIPage gestionePOIPage;
	@Autowired
	private ManageTOURPage gestioneTOURPage;
	@Autowired
	private ManageContestPage gestioneContestPage;
	@Autowired
	private ManageEventPage gestioneEventoPage;
	@Autowired
	private ManageContentPage gestioneContenutiPage;
	@Autowired
	private ValidationPage validazionePage;
	@Autowired
	private EliminationPage eliminazionePage;
	
	public HomePage() {
		super ("Menu Principale");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Gestione POI");
		chapters.add("Gestione Tour");
		chapters.add("Gestione Contest");
		chapters.add("Gestione Evento");
		chapters.add("Gestione Contenuti Multimediali");
		chapters.add("Validazione Elementi e Contenuti Pendenti");
		chapters.add("Eliminazione Elementi e Contenuti");
		this.setChapters(chapters);
	}
}
