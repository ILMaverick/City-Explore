package com.unicam.City_Explore.visual_interface.menu_pages;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.autorizzazione.AuthorizationService;
import com.unicam.City_Explore.visual_interface.Page;


@Component
public abstract class MenuPage extends Page {
	
	@Autowired
	protected AuthorizationService authService;
	
	private ArrayList<String> chapters = new ArrayList<String>();
	private HashMap<String, Page> linksTable = new HashMap<String, Page>();
	
	public MenuPage() {
		super();
	}
	
	public MenuPage(String title) {
		super(title);
	}
	
	public abstract void setAuthorization();
	
	public abstract void populateLinksTable();

	/**
	 * @return the chapters
	 */
	public ArrayList<String> getChapters() {
		return chapters;
	}

	/**
	 * @param chapters the chapters to set
	 */
	protected void setChapters(ArrayList<String> chapters) {
		this.chapters = chapters;
	}

	/**
	 * @return the linksTable
	 */
	public HashMap<String, Page> getLinksTable() {
		return linksTable;
	}

	/**
	 * @param linksTable the linksTable to set
	 */
	protected void setLinksTable(HashMap<String, Page> linksTable) {
		this.linksTable = linksTable;
	}
}
