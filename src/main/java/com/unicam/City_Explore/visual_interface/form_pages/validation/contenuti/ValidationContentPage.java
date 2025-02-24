package com.unicam.City_Explore.visual_interface.form_pages.validation.contenuti;

import java.util.Scanner;


import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class ValidationContentPage extends FormPage {
	
	public ValidationContentPage() {
		super("Valida Contenuti Multimediali Pendenti");
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
