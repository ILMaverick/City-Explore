package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class LoginPage extends FormPage {

	@Autowired
	private HomePage homePage;
	@Autowired
	private UserService userService;
	
	public LoginPage() {
		super("Login (Seleziona un accesso)");
		
	}
	
//	@Override
//	public void setAuthorization() {
//		this.authService.addAuthorization("Turista", Role.values());
//		this.authService.addAuthorization("Turista Autenticato", Role.values());
//		this.authService.addAuthorization("Contributore", Role.values());
//		this.authService.addAuthorization("Contributore Autorizzato", Role.values());
//		this.authService.addAuthorization("Curatore", Role.values());
//		this.authService.addAuthorization("Animatore", Role.values());
//		this.authService.addAuthorization("Amministratore", Role.values());
//	}
//
//	@Override
//	public void populateLinksTable() {
//		this.getLinksTable().put("Turista", this.homePage);
//		this.getLinksTable().put("Turista Autenticato", this.homePage);
//		this.getLinksTable().put("Contributore", this.homePage);
//		this.getLinksTable().put("Contributore Autorizzato", this.homePage);
//		this.getLinksTable().put("Curatore", this.homePage);
//		this.getLinksTable().put("Animatore", this.homePage);
//		this.getLinksTable().put("Amministratore", this.homePage);
//	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("1. Turista");
		System.out.println("2. Turista Autenticato");
		System.out.println("3. Contributore");
		System.out.println("4. Contributore Autorizzato");
		System.out.println("5. Curatore");
		System.out.println("6. Animatore");
		System.out.println("7. Amministratore");
		switch (scanner.nextInt()) {
		case 1:
			userService.getCurrentUser().setRole(Role.TOURIST);
			break;
		case 2:
			userService.getCurrentUser().setRole(Role.AUTHENTICATED_TOURIST);
			break;
		case 3:
			userService.getCurrentUser().setRole(Role.CONTRIBUTOR);
			break;
		case 4:
			userService.getCurrentUser().setRole(Role.AUTORIZED_CONTRIBUTOR);
			break;
		case 5:
			userService.getCurrentUser().setRole(Role.CURATOR);
			break;
		case 6:
			userService.getCurrentUser().setRole(Role.ANIMATOR);
			break;
		case 7:
			userService.getCurrentUser().setRole(Role.ADMINISTRATOR);
			break;

		default:
			this.startForm(scanner);
			break;
		}
		this.setNext(homePage);
	}
}
