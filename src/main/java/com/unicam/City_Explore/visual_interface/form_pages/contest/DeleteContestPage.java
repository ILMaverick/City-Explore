package com.unicam.City_Explore.visual_interface.form_pages.contest;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestService;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class DeleteContestPage extends FormPage{
	@Autowired
	private ContestService contestService;
	@Autowired
	private DeletionService deletionService;

	public DeleteContestPage() {
		super("Eliminazione Contest");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.println("=== Eliminazione Contest===");
		List<Contest> contestList = contestService.getAllContest();
        if (contestList == null || contestList.isEmpty()) {
            System.out.println("Nessun Contest disponibile per l'eliminazione.");
            return;
        }

        // Visualizza la lista dei Contest con indice
        System.out.println("Elenco dei Contest disponibili:");
        for (int i = 0; i < contestList.size(); i++) {
            System.out.println((i + 1) + ". " + contestList.get(i));
        }
        
     // L'utente seleziona il Contest da includere (inserisci un solo numero)
        System.out.print("Seleziona il Contest da eliminare: (inserisci il numero corrispondente)");
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
        List<Event> eventList = selectedContest.getEventList();
        List<MultimediaContent> multimediaContentList = selectedContest.getMultimediaContentList();
        if(eventList.isEmpty()) {
            System.out.println("Non ci sono Eventi associati a questo Contest.");
        } else {
            System.out.print("Il Contest da eliminare è associato a questi Eventi: ");
            for(Contest e: contestList) {
                System.out.println(e);
            }
        }
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non ci sono Contenuti associati a questo Contest.");
        } else {
            System.out.print("Il Contest da eliminare ha questi Contenuti: ");
            for(MultimediaContent mc: multimediaContentList) {
                System.out.println(mc);
            }
        }
        
        System.out.println("Sei sicuro di voler eliminare il Contest? (si/no)");
        String risposta = scanner.nextLine().trim().toLowerCase();
        String reason = "";
        if (risposta.equals("s") || risposta.equals("si")) {
            System.out.println("Eliminazione confermata.");
            System.out.print("Aggiungi una motivazione per l'eliminazione: ");
            reason = scanner.nextLine();
            deletionService.deleteContest(selectedContest.getId(), reason);
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
