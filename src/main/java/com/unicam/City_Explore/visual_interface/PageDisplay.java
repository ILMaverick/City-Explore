package com.unicam.City_Explore.visual_interface;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class PageDisplay {
	
	public void showMenu(MenuPage toShow) {
		System.out.println("=== " + toShow.getTitle() + " ===");
		for (int id = 0; id < toShow.getChapters().size(); id++) {
			System.out.println((id + 1) + ". " + toShow.getChapters().get(id));
		}
		System.out.println("0. Esci");
	}
}
