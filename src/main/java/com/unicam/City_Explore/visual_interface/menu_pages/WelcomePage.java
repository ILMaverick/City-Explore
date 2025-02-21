package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.EliminazionePage;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidazionePage;

@Component
public class WelcomePage extends MenuPage{

	@Autowired
	private LoginPage loginPage;
	@Autowired
	private GestioneTOURPage gestioneTOURPage;
	@Autowired
	private GestioneContestPage gestioneContestPage;
	
	public WelcomePage() {
		super ("Benvenuto!");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Login");
		chapters.add("Registrati");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) throws IllegalArgumentException {
		switch (idChapter) {
		case 1: {
			return this.loginPage;
		}
//		case 2: {
//			return this.RegistrationPage;
//		}
		default:
			throw new IllegalArgumentException("Scelta non valida: " + idChapter);
		}
	}
}
