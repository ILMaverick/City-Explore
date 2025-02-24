package com.unicam.City_Explore.visual_interface.form_pages.tour;

import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ShowTourSavedPage extends FormPage {

	@Autowired
	private TourService tourService;
	
	public ShowTourSavedPage() {
		super("Elenco di tutti gli Itinerari salvati");
	}

	@Override
	public void startForm(Scanner scanner) {
		List<Tour> tourList = tourService.getAllTours();
        if (tourList.isEmpty()) {
            System.out.println("Nessun Itinerario salvato!");
        } else {
            for (Tour tour: tourList) {
                System.out.println(tour + "\n");
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
