package com.unicam.City_Explore.visual_interface;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.autorizzazione.AuthorizationService;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;
import com.unicam.City_Explore.visual_interface.menu_pages.WelcomePage;

@Component
public class MenuPageBuilder implements PageBuilder<MenuPage>{
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorizationService authService;
	
	@Override
	public Page buildPage(MenuPage toExecute, Scanner scanner) {
		toExecute.setAuthorization();
		toExecute.populateLinksTable();
		ArrayList<String> authPages = this.getAuthorizedPages(toExecute);
		this.showMenuPage(toExecute, authPages);
		int idChapter = scanner.nextInt();
		scanner.nextLine();
		if (idChapter == 0) {
			if(toExecute instanceof HomePage) {
				this.userService.setCurrentUser(null);
			}
			return toExecute.getPrevious();
		} else {
			Page toReturn = toExecute.getLinksTable().get(authPages.get(idChapter - 1));
			if (toReturn.getPrevious() == null) {
				toReturn.setPrevious(toExecute);
			}
			return toReturn;
		}
	}
	
	private void showMenuPage(MenuPage toShow, ArrayList<String> pagesToShow) {
		System.out.println("=== CITY EXPLORE ===\n");
		if (this.userService.getCurrentUser() != null) {
		System.out.println("=== " + userService.getCurrentUser().getUsername() + "(" + userService.getCurrentUser().getRole() + ")" + " ===\n");
		}
		System.out.println("=== " + toShow.getTitle() + " ===");
		
		for (int id = 0; id < pagesToShow.size(); id++) {
			System.out.println((id + 1) + ". " + pagesToShow.get(id));
		}
		
		System.out.print("0. Esci\n\nSeleziona un'opzione:");
	}
	
	private ArrayList<String> getAuthorizedPages(MenuPage menu){
		ArrayList<String> authorizedPages = new ArrayList<String>();
		if (menu instanceof WelcomePage) {
			authorizedPages = menu.getChapters();
		} else {
			for (String chapter : menu.getChapters()) {
				if (authService.checkAuthorization(chapter)) {
					authorizedPages.add(chapter);
				}
			}
		}
		return authorizedPages;
	}
}
