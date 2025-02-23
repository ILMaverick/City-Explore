package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.poi.POIService;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.poi.ManagePOIPage;

@Component
public class InizializerPage extends FormPage {

	@Autowired
	private ManagePOIPage managePOIPage;
	@Autowired
	private POIService poiService;
	
	public InizializerPage() {
		super("Inizializzazione Elementi...");
	}

	@Override
	public void startForm(Scanner scanner) {
		this.poiService.initializer();
		System.out.println("Elementi inizializzati.");
	}

	@Override
	public Page getNext() {
		return this.managePOIPage;
	}
}
