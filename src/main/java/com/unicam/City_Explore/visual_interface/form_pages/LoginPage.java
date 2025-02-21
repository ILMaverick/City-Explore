package com.unicam.City_Explore.visual_interface.form_pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class LoginPage extends MenuPage {

	@Autowired
	private HomePage homePage;
	
	public LoginPage() {
		super("Login (Seleziona un accesso)");
//		this.populateChapters();
//		this.populateLinksTable();
//		this.setAuthorization();
	}
	
	private void setAuthorization() {
		this.authService.addAuthorization("Turista", Role.values());
		this.authService.addAuthorization("Turista Autenticato", Role.values());
		this.authService.addAuthorization("Contributore", Role.values());
		this.authService.addAuthorization("Contributore Autorizzato", Role.values());
		this.authService.addAuthorization("Curatore", Role.values());
		this.authService.addAuthorization("Animatore", Role.values());
		this.authService.addAuthorization("Amministratore", Role.values());
	}
	
	private void populateChapters() {
		this.getChapters().add("Turista");
		this.getChapters().add("Turista Autenticato");
		this.getChapters().add("Contributore");
		this.getChapters().add("Contributore Autorizzato");
		this.getChapters().add("Curatore");
		this.getChapters().add("Animatore");
		this.getChapters().add("Amministratore");
	}
	
	private void populateLinksTable() {
		this.getLinksTable().put("Turista", this.homePage);
		this.getLinksTable().put("Turista Autenticato", this.homePage);
		this.getLinksTable().put("Contributore", this.homePage);
		this.getLinksTable().put("Contributore Autorizzato", this.homePage);
		this.getLinksTable().put("Curatore", this.homePage);
		this.getLinksTable().put("Animatore", this.homePage);
		this.getLinksTable().put("Amministratore", this.homePage);
	}
}
