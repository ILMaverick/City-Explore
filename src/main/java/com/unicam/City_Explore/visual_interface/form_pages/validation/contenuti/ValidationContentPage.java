package com.unicam.City_Explore.visual_interface.form_pages.validation.contenuti;

import java.util.List;
import java.util.Scanner;


import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.validazione.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ValidationContentPage extends FormPage {

	@Autowired
	private ValidationService validationService;
	
	public ValidationContentPage() {
		super("Valida Contenuti Multimediali Pendenti");
	}

	@Override
	public void startForm(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("=== Validazione Contenuti Multimediali Pendenti ===");

		List<MultimediaContent> multimediaContentList = validationService.getAllPendingMultimediaContent();
		for(MultimediaContent mc: multimediaContentList) {
			System.out.println(mc);
			System.out.print("1 per approvare, 2 per rifiutare, 3 per aggiornare: ");
			int num = scanner.nextInt();
			if(num == 1) {
				validationService.approveMultimediaContent(mc.getId());
				System.out.println("Contenuto Approvato.");
			} else if(num == 2) {
				System.out.print("Inserisci una motivazione per il rifiuto: ");
				String reason = scanner.nextLine();
				validationService.rejectMultimediaContent(mc.getId(), reason);
				System.out.println("Contenuto Rifiutato " + reason + ".");
			} else if(num == 3) {
				validationService.updatePOI(mc.getId());
				System.out.println("Contenuto in stato di aggiornamento.");
			} else {
				System.out.println("Opzione inesistente, il Contenuto e' ancora pendente.");
			}
		}
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
