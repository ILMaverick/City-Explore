package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.visual_interface.Page;

@Component
public abstract class FormPage extends Page {
	
	public ArrayList<String> form;

	public FormPage() {
		super();
	}

	public FormPage(String title) {
		super(title);
	}

	/**
	 * @return the form
	 */
	public ArrayList<String> getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(ArrayList<String> form) {
		this.form = form;
	}
	
	public abstract void startForm(User user, Scanner scanner);
}
