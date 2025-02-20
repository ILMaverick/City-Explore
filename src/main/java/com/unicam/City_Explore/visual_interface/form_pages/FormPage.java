package com.unicam.City_Explore.visual_interface.form_pages;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;

@Component
public abstract class FormPage extends Page {
	
	public LinkedList<String> form;

	public FormPage() {
		super();
	}

	public FormPage(String title) {
		super(title);
	}

	/**
	 * @return the form
	 */
	public LinkedList<String> getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(LinkedList<String> form) {
		this.form = form;
	}	
}
