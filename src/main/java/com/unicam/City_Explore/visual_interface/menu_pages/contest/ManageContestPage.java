package com.unicam.City_Explore.visual_interface.menu_pages.contest;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageContestPage extends MenuPage {

	public ManageContestPage() {
		super ("Gestione Contest");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Contest");
		chapters.add("Visualizza tutti i Contest salvati");
		chapters.add("Ricerca Contest tramite nome");
		chapters.add("Ricerca Contest tramite descrizione");
		this.setChapters(chapters);
	}
}
