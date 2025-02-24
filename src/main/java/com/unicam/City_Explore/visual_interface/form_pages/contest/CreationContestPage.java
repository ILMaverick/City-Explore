package com.unicam.City_Explore.visual_interface.form_pages.contest;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class CreationContestPage extends FormPage {
	
	
	public CreationContestPage() {
		super("Creazione di un nuovo Contest da zero");
	}

	@Override
	public void startForm(Scanner scanner) {
		
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
