package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.visual_interface.menu_pages.GestionePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class CreazionePOIUtentePage extends FormPage {
	
	@Autowired
	private POIService poiService;
	
	public CreazionePOIUtentePage() {
		super("Creazione di un nuovo PointOfInterest da zero");
	}

	@Override
	public void startForm(User user, Scanner scanner) {
		System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci la latitudine: ");
        double lat = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci la longitudine: ");
        double lon = Double.parseDouble(scanner.nextLine());

        // In questo esempio, open_time e close_time sono lasciati null
        // Chiediamo anche il tipo di POI (da un enum: Turismo, Alloggio, Servizio, Natura, Altro)
        System.out.print("Inserisci il tipo di POI (Turismo, Alloggio, Servizio, Natura, Altro): ");
        String typeInput = scanner.nextLine().trim();
        POIType poiType;
        try {
            poiType = POIType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo non valido. Verrà  usato 'Altro'.\n");
            poiType = POIType.Altro;
        }

        // Crea il PointOfInterest utilizzando la factory
//        PointOfInterest newPoi = poiService.createPOIFromUser(name, description, lat, lon, user, poiType );
        
        System.out.println("PointOfInterest creato da zero:\n");
//        System.out.println(newPoi);
	}
}
