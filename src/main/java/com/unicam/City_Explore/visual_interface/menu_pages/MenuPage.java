package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.Page;


@Component
public abstract class MenuPage extends Page {
	
	private ArrayList<String> chapters;
	private ArrayList<Page> pages;
	
	public MenuPage() {
		super();
	}
	
	public MenuPage(String title) {
		super(title);
	}
	
	public Page nextPage(int idChapther) {
		if (idChapther == 0) {
			return this.getPrevious();
		}
		return pages.get(idChapther - 1);
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

	/**
	 * @param pages the pages to set
	 */
	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public abstract Page getNext(int idChapter);
}
