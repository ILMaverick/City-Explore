package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.form_pages.RegistrationPage;

@Component
public class WelcomePage extends MenuPage{

	@Autowired
	private LoginPage loginPage;
	private RegistrationPage registrationPage;
	
	public WelcomePage() {
		super ("Benvenuto!");
		this.getChapters().add("Login");
		this.getChapters().add("Registrati");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Login", Role.values());
		this.authService.addAuthorization("Registrati", Role.values());
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Login", this.loginPage);
		this.getLinksTable().put("Registrati", this.registrationPage);
	}
}
