package com.unicam.City_Explore.visual_interface;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class FormPageBuilder implements PageBuilder<FormPage>{
	
	@Autowired
	private UserService userService;
	
	@Override
	public Page buildPage(FormPage toExecute, Scanner scanner) {
		System.out.println("=== CITY EXPLORE ===\n");
		if (this.userService.getCurrentUser() != null) {
		System.out.println("=== " + userService.getCurrentUser().getUsername() + "(" + userService.getCurrentUser().getRole() + ")" + " ===\n");
		}
		System.out.println("=== " + toExecute.getTitle() + " ===");
		toExecute.startForm(scanner);
		if (toExecute.getNext().getPrevious() == null) {
			toExecute.getNext().setPrevious(toExecute.getPrevious());
		}
		return toExecute.getNext();
	}
}
