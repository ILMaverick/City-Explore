package CONTENUTI;

import POI.InMemoryPOIRepository;
import POI.POIRepository;
import POI.PointOfInterest;
import USER.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MultimediaContentService {
    private MultimediaContentRepository multimediaContentRepository;
    private POIRepository poiRepository;
    private Scanner scanner;

    public MultimediaContentService() {
        this.multimediaContentRepository = new InMemoryMultimediaContent();
        this.poiRepository = new InMemoryPOIRepository();
        scanner = new Scanner(System.in);
    }

    public MultimediaContent loadMultimediaContent(String name, String description, User author, FormatFileEnum format, float duration, float dimension, float resolution, PointOfInterest poi) {


        MultimediaContent multimediaContent = new MultimediaContent(name, description, author);
        multimediaContent.setFormatFileEnum(format);
        multimediaContent.setDuration(duration);
        multimediaContent.setDimension(dimension);
        multimediaContent.setResolution(resolution);
        multimediaContent.setPointOfInterest(poi);
        multimediaContent.setDataCreation(LocalDateTime.now());
        multimediaContentRepository.save(multimediaContent);
        return multimediaContent;
    }

    public void loadMultimediaContent() {

        System.out.println("=== Creazione di un Contenuto Multimediale ===");
        /**
        List<PointOfInterest> poiList = poiRepository.findAll();

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
        */

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

        System.out.print("Inserisci l'ID del Punto di Interesse: ");
        String idPOI = scanner.nextLine();
        PointOfInterest poi = poiRepository.findById(idPOI);

        MultimediaContent multimediaContent = loadMultimediaContent(name, description, currentUser, format, duration, dimension, resolution, poi);

        System.out.println("Contenuto Multimediale caricato in data: " + multimediaContent.getDataCreation());
        System.out.println(multimediaContent);
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

    public void saveMultimediaContent(MultimediaContent multimediaContent) {
        multimediaContentRepository.save(multimediaContent);
    }

    public List<MultimediaContent> getAllMultimediaContent() {
        return multimediaContentRepository.findAll();
    }

    public MultimediaContent getMultimediaContentById(String id) {
        return multimediaContentRepository.findById(id);
    }

}
