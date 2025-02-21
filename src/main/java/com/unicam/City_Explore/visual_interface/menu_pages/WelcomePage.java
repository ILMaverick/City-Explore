package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.autorizzazione.AuthorizationService;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.EliminationPage;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.form_pages.ValidationPage;

@Component
public class WelcomePage extends MenuPage{

	@Autowired
	private LoginPage loginPage;
	
	public WelcomePage() {
		super ("Benvenuto!");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Login");
		this.authService.addAuthorization("Login", Role.values());
		chapters.add("Registrati");
		this.authService.addAuthorization("Registrati", Role.values());
		this.setChapters(chapters);
	}
}
