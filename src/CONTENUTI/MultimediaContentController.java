package CONTENUTI;

import POI.POIService;
import POI.PointOfInterest;
import USER.User;

import java.util.List;
import java.util.Scanner;

public class MultimediaContentController {

    private Scanner scanner;
    private MultimediaContentService multimediaContentService;
    private POIService poiService;

    public MultimediaContentController(POIService poiService) {
        scanner = new Scanner(System.in);
        this.poiService = poiService;
        MultimediaContentRepository multimediaContentRepository = new InMemoryMultimediaContent();
        this.multimediaContentService = new MultimediaContentService(multimediaContentRepository);
    }

    public void loadMultimediaContent() {
        System.out.println("=== Creazione di un Contenuto Multimediale ===");

        List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList == null || poiList.isEmpty()) {
            System.out.println("Nessun POI disponibile per caricare un Contenuto Multimediale.");
            return;
        }
        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println((i + 1) + ". " + poiList.get(i));
        }

        // L'utente seleziona il POI da utilizzare
        System.out.print("Seleziona il POI da utilizzare (inserisci il numero): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        if (selection < 1 || selection > poiList.size()) {
            System.out.println("Selezione non valida.");
            return;
        }
        PointOfInterest selectedPOI = poiList.get(selection - 1);

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        User currentUser = getCurrentUser();

        System.out.print("Inserisci il formato: ");
        String formatString = scanner.nextLine();
        FormatFileEnum format = FormatFileEnum.formatFile(formatString);

        System.out.print("Inserisci la durata: ");
        float duration = scanner.nextFloat();

        System.out.print("Inserisci la dimensione: ");
        float dimension = scanner.nextFloat();

        System.out.print("Inserisci la risoluzione: ");
        float resolution = scanner.nextFloat();

        MultimediaContent multimediaContent = multimediaContentService.loadMultimediaContent(name, description, currentUser, format, duration, dimension, resolution, selectedPOI);

        System.out.println("Contenuto Multimediale caricato in data: " + multimediaContent.getDataCreation());
        System.out.println(multimediaContent);
    }

    public void displayAllMultimediaContent() {
        List<MultimediaContent> multimediaContentList = multimediaContentService.getAllMultimediaContent();
        if(multimediaContentList.isEmpty()) {
            System.out.println("Nessun Contenuto Multimediale salvato.");
        } else {
            System.out.println("Elenco Contenuti Multimediali salvati:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    private User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
