package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.UserService;

@Component
public class RegistrationPage extends FormPage {
	
	@Autowired
	private UserService userService;
	
	public RegistrationPage() {
		super("Inserisci i tuoi dati");
	}

	@Override
	public void startForm(Scanner scanner) {
		
        System.out.print("Inserisci il tuo nome: ");
        String name = scanner.nextLine();
	}

}
