package com.unicam.City_Explore.visual_interface;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class PageExecutioner {
	
	@Autowired
	private UserService userService;
	private Scanner scanner = new Scanner(System.in);
	
	public int executeMenu(MenuPage toExecute, ArrayList<String> authorizedPages) {
		this.showMenuPage(toExecute.getTitle(), authorizedPages);
		int idChapter = this.scanner.nextInt();
		scanner.nextLine();
		return idChapter;		
	}
	
	public void executeForm(FormPage toExecute) {
		System.out.println("=== CITY EXPLORE ===\n");
		if (this.userService.getCurrentUser() != null) {
		System.out.println("=== " + userService.getCurrentUser().getUsername() + "(" + userService.getCurrentUser().getRole() + ")" + " ===\n");
		}
		System.out.println("=== " + toExecute.getTitle() + " ===");
		toExecute.startForm(scanner);
	}
	
	private void showMenuPage(String title, ArrayList<String> chapters) {
		System.out.println("=== CITY EXPLORE ===\n");
		if (this.userService.getCurrentUser() != null) {
		System.out.println("=== " + userService.getCurrentUser().getUsername() + "(" + userService.getCurrentUser().getRole() + ")" + " ===\n");
		}
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
