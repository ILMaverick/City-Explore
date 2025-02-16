import java.util.Scanner;

import CONTENUTI.MultimediaContentController;
import CONTEST.ContestController;
import EVENTO.EventoController;
import POI.POIController;
import TOUR.TourController;


public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        POIController poiController = new POIController();
        TourController tourController = new TourController(poiController.getPOIService());
        ContestController contestController = new ContestController();
        EventoController eventoController = new EventoController(poiController.getPOIService());
        MultimediaContentController multimediaContentController = new MultimediaContentController(poiController.getPOIService());
        
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principale ===");
            System.out.println("1. Gestione POI");
            System.out.println("2. Gestione Tour");
            System.out.println("3. Gestione Contest");
            System.out.println("4. Gestione Evento");
            System.out.println("5. Gestione Contenuti Multimediale");
            System.out.println("6. Esci");
            System.out.print("Seleziona un'opzione (1, 2, 3, 4, 5, 6): ");
            int mainOption = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            
            switch (mainOption) {
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
                    manageEvento(scanner, eventoController);
                    break;
                case 5:
                    manageMultimediaContent(scanner, multimediaContentController);
                    break;
                case 6:
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
        System.out.println("Programma terminato.");
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
    private static void manageEvento(Scanner scanner, EventoController eventoController) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Menu Gestione Evento ===");
            System.out.println("1. Crea Evento da POI");
            System.out.println("2. Visualizza tutti gli Eventi salvati");
            System.out.println("3. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2 o 3): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            
            switch (option) {
                case 1:
                    eventoController.createEventoFromInput();
                    break;
                case 2:
                    eventoController.displayAllEventi();
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

    //Menù Gestione Contenuti Multimediali
    private static void manageMultimediaContent(Scanner scanner, MultimediaContentController multimediaContentController) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Menu Gestione Contenuti Multimediali ===");
            System.out.println("1. Carica Contenuto ad un POI");
            System.out.println("2. Visualizza tutti i Contenuti Multimediali salvati");
            System.out.println("3. Torna al menu principale");
            System.out.print("Seleziona un'opzione (1, 2 o 3): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (option) {
                case 1:
                    multimediaContentController.loadMultimediaContent();
                    break;
                case 2:
                    multimediaContentController.displayAllMultimediaContent();
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
}

