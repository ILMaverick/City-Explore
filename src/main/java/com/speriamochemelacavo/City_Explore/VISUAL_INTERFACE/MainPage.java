package VISUAL_INTERFACE;

import java.util.ArrayList;
import java.util.HashMap;

public class MainPage extends MenuPage{

	public MainPage() {
		super ("Menu Principale");
		HashMap<String, Page> chapters = new HashMap<String, Page>();
		chapters.add("Gestione POI");
		chapters.add("Gestione Tour");
		chapters.add("Gestione Contest");
		chapters.add("Gestione Evento");
		chapters.add("Gestione Contenuti Multimediali");
		chapters.add("Validazione Elementi e Contenuti Pendenti");
		this.setChapters(chapters);
	}
}
