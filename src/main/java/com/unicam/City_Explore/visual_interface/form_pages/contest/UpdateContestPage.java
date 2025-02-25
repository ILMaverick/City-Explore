package com.unicam.City_Explore.visual_interface.form_pages.contest;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class UpdateContestPage extends FormPage {
	@Autowired
	private ContestService contestService;

	public UpdateContestPage() {
		super("Aggiorna Contest");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<Contest> contestList = contestService.getAllContest();
        if (contestList == null || contestList.isEmpty()) {
            System.out.println("Nessun Contest disponibile per aggiornamento.");
            return;
        }

        // Visualizza la lista dei Contest con indice
        System.out.println("Elenco dei Contest disponibili:");
        for (int i = 0; i < contestList.size(); i++) {
            System.out.println((i + 1) + ". " + contestList.get(i));
        }
        
     // L'utente seleziona il Contest da includere (inserisci un solo numero)
        System.out.print("Seleziona il Contest da aggiornare: (inserisci il numero corrispondente)");
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido: " + input);
            return;
        }

        if (index < 1 || index > contestList.size()) {
            System.out.println("Indice fuori range. Selezione non valida.");
            return;
        }
        
        Contest selectedContest = contestList.get(index - 1);
        
        System.out.print("Nome corrente (" + selectedContest.getName() + "). Inserisci nuovo nome -String- (premi invio per saltare): ");
        String name = scanner.nextLine();

        System.out.print("Descrizione corrente (" + selectedContest.getDescription() + "). Inserisci nuova descrizione -String- (premi invio per saltare): ");
        String description = scanner.nextLine();
        
        System.out.print("Regole corrente (" + selectedContest.getRules() + "). Inserisci nuova regola -String- (premi invio per saltare): ");
        String roles = scanner.nextLine();
        
        System.out.print("Obiettivo corrente (" + selectedContest.getGoal() + "). Inserisci il nuovo obiettivo -String- (premi invio per saltare): ");
        String goal = scanner.nextLine();
        
        System.out.print("Prezzo corrente (" + selectedContest.getPrize() + "). Inserisci il nuovo prezzo -String- (premi invio per saltare): ");
        String price = scanner.nextLine();
        
		
		selectedContest = contestService.updateContest(selectedContest.getId(), name, description, roles, goal, price);
		System.out.println("Evento aggiornato: ");
		System.out.println(selectedContest);
		}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
