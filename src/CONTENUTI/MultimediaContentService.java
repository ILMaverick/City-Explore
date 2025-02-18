package CONTENUTI;

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

    public MultimediaContentService(MultimediaContentRepository multimediaContentRepository, POIRepository poiRepository) {
        scanner = new Scanner(System.in);
        this.multimediaContentRepository = multimediaContentRepository;
        this.poiRepository = poiRepository;
    }

    public void initializer() {
        User user = new User();
        user.setName("Simone");
        user.setSurname("Stacchiotti");
        user.setUsername("SilverSimon");

        MultimediaContent mc1 = new MultimediaContent("foto", "descrizione",user);
        MultimediaContent mc2 = new MultimediaContent("video", "secondo", user);
        MultimediaContent mc3 = new MultimediaContent("immagine", "terzo", user);

        mc1.setFormatFileEnum(FormatFileEnum.Image);
        mc1.setDuration(1);
        mc1.setDimension(1);
        mc1.setResolution(100);
        save(mc1);

        mc2.setFormatFileEnum(FormatFileEnum.Audio);
        mc2.setDuration(2);
        mc2.setDimension(2);
        mc2.setResolution(200);
        save(mc2);

        mc3.setFormatFileEnum(FormatFileEnum.Video);
        mc3.setDuration(3);
        mc3.setDimension(3);
        mc3.setResolution(400);
        save(mc3);

    }

    public void createMultimediaContent() {
        System.out.println("=== Creazione di un Contenuto Multimediale ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci il formato: ");
        String formatString = scanner.nextLine();
        FormatFileEnum format = FormatFileEnum.formatFile(formatString);

        System.out.print("Inserisci la durata: ");
        float duration = scanner.nextFloat();

        System.out.print("Inserisci la dimensione: ");
        float dimension = scanner.nextFloat();

        System.out.print("Inserisci la risoluzione: ");
        float resolution = scanner.nextFloat();

        User currentUser = getCurrentUser();

        MultimediaContent multimediaContent = createMultimediaContent(name, description, currentUser, format, duration, dimension, resolution);

        System.out.println("Contenuto Multimediale creato in data: " + multimediaContent.getDataCreation());
        System.out.println(multimediaContent);
    }

    public void loadMultimediaContentToPOI() {

        System.out.println("=== Caricamento Contenuto Multimediale su un Punto di Interesse ===");

        System.out.print("Inserisci l'ID del Punto di Interesse: ");
        int idPOI = scanner.nextInt();

        System.out.print("Inserisci l'ID del Contenuto: ");
        int idMC = scanner.nextInt();

        PointOfInterest poi = loadMultimediaContentToPOI(idPOI, idMC);

        System.out.println("Contenuto Multimediale caricato al Punto di Interesse: ");
        System.out.println(poi);
    }

    public void searchMultimediaContentByName() {
        System.out.println("=== Ricerca Contenuti Multimediali tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<MultimediaContent> multimediaContentList = searchMultimediaContentByName(name);
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto Multimediale con questo nome.");
        } else {
            System.out.println("Elenco Contenuti Multimediali con il nome cercato:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    public void searchMultimediaContentByDescription() {
        System.out.println("=== Ricerca Contenuti Multimediali tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<MultimediaContent> multimediaContentList = searchMultimediaContentByDescription(description);
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto Multimediale con questa descrizione.");
        } else {
            System.out.println("Elenco Contenuti Multimediali con la descrizione cercata:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    public MultimediaContent createMultimediaContent(String name, String description, User author, FormatFileEnum format, float duration, float dimension, float resolution) {
        MultimediaContent multimediaContent = new MultimediaContent(name, description, author);
        multimediaContent.setFormatFileEnum(format);
        multimediaContent.setDuration(duration);
        multimediaContent.setDimension(dimension);
        multimediaContent.setResolution(resolution);
        multimediaContent.setDataCreation(LocalDateTime.now());
        save(multimediaContent);
        return multimediaContent;
    }

    public PointOfInterest loadMultimediaContentToPOI(int idPOI, int idMC) {

        PointOfInterest poi = poiRepository.findById(idPOI);
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC);

        multimediaContent.setPointOfInterest(poi);
        save(multimediaContent);
        poi.getMultimediaContentList().add(multimediaContent);
        poiRepository.save(poi);
        return poi;
    }

    public void save(MultimediaContent multimediaContent) {
        multimediaContentRepository.save(multimediaContent);
    }

    public List<MultimediaContent> getAllMultimediaContent() {
        return multimediaContentRepository.findAll();
    }

    public MultimediaContent getMultimediaContentById(int id) {
        return multimediaContentRepository.findById(id);
    }

    public List<MultimediaContent> searchMultimediaContentByName(String name) {
        return multimediaContentRepository.searchByName(name);
    }

    public List<MultimediaContent> searchMultimediaContentByDescription(String description) {
        return multimediaContentRepository.searchByDescription(description);
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
