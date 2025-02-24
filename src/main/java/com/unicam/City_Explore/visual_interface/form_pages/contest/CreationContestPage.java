package com.unicam.City_Explore.visual_interface.form_pages.contest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class CreationContestPage extends FormPage {
	@Autowired
	private ContestService contestService;
	
	public CreationContestPage() {
		super("Creazione di un nuovo Contest da zero");
	}

	@Override
	public void startForm(Scanner scanner) {
		 System.out.println("=== Creazione di un Contest ===");

	        System.out.print("Inserisci il nome: ");
	        String name = scanner.nextLine();

	        System.out.print("Inserisci la descrizione: ");
	        String description = scanner.nextLine();

	        System.out.print("Inserisci le regole: ");
	        String rules = scanner.nextLine();

	        System.out.print("Inserisci l'obiettivo: ");
	        String goal = scanner.nextLine();

	        System.out.print("Inserisci il premio: ");
	        String prize = scanner.nextLine();

	        System.out.print("Inserisci la scadenza (il formato e' dd-MM-yyyy): ");
	        String deadlineString = scanner.nextLine();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate deadline = null;

	        try {
	            deadline = LocalDate.parse(deadlineString, formatter);
	            System.out.println("Data convertita: " + deadline);
	        } catch (DateTimeParseException e) {
	            System.out.println("Formato della data non valido: " + e.getMessage());
	        }
	        
	        Contest contest = contestService.createContest(name, description, rules, goal, prize, deadline);
	        
	        System.out.println("Contest creato da zero: ");
	        System.out.println(this.contestService.searchContestByName(contest.getName()));
		
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
