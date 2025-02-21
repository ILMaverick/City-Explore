package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.visual_interface.menu_pages.GestionePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class RicercaPOIDescrizione extends FormPage {

	@Autowired
	private POIService poiService;
	
	public RicercaPOIDescrizione() {
		super("Ricerca Punti di Interesse tramite descrizione");
	}

	@Override
	public void startForm(User user, Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<PointOfInterest> pointOfInterestList = poiService.searchPOIByDescription(description);
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questa descrizione.");
        } else {
            System.out.println("Elenco Punti di Interesse con la descrizione cercata:");
            for(PointOfInterest pointOfInterest: pointOfInterestList) {
                System.out.println(pointOfInterest);
            }
        }
	}
}
