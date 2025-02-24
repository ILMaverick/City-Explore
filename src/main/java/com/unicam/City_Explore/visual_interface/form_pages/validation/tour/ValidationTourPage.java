package com.unicam.City_Explore.visual_interface.form_pages.validation.tour;

import java.util.List;
import java.util.Scanner;


import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.validazione.ValidationService;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ValidationTourPage extends FormPage {

	@Autowired
	private ValidationService validationService;
	
	public ValidationTourPage() {
		super("Valida Tour Pendenti");
	}

	@Override
	public void startForm(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("=== Validazione Itinerari Pendenti ===");

		List<Tour> tourList = validationService.getAllPendingTour();
		for(Tour t: tourList) {
			System.out.println(t);
			System.out.print("1 per approvare, 2 per rifiutare, 3 per aggiornare: ");
			int num = scanner.nextInt();
			if(num == 1) {
				validationService.approveTour(t.getId());
				System.out.println("Itinerario Approvato.");
			} else if(num == 2) {
				System.out.print("Inserisci una motivazione per il rifiuto: ");
				String reason = scanner.nextLine();
				validationService.rejectTour(t.getId(), reason);
				System.out.println("Itinerario Rifiutato " + reason + ".");
			} else if(num == 3) {
				validationService.updateTour(t.getId());
				System.out.println("Itinerario in stato di aggiornamento.");
			} else {
				System.out.println("Opzione inesistente, l'Itinerario e' ancora pendente.");
			}
		}
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
