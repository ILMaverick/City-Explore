package com.unicam.City_Explore.visual_interface.form_pages.evento;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class UpdateEventPage extends FormPage {

	public UpdateEventPage() {
		super("Aggiorna POI");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startForm(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
