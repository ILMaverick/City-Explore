package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.osm.OSMSearchService;
import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.visual_interface.menu_pages.GestionePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class CreazionePOIOSMPage extends FormPage {
	
	@Autowired
	private POIService poiService;
	@Autowired
	private OSMSearchService osmSearchService;
	
	public CreazionePOIOSMPage() {
		super("Creazione di un PointOfInterest a partire da OSM");
	}

	@Override
	public void startForm(User user, Scanner scanner) {
		// Esegui la ricerca tramite il servizio
        System.out.println("Inserisci il nome della citt‡: ");
        String city = scanner.nextLine();
        System.out.println("Inserisci il tipo di POI:\n"
                + "-TURISMO: monumenti, musei, quartieri_storici, teatri, luoghi_culto, zone_pedonali, planetari\n"
                + "-ALLOGGI: hotels, motels, ostelli, guest_house\n"
                + "-SERVIZI: scuole, universit√†, ospedali, farmacie, cinema, mercati, ristoranti\n"
                + "-NATURA: parchi, foreste, vette, vigneti, spiagge");
        String poi = scanner.nextLine();

        List<OverpassElement> results = osmSearchService.search(city, poi);

        if (results == null || results.isEmpty()) {
            System.out.println("Nessun elemento trovato dalla ricerca OSM.");
            return;
        }

        // Mostra la lista degli elementi trovati con indice
        System.out.println("Elementi trovati:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }

        // Chiedi all'utente di selezionare un elemento dalla lista
        System.out.println("Seleziona l'elemento da utilizzare (inserisci il numero): ");
        int selection = scanner.nextInt();
        if (selection < 1 || selection > results.size()) {
            System.out.println("Selezione non valida.");
            return;
        }

        OverpassElement selectedElement = results.get(selection - 1);
        POIType poiType = POIType.fromOSMTag(poi);

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = poiService.createPOIFromOSM(selectedElement, user, poiType);

        System.out.println("\nPointOfInterest creato dalla ricerca OSM:");
        System.out.println(newPoi);		
	}
}
