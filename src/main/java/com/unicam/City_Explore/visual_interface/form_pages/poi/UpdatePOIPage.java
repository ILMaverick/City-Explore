package com.unicam.City_Explore.visual_interface.form_pages.poi;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class UpdatePOIPage extends FormPage {
	@Autowired
	private POIService poiService;

	public UpdatePOIPage() {
		super("Aggiorna POI");
	}

	@Override
	public void startForm(Scanner scanner) {
		 List<PointOfInterest> poiList = poiService.getAllPOIs();
	        if (poiList == null || poiList.isEmpty()) {
	            System.out.println("Nessun POI disponibile per aggiungere un contenuto.");
	            return;
	        }

	        // Visualizza la lista dei POI con indice
	        System.out.println("Elenco dei POI disponibili:");
	        for (int i = 0; i < poiList.size(); i++) {
	            System.out.println((i + 1) + ". " + poiList.get(i));
	        }
	        
	     // L'utente seleziona il POI da includere (inserisci un solo numero)
	        System.out.print("Seleziona il POI a cui aggiungere un Contenuto: (inserisci il numero corrispondente)");
	        String input = scanner.nextLine();
	        int index;
	        try {
	            index = Integer.parseInt(input.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Input non valido: " + input);
	            return;
	        }

	        if (index < 1 || index > poiList.size()) {
	            System.out.println("Indice fuori range. Selezione non valida.");
	            return;
	        }

	        PointOfInterest selectedPOI = poiList.get(index - 1);
	        System.out.print("Nome corrente (" + selectedPOI.getName() + "). Inserisci nuovo nome -String- (premi invio per saltare): ");
	        String nome = scanner.nextLine();

	        System.out.print("Descrizione corrente (" + selectedPOI.getDescription() + "). Inserisci nuova descrizione -String- (premi invio per saltare): ");
	        String descrizione = scanner.nextLine();

	        System.out.print("Latitudine corrente (" + selectedPOI.getLatitude() + "). Inserisci nuova latitudine -double- (premi invio per saltare): ");
	        String latInput = scanner.nextLine();
	        
	        System.out.print("Longitudine corrente (" + selectedPOI.getLongitude() + "). Inserisci nuova longitudine -double- (premi invio per saltare): ");
	        String lonInput = scanner.nextLine();

	        System.out.print("Tipo corrente (" + selectedPOI.getType() + "). Inserisci nuovo tipo -turismo, alloggio, servizio, natura, altro- (premi invio per saltare): ");
	        String tipoInput = scanner.nextLine();
	        
	        poiService.updatePOI(selectedPOI.getId(), nome, descrizione, latInput, lonInput, tipoInput);
	        
	        System.out.println("Hai aggiornato il seguente Punto di Interesse: ");
	        System.out.println(this.poiService.getPOIById(selectedPOI.getId()));
	        }

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
