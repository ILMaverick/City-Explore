package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class LoginPage extends MenuPage {

	@Autowired
	private HomePage homePage;
	
	public LoginPage() {
		super("Login (Seleziona un accesso)");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Turista");
		chapters.add("Turista Autenticato");
		chapters.add("Contributore");
		chapters.add("Contributore Autorizzato");
		chapters.add("Curatore");
		chapters.add("Animatore");
		chapters.add("Amministratore");
		this.setChapters(chapters);
	}

	@Override
	public Page getNext(int idChapter) {
		return this.homePage;
	}

}
