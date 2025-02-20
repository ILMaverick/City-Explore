package com.unicam.City_Explore.visual_interface;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public abstract class Page {

	private String title;
	
	private MenuPage previous;
	
	public Page() {
		
	}

	public Page(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the previous
	 */
	public Page getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(MenuPage previous) {
		this.previous = previous;
	}
}
