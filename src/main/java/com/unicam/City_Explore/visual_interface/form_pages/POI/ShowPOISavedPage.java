package com.unicam.City_Explore.visual_interface.form_pages.POI;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;

import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.ManagePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class ShowPOISavedPage extends FormPage {

	@Autowired
	private POIService poiService;
	
	public ShowPOISavedPage() {
		super("Elenco di tutti i POI salvati");
	}

	@Override
	public void startForm(Scanner scanner) {
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
