package visual_interface.menu_pages;

import java.util.ArrayList;

import visual_interface.Page;

public class GestioneContestPage extends MenuPage {

	public GestioneContestPage() {
		super ("Gestione Contest");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Contest");
		chapters.add("Visualizza tutti i Contest salvati");
		chapters.add("Ricerca Contest tramite nome");
		chapters.add("Ricerca Contest tramite descrizione");
		this.setChapters(chapters);
		ArrayList<Page> pages = new ArrayList<Page>();
//		pages.add(new GestionePOIPage());
//		pages.add(new GestioneTOURPage());
//		pages.add(new GestioneContestPage());
//		pages.add(new GestioneEventoPage());
		this.setPages(pages);
		this.setPrevious(new MainPage());
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
