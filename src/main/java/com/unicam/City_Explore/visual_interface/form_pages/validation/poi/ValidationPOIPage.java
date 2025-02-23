package com.unicam.City_Explore.visual_interface.form_pages.validation.poi;

import java.util.Scanner;


import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ValidationPOIPage extends FormPage {
	
	public ValidationPOIPage() {
		super();
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
