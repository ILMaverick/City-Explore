package com.unicam.City_Explore.visual_interface.menu_pages.contest;

import java.util.ArrayList;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.stereotype.Component;

@Component
public class ManageContestPage extends MenuPage {

	public ManageContestPage() {
		super ("Gestione Contest");
		this.getChapters().add("Crea Contest");
		this.getChapters().add("Aggiorna un Contest");
		this.getChapters().add("Partecipa ad un Contest");
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
