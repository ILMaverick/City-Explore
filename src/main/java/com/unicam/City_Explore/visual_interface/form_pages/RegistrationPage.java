package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;

@Component
public class RegistrationPage extends FormPage {
	
	@Autowired
	private UserService userService;
	@Autowired
	private HomePage homePage;
	
	public RegistrationPage() {
		super("Inserisci i tuoi dati");
	}

	@Override
	public void startForm(Scanner scanner) {
		
        System.out.print("Inserisci il tuo nome: ");
        String name = scanner.nextLine();
        System.out.print("Inserisci il tuo cognome: ");
        String surname = scanner.nextLine();
        System.out.print("Inserisci il tuo username: ");
        String username = scanner.nextLine();
        System.out.print("Inserisci la tua email: ");
        String email = scanner.nextLine();
        System.out.print("Inserisci la password: ");
        String password = scanner.nextLine();
        User newUser = this.userService.createUser(name, surname, username, email, password, Role.TOURIST);
        this.userService.setCurrentUser(newUser);
        System.out.println("Ti sei Registrato!");
        System.out.println(newUser);
	}

	@Override
	public Page getNext() {
		return this.homePage;
	}

}
