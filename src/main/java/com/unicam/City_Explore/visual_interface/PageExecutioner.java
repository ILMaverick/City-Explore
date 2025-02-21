package com.unicam.City_Explore.visual_interface;

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
	
	public Page executeMenu(MenuPage toExecute) {
		System.out.println("=== " + toExecute.getTitle() + " ===");
		for (int id = 0; id < toExecute.getChapters().size(); id++) {
			System.out.println((id + 1) + ". " + toExecute.getChapters().get(id));
		}
		System.out.print("0. Esci\n\nSeleziona un'opzione:");
		int idChapter = this.scanner.nextInt();
		if (idChapter == 0) {
			return toExecute.getPrevious();
		} else {
			Page toReturn = toExecute.getNext(idChapter);
			toReturn.setPrevious(toExecute);
			return toReturn;
		}
	}
	
	public void executeForm(FormPage toExecute) {
		System.out.println("=== " + toExecute.getTitle() + " ===");
		toExecute.startForm(userService.getCurrentUser(), scanner);
	}

	public void close() {
		this.scanner.close();
	}
}
