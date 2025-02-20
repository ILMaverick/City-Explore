import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import contenuti.MultimediaContentController;
import contest.ContestController;
import eliminazione.DeletionController;
import evento.EventController;
import poi.POIController;
import tour.TourController;
import validazione.ValidationController;

@SpringBootApplication
public class CityExploreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityExploreApplication.class, args);
	}

	Scanner scanner = new Scanner(System.in);
	POIController poiController = new POIController();
	TourController tourController = new TourController();
	ContestController contestController = new ContestController();
	EventController eventController = new EventController();
	MultimediaContentController multimediaContentController = new MultimediaContentController();
	ValidationController validationController = new ValidationController();
	DeletionController deletionController = new DeletionController();

	boolean exit = false;
	{
		while (!exit) {
			System.out.println("=== Menu Principale ===");
			System.out.println(
					"0. Inizializza Punti di Interesse, Contenuti, Contest ed Evento (Avviene solo una volta)");
			System.out.println("1. Gestione POI");
			System.out.println("2. Gestione Tour");
			System.out.println("3. Gestione Contest");
			System.out.println("4. Gestione Evento");
			System.out.println("5. Gestione Contenuti Multimediale");
			System.out.println("6. Validazione Elementi e Contenuti Pendenti");
			System.out.println("7. Eliminazione Elementi e Contenuti");
			System.out.println("8. Esci");
			System.out.print("Seleziona un'opzione (0, 1, 2, 3, 4, 5, 6, 7, 8): ");
			int mainOption = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (mainOption) {
			case 0:
				initializer(poiController, multimediaContentController, contestController, eventController);
				break;
			case 1:
				managePOI(scanner, poiController);
				break;
			case 2:
				manageTour(scanner, tourController);
				break;
			case 3:
				manageContest(scanner, contestController);
				break;
			case 4:
				manageEvent(scanner, eventController);
				break;
			case 5:
				manageMultimediaContent(scanner, multimediaContentController);
				break;
			case 6:
				manageValidation(scanner, validationController);
				break;
			case 7:
				manageDeletion(scanner, deletionController);
				break;
			case 8:
				exit = true;
				System.out.println("Uscita dal programma...");
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!exit) {
				System.out.println("\nPremi INVIO per tornare al menu principale...");
				scanner.nextLine();
			}
		}

		scanner.close();
		poiController.close();
		tourController.close();
		contestController.close();
		eventController.close();
		multimediaContentController.close();
		validationController.close();
		deletionController.close();
		System.out.println("Programma terminato.");
	}

	private static void initializer(POIController poiController,
			MultimediaContentController multimediaContentController, ContestController contestController,
			EventController eventController) {
		poiController.initializer();
		multimediaContentController.initializer();
		contestController.initializer();
		eventController.initializer();
		System.out.println("Inizializzazione avvenuta con successo.");
	}

