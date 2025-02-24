package com.unicam.City_Explore.visual_interface.form_pages.validation.poi;

import java.util.List;
import java.util.Scanner;


import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.validazione.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ValidationPOIPage extends FormPage {

	@Autowired
	private ValidationService validationService;
	
	public ValidationPOIPage() {
		super("Valida POI Pendenti");
	}

	@Override
	public void startForm(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("=== Validazione Punti di Interesse Pendenti ===");

		List<PointOfInterest> pointOfInterestList = validationService.getAllPendingPOI();
		for(PointOfInterest p: pointOfInterestList) {
			System.out.println(p);
			System.out.print("1 per approvare, 2 per rifiutare, 3 per aggiornare: ");

			int num = scanner.nextInt();

			if(num == 1) {
				validationService.approvePOI(p.getId());
				System.out.println("Punto di Interesse Approvato.");
			} else if(num == 2) {
				System.out.print("Inserisci una motivazione per il rifiuto: ");
				String reason = scanner.nextLine();
				validationService.rejectPOI(p.getId(), reason);
				System.out.println("Punto di Interesse Rifiutato " + reason + ".");
			} else if(num == 3) {
				validationService.updatePOI(p.getId());
				System.out.println("Punto di Interesse in stato di aggiornamento.");
			} else {
				System.out.println("Opzione inesistente, il Punto di Interesse e' ancora pendente.");
			}
		}
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
