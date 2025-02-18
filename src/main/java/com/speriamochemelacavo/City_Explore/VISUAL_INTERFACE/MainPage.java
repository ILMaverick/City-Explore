package com.speriamochemelacavo.City_Explore.VISUAL_INTERFACE;

public class MainPage implements Page {

	@Override
	public void show() {
		System.out.println("=== Menu Principale ===");
        System.out.println("1. Gestione POI");
        System.out.println("2. Gestione Tour");
        System.out.println("3. Gestione Contest");
        System.out.println("4. Gestione Evento");
        System.out.println("5. Gestione Contenuti Multimediale");
        System.out.println("6. Validazione Elementi e Contenuti Pendenti");
        System.out.println("7. Esci");
        System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6, 7): ");
	}

}
