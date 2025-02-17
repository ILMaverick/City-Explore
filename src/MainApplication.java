import java.util.Scanner;

import CONTENUTI.MultimediaContentController;
import CONTEST.ContestController;
import EVENTO.EventController;
import POI.POIController;
import TOUR.TourController;
import VALIDAZIONE.ValidationController;


public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        POIController poiController = new POIController();
        TourController tourController = new TourController();
        ContestController contestController = new ContestController();
        EventController eventController = new EventController();
        MultimediaContentController multimediaContentController = new MultimediaContentController();
        ValidationController validationController = new ValidationController();

        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principale ===");
            System.out.println("0. Inizializza Punti di Interesse, Contenuti, Contest ed Evento (Avviene solo una volta)");
            System.out.println("1. Gestione POI");
            System.out.println("2. Gestione Tour");
            System.out.println("3. Gestione Contest");
            System.out.println("4. Gestione Evento");
            System.out.println("5. Gestione Contenuti Multimediale");
            System.out.println("6. Validazione Elementi e Contenuti Pendenti");
            System.out.println("7. Esci");
            System.out.print("Seleziona un'opzione (0, 1, 2, 3, 4, 5, 6, 7): ");
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
        System.out.println("Programma terminato.");
    }

    private static void initializer(POIController poiController, MultimediaContentController multimediaContentController,
                                    ContestController contestController, EventController eventController) {
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
            System.out.println("4. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2, 3 o 4): ");
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
            System.out.println("1. Crea Tour da POI");
            System.out.println("2. Visualizza tutti i Tour salvati");
            System.out.println("3. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2 o 3): ");
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
        //TODO
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Menu Gestione Contest ===");
            System.out.println("1. Crea Contest");
            System.out.println("2. Visualizza tutti i Contest salvati");
            System.out.println("3. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2 o 3): ");
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
            System.out.println("5. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2, 3, 4 o 5): ");
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
    private static void manageMultimediaContent(Scanner scanner, MultimediaContentController multimediaContentController) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Menu Gestione Contenuti Multimediali ===");
            System.out.println("1. Crea Contenuto");
            System.out.println("2. Carica Contenuto ad un POI");
            System.out.println("3. Visualizza tutti i Contenuti Multimediali salvati");
            System.out.println("4. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2, 3 o 4): ");
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
}

