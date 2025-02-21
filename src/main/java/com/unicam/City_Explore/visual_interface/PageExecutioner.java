package com.unicam.City_Explore.visual_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.autorizzazione.AuthorizationService;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;
import com.unicam.City_Explore.visual_interface.menu_pages.WelcomePage;

@Component
public class PageExecutioner {
	
	@Autowired
	private AuthorizationService autService;
	private Scanner scanner = new Scanner(System.in);
	
	public String executeMenu(MenuPage toExecute) {
		ArrayList<String> authorizedPages = new ArrayList<String>();
		for (String chapter : toExecute.getChapters()) {
			if (autService.checkAutorization(chapter)) {
				authorizedPages.add(chapter);
			}
		}
		this.showMenuPage(toExecute.getTitle(), authorizedPages);
		
		int idChapter = this.scanner.nextInt();
		scanner.nextLine();
		return authorizedPages.get(idChapter);		
	}
	
	public MenuPage executeForm(FormPage toExecute) {
		System.out.println("=== " + toExecute.getTitle() + " ===");
		toExecute.startForm(scanner);
		return toExecute.getPrevious();
	}
	
	private void showMenuPage(String title, ArrayList<String> chapters) {
		System.out.println("=== CITY EXPLORE ===");
		System.out.println("=== " + title + " ===");
		
		for (int id = 0; id < chapters.size(); id++) {
			System.out.println((id + 1) + ". " + chapters.get(id));
		}
		
		System.out.print("0. Esci\n\nSeleziona un'opzione:");
	}

	public void close() {
		this.scanner.close();
	}
}
