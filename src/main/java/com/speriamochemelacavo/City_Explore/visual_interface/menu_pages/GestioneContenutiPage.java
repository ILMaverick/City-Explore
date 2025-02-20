package visual_interface.menu_pages;

import java.util.ArrayList;

import visual_interface.Page;

public class GestioneContenutiPage extends MenuPage {

	public GestioneContenutiPage() {
		super ("Gestione Contenuti Multimediali");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Contenuto");
		chapters.add("Carica Contenuto ad un POI");
		chapters.add("Visualizza tutti i Contenuti Multimediali salvati");
		chapters.add("Ricerca Contenuto tramite nome");
		chapters.add("Ricerca Contenuto tramite descrizione");
		this.setChapters(chapters);
		ArrayList<Page> pages = new ArrayList<Page>();
//		pages.add(new GestionePOIPage());
//		pages.add(new GestioneTOURPage());
//		pages.add(new GestioneContestPage());
//		pages.add(new GestioneEventoPage());
//		pages.add(new GestioneContenutiPage());
		this.setPages(pages);
		this.setPrevious(new MainPage());
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
