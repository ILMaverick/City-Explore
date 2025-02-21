package com.unicam.City_Explore.visual_interface;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;
import com.unicam.City_Explore.visual_interface.menu_pages.WelcomePage;

@Component
public class PageExecutioner {

	@Autowired
	private UserService userService;
	private Scanner scanner = new Scanner(System.in);
	
	public Page executeMenu(MenuPage toExecute) {
		System.out.println("=== CITY EXPLORE ===");
		System.out.println("=== " + toExecute.getTitle() + " ===");
		for (int id = 0; id < toExecute.getChapters().size(); id++) {
			System.out.println((id + 1) + ". " + toExecute.getChapters().get(id));
		}
		System.out.print("0. Esci\n\nSeleziona un'opzione:");
		int idChapter = this.scanner.nextInt();
		scanner.nextLine();
		if (idChapter == 0) {
			if (toExecute instanceof WelcomePage) {
				return null;
			} else {
				return toExecute.getPrevious();
			}
		} else {
			Page toReturn = toExecute.getNext(idChapter);
			if (toReturn.getPrevious() == null) {
				toReturn.setPrevious(toExecute);
			}
			return toReturn;
		}
	}
	
	public MenuPage executeForm(FormPage toExecute) {
		System.out.println("=== " + toExecute.getTitle() + " ===");
		toExecute.startForm(userService.getCurrentUser(), scanner);
		return toExecute.getPrevious();
	}
	
	private void showPage() {
		
	}

	public void close() {
		this.scanner.close();
	}
}
