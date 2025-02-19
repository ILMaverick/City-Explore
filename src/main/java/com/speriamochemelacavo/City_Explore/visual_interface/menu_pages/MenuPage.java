package visual_interface.menu_pages;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import visual_interface.Page;

@Component
public abstract class MenuPage extends Page {
	
	private HashMap<String, Page> chapters;
	
	public MenuPage() {
		super();
	}
	
	public MenuPage(String title) {
		super(title);
	}

	/**
	 * @return the chapters
	 */
	public HashMap<String, Page> getChapters() {
		return chapters;
	}

	/**
	 * @param chapters the chapters to set
	 */
	public void setChapters(HashMap<String, Page> chapters) {
		this.chapters = chapters;
	}
}
