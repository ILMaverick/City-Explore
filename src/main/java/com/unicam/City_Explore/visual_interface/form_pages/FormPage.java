package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;

@Component
public abstract class FormPage extends Page {
	
	public FormPage() {
		super();
	}

	public FormPage(String title) {
		super(title);
	}
	
	public abstract void startForm(Scanner scanner);

	/**
	 * @return the next
	 */
	public abstract Page getNext();
}
