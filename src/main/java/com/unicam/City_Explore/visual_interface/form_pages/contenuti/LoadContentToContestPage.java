package com.unicam.City_Explore.visual_interface.form_pages.contenuti;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.FormatFileEnum;
import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentService;
import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class LoadContentToContestPage extends FormPage {
	
	@Autowired
	private ContestService contestService;
	@Autowired
	private MultimediaContentService contentService;
	
	public LoadContentToContestPage() {
		super("Creazione di un nuovo PointOfInterest da zero");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Caricamento Contenuto Multimediale su un Contest ===");
		 
		 List<Contest> contestList = contestService.getAllContest();
	        if (contestList == null || contestList.isEmpty()) {
	            System.out.println("Nessun Contest disponibile per aggiungere un contenuto.");
	            return;
	        }

	        // Visualizza la lista dei Contest con indice
	        System.out.println("Elenco dei Contest disponibili:");
	        for (int i = 0; i < contestList.size(); i++) {
	            System.out.println((i + 1) + ". " + contestList.get(i));
	        }
	        
	     // L'utente seleziona il Contest da includere (inserisci un solo numero)
	        System.out.print("Seleziona il Contest a cui aggiungere un Contenuto: (inserisci il numero corrispondente)");
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
	        System.out.println("Hai selezionato il Contest: " + selectedContest);
	        
	        System.out.print("Inserisci il nome del Contenuto: ");
	        String name = scanner.nextLine();
	        
	        System.out.print("Inserisci la descrizione del Contenuto: ");
	        String description = scanner.nextLine();
	        
	        System.out.print("Inserisci il formato: ");
	        String formatString = scanner.nextLine();
	        FormatFileEnum format = FormatFileEnum.formatFile(formatString);
	        
	        System.out.print("Inserisci la durata: ");
	        float duration = scanner.nextFloat();

	        System.out.print("Inserisci la dimensione: ");
	        float dimension = scanner.nextFloat();

	        System.out.print("Inserisci la risoluzione: ");
	        float resolution = scanner.nextFloat();
	        MultimediaContent content = contentService.createMultimediaContent(name, description, format, duration, dimension, resolution);
	        
	        contentService.loadMultimediaContentToEvent(selectedContest.getId(), content.getId());
	        
	        System.out.println("Contenuto Multimediale aggiunto al Contest: ");
	        System.out.println(this.contentService.getMultimediaContentById(content.getId()));
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