// Sotto-menu per la gestione dei POI
	private static void managePOI(Scanner scanner, POIController poiController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione POI ===");
			System.out.println("1. Crea PointOfInterest da zero");
			System.out.println("2. Crea PointOfInterest da OSM");
			System.out.println("3. Visualizza tutti i PointOfInterest salvati");
			System.out.println("4. Ricerca Punto di Interesse tramite nome");
			System.out.println("5. Ricerca Punto di Interesse tramite descrizione");
			System.out.println("6. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				poiController.createPOIFromScratch();
				break;
			case 2:
				poiController.createPOIFromOSM();
				break;
			case 3:
				poiController.displayAllPOIs();
				break;
			case 4:
				poiController.searchPOIByName();
				break;
			case 5:
				poiController.searchPOIByDescription();
			case 6:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

// Sotto-menu per la gestione dei Tour
	private static void manageTour(Scanner scanner, TourController tourController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Tour ===");
			System.out.println("1. Crea Itinerario da POI");
			System.out.println("2. Visualizza tutti gli Itinerari salvati");
			System.out.println("3. Ricerca Itinerario tramite nome");
			System.out.println("4. Ricerca Itinerario tramite descrizione");
			System.out.println("5. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				tourController.createTourFromPOIs();
				break;
			case 2:
				tourController.displayAllTours();
				break;
			case 3:
				tourController.searchTourByName();
				break;
			case 4:
				tourController.searchTourByDescription();
			case 5:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

// Sotto-menu per la gestione dei Contest
	private static void manageContest(Scanner scanner, ContestController contestController) {
		// TODO
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Contest ===");
			System.out.println("1. Crea Contest");
			System.out.println("2. Visualizza tutti i Contest salvati");
			System.out.println("3. Ricerca Contest tramite nome");
			System.out.println("4. Ricerca Contest tramite descrizione");
			System.out.println("5. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				contestController.createContest();
				break;
			case 2:
				contestController.displayAllContest();
				break;
			case 3:
				contestController.searchContestByName();
				break;
			case 4:
				contestController.searchContestByDescription();
			case 5:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

// Sotto-menu per la gestione degli Eventi
	private static void manageEvent(Scanner scanner, EventController eventController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Evento ===");
			System.out.println("1. Crea Evento");
			System.out.println("2. Aggiungi Evento a POI");
			System.out.println("3. Aggiorna Evento");
			System.out.println("4. Visualizza tutti gli Eventi salvati");
			System.out.println("5. Visualizza tutti i POI salvati");
			System.out.println("6. Ricerca Evento tramite nome");
			System.out.println("7. Ricerca Evento tramite descrizione");
			System.out.println("8. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6, 7, 8): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				eventController.createEventFromInput();
				break;
			case 2:
				eventController.addEventToPOI();
				break;
			case 3:
				eventController.updateEvent();
				break;
			case 4:
				eventController.displayAllEvents();
				break;
			case 5:
				eventController.displayAllPoI();
				break;
			case 6:
				eventController.searchEventByName();
				break;
			case 7:
				eventController.searchEventByDescription();
				break;
			case 8:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

//Sotto-menu Gestione Contenuti Multimediali
	private static void manageMultimediaContent(Scanner scanner,
			MultimediaContentController multimediaContentController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Contenuti Multimediali ===");
			System.out.println("1. Crea Contenuto");
			System.out.println("2. Carica Contenuto ad un POI");
			System.out.println("3. Visualizza tutti i Contenuti Multimediali salvati");
			System.out.println("4. Ricerca Contenuto tramite nome");
			System.out.println("5. Ricerca Contenuto tramite descrizione");
			System.out.println("6. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				multimediaContentController.createMultimediaContent();
				break;
			case 2:
				multimediaContentController.loadMultimediaContentToPOI();
				break;
			case 3:
				multimediaContentController.displayAllMultimediaContent();
				break;
			case 4:
				multimediaContentController.searchMultimediaContentByName();
				break;
			case 5:
				multimediaContentController.searchMultimediaContentByDescription();
				break;
			case 6:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

	private static void manageValidation(Scanner scanner, ValidationController validationController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Validazione ===");
			System.out.println("1. Validazione");
			System.out.println("2. Mostra POI Pendenti");
			System.out.println("3. Mostra Itinerari Pendenti");
			System.out.println("4. Mostra Contenuti Multimediali Pendenti");
			System.out.println("5. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4 o 5): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				validationController.validation();
				break;
			case 2:
				validationController.displayAllPOiPending();
				break;
			case 3:
				validationController.displayAllTourPending();
				break;
			case 4:
				validationController.displayAllMultimediaContentPending();
				break;
			case 5:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}

	private static void manageDeletion(Scanner scanner, DeletionController deletionController) {
		boolean back = false;
		while (!back) {
			System.out.println("\n=== Menu Gestione Eliminazione ===");
			System.out.println("1. Elimina POI");
			System.out.println("2. Elimina Itinerario");
			System.out.println("3. Elimina Contest");
			System.out.println("4. Elimina Evento");
			System.out.println("5. Elimina Contenuto");
			System.out.println("6. Torna al menu principale");
			System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6): ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			switch (option) {
			case 1:
				deletionController.deletePOI();
				break;
			case 2:
				deletionController.deleteTour();
				break;
			case 3:
				deletionController.deleteContest();
				break;
			case 4:
				deletionController.deleteEvent();
				break;
			case 5:
				deletionController.deleteMultimediaContent();
				break;
			case 6:
				back = true;
				break;
			default:
				System.out.println("Opzione non valida.");
				break;
			}
			if (!back) {
				System.out.println("\nPremi INVIO per continuare...");
				scanner.nextLine();
			}
		}
	}
}
