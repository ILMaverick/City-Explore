package com.unicam.City_Explore.visual_interface;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;
import com.unicam.City_Explore.visual_interface.menu_pages.WelcomePage;

@Component
public class PageController implements CommandLineRunner{
	
	@Autowired
	private WelcomePage welcomePage;
	@Autowired
	private UserService userService;
	@Autowired
	private MenuPageBuilder menuPageBuilder;
	@Autowired
	private FormPageBuilder formPageBuilder;
	
	private Page pointerPage = welcomePage;
	
	private Scanner scanner = new Scanner(System.in);
	
	public PageController() {
		
	}
	
	@Override
	public void run(String... args) {
		this.inizialize();
		this.pointerPage = this.welcomePage;
		while (this.pointerPage != null) {
			this.execute(this.pointerPage);
		}
		this.close();
	}
	
	private void inizialize() {
		this.userService.createUser("CURATOR", "CURATOR", "CURATOR", "CURATOR@gmail.com", "CURATOR", Role.CURATOR);		
		this.userService.createUser("ADMINISTRATOR", "ADMINISTRATOR", "ADMINISTRATOR", "ADMINISTRATOR@gmail.com", "ADMINISTRATOR", Role.ADMINISTRATOR);		
		this.userService.createUser("ANIMATOR", "ANIMATOR", "ANIMATOR", "ANIMATOR@gmail.com", "ANIMATOR", Role.ANIMATOR);
		this.userService.createUser("CONTRIBUTOR", "CONTRIBUTOR", "CONTRIBUTOR", "CONTRIBUTOR@gmail.com", "CONTRIBUTOR", Role.CONTRIBUTOR);
		this.userService.createUser("AUTORIZED_CONTRIBUTOR", "AUTORIZED_CONTRIBUTOR", "AUTORIZED_CONTRIBUTOR", "AUTORIZED_CONTRIBUTOR@gmail.com", "AUTORIZED_CONTRIBUTOR", Role.AUTORIZED_CONTRIBUTOR);
		this.userService.createUser("TOURIST", "TOURIST", "TOURIST", "TOURIST@gmail.com", "TOURIST", Role.TOURIST);
		this.userService.createUser("AUTHENTICATED_TOURIST", "AUTHENTICATED_TOURIST", "AUTHENTICATED_TOURIST", "AUTHENTICATED_TOURIST@gmail.com", "AUTHENTICATED_TOURIST", Role.AUTHENTICATED_TOURIST);
	}

	private void execute(Page toExecute) {
		if (toExecute instanceof MenuPage) {
			MenuPage menuToExecute = (MenuPage) toExecute;
			this.next(this.menuPageBuilder.buildPage(menuToExecute, scanner));
			
		} else {
			FormPage formToExecute = (FormPage) toExecute;
			this.next(this.formPageBuilder.buildPage(formToExecute, scanner));
		}
	}
	
	private void next(Page nextPage) {
		this.pointerPage = nextPage;
	}
	
	private void close() {
		this.scanner.close();
		System.out.println("Programma terminato. Arrivederci!");
	}	
}
