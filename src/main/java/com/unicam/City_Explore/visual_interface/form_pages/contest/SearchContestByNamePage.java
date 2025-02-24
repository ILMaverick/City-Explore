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
public class SearchContestByNamePage extends FormPage {

	@Autowired
	private ContestService contestService;
	
	public SearchContestByNamePage() {
		super("Ricerca Contest tramite nome");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();
        List<Contest> contestList = contestService.searchContestByName(name);
        if(contestList.isEmpty()) {
            System.out.println("Non e' presente un Contest con questo nome.");
        } else {
            System.out.println("Elenco Contest con il nome cercato:");
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
