package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;


@Component
public abstract class MenuPage extends Page {
	
	private ArrayList<String> chapters;
	
	public MenuPage() {
		super();
	}
	
	public MenuPage(String title) {
		super(title);
	}

	/**
	 * @return the chapters
	 */
	public ArrayList<String> getChapters() {
		return chapters;
	}

	/**
	 * @param chapters the chapters to set
	 */
	public void setChapters(ArrayList<String> chapters) {
		this.chapters = chapters;
	}

	public abstract Page getNext (int idChapter);
}
