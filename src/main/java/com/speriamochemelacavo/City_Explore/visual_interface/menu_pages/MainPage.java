package visual_interface.menu_pages;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class MainPage extends MenuPage{

	public MainPage() {
		super ("Menu Principale");
		HashMap<String, Page> chapters = new HashMap<String, Page>();
		chapters.put("Gestione POI", new );
		chapters.add("Gestione Tour");
		chapters.add("Gestione Contest");
		chapters.add("Gestione Evento");
		chapters.add("Gestione Contenuti Multimediali");
		chapters.add("Validazione Elementi e Contenuti Pendenti");
		this.setChapters(chapters);
	}
}
