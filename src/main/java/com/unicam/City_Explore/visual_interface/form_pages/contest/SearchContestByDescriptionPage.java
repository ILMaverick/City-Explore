package com.unicam.City_Explore.visual_interface.form_pages.contest;

import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class SearchContestByDescriptionPage extends FormPage {

	@Autowired
	private ContestService contestService;
	
	public SearchContestByDescriptionPage() {
		super("Ricerca Contest tramite descrizione");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Contest> contestList = contestService.searchContestByDescription(description);
        if(contestList.isEmpty()) {
            System.out.println("Non e' presente un Contest con questa descrizione.");
        } else {
            System.out.println("Elenco Contest con la descrizione cercata:");
            for(Contest contest: contestList) {
                System.out.println(contest);
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
