package visual_interface.menu_pages;

import java.util.ArrayList;

import visual_interface.Page;

public class GestionePOIPage extends MenuPage {

	public GestionePOIPage() {
		super ("Gestione POI");
		ArrayList<String> chapters = new ArrayList<String>();
		chapters.add("Crea PointOfInterest da zero");
		chapters.add("Crea PointOfInterest da OSM");
		chapters.add("Visualizza tutti i PointOfInterest salvati");
		chapters.add("Ricerca Punto di Interesse tramite nome");
		chapters.add("Ricerca Punto di Interesse tramite descrizione");
		this.setChapters(chapters);
		ArrayList<Page> pages = new ArrayList<Page>();
//		pages.add(new GestionePOIPage());
//		pages.add(new GestioneTOURPage());
//		pages.add(new GestioneContestPage());
//		pages.add(new GestioneEventoPage());
//		pages.add(new GestioneContenutiPage());
		this.setPages(pages);
		this.setPrevious(null);
	}

	@Override
	public Page getNext(int idChapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
