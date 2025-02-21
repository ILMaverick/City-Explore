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
public class VisualizzazionePOISalvatiPage extends FormPage {

	@Autowired
	private POIService poiService;
	
	public VisualizzazionePOISalvatiPage() {
		super("Elenco di tutti i POI salvati");
	}

	@Override
	public void startForm(User user, Scanner scanner) {
		List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList.isEmpty()) {
            System.out.println("Nessun POI salvato!");
        } else {
            for (PointOfInterest poi : poiList) {
                System.out.println(poi + "\n");
            }
        }
	}
}
