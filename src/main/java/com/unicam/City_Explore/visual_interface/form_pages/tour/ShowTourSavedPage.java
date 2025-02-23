package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ShowTourSavedPage extends FormPage {

	@Autowired
	private POIService poiService;
	
	public ShowTourSavedPage() {
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

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
