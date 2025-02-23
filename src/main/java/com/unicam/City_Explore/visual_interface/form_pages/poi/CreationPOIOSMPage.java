package com.unicam.City_Explore.visual_interface.form_pages.poi;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.osm.OSMSearchService;
import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class CreationPOIOSMPage extends FormPage {
	
	@Autowired
	private POIService poiService;
	@Autowired
	private OSMSearchService osmSearchService;
	
	public CreationPOIOSMPage() {
		super("Creazione di un PointOfInterest a partire da OSM");
	}

	@Override
	public void startForm(Scanner scanner) {
		// Esegui la ricerca tramite il servizio
        System.out.print("Inserisci il nome della citta': ");
        String city = scanner.nextLine();
        System.out.print("Tipi disponibili:\n"
                + "-TURISMO: monumenti, musei, quartieri_storici, teatri, luoghi_culto, zone_pedonali, planetari\n"
                + "-ALLOGGI: hotels, motels, ostelli, guest_house\n"
                + "-SERVIZI: scuole, universita'�, ospedali, farmacie, cinema, mercati, ristoranti\n"
                + "-NATURA: parchi, foreste, vette, vigneti, spiagge\n"
                + "Inserisci il tipo di POI: ");
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
        System.out.print("Seleziona l'elemento da utilizzare (inserisci il numero): ");
        int selection = scanner.nextInt();
        scanner.nextLine();
        if (selection < 1 || selection > results.size()) {
            System.out.println("Selezione non valida.");
            return;
        }

        OverpassElement selectedElement = results.get(selection - 1);
        POIType poiType = POIType.fromOSMTag(poi);

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = poiService.createPOIFromOSM(selectedElement, poiType);

        System.out.println("\nPointOfInterest creato dalla ricerca OSM:");
        System.out.println(this.poiService.searchPOIByName(newPoi.getName()));
        
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
