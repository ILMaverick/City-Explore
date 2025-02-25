package com.unicam.City_Explore.visual_interface.form_pages.contenuti;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentService;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class DeleteContentPage extends FormPage {
	@Autowired
	private MultimediaContentService multimediaContentService;
	@Autowired
	private DeletionService deletionService;

	public DeleteContentPage() {
		super("Eliminazione Contenuto");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Eliminazione Contenuto ===");
		List<MultimediaContent> contentList = multimediaContentService.getAllMultimediaContent();
        if (contentList == null || contentList.isEmpty()) {
            System.out.println("Nessun Contenuto disponibile per l'eliminazione.");
            return;
        }

        // Visualizza la lista dei Contenuti con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < contentList.size(); i++) {
            System.out.println((i + 1) + ". " + contentList.get(i));
        }
        
     // L'utente seleziona il Contenuto da includere (inserisci un solo numero)
        System.out.print("Seleziona il Contenuto da eliminare: (inserisci il numero corrispondente)");
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido: " + input);
            return;
        }

        if (index < 1 || index > contentList.size()) {
            System.out.println("Indice fuori range. Selezione non valida.");
            return;
        }

        MultimediaContent selectedContent = contentList.get(index - 1);
        
        System.out.println("Sei sicuro di voler eliminare il Punto di Interesse? (si/no)");
        String risposta = scanner.nextLine().trim().toLowerCase();
        String reason = "";
        if (risposta.equals("s") || risposta.equals("si")) {
            System.out.println("Eliminazione confermata.");
            System.out.print("Aggiungi una motivazione per l'eliminazione: ");
            reason = scanner.nextLine();
            deletionService.deleteMultimediaContent(selectedContent.getId(), reason);
        } else if (risposta.equals("n") || risposta.equals("no")) {
            System.out.println("Operazione annullata.");
        } else {
            System.out.println("Risposta non valida. Operazione annullata.");
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}

}
