package visual_interface.menu_pages;

import java.util.ArrayList;

import visual_interface.Page;

public class GestioneTOURPage extends MenuPage {

	public GestioneTOURPage() {
		super ("Gestione TOUR");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea Itinerario da POI");
		chapters.add("Visualizza tutti gli Itinerari salvati");
		chapters.add("Ricerca Itinerario tramite nome");
		chapters.add("Ricerca Itinerario tramite descrizione");
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
